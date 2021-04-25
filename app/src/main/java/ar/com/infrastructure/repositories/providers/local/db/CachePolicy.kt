package ar.com.infrastructure.repositories.providers.local.db

import ar.com.infrastructure.repositories.providers.local.db.CachePolicy.*
import ar.com.utils.DateFormatter
import java.text.ParseException
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeUnit.*
import kotlin.math.abs

/**
 * This class handles the cache policy in the Car Parts domain based on a TTL setup by the
 * [CachePolicyConfig] class.
 *
 * TODO: In the future this class is going to handle the save policy state of the repository, like
 * NO_CACHE: acquire data from a service call without cache them.
 * AFTER_CALL: acquire data from a service call and then cache them.
 * BEFORE_CALL: check data first in cache and the call the service if needed. This is the current
 * Implementation (TTL controlled)
 */
class CachePolicy {

    data class CachePolicyConfig(val expireTimeUnit: TimeUnit,
                                 val expireTimeOut: Int)

    companion object {
        fun expireAfter(expireTimeUnit: TimeUnit = SECONDS,
                        expireTimeOut: Int = 0): CachePolicyConfig {
            return CachePolicyConfig(expireTimeUnit, expireTimeOut)
        }

        fun isThePolicyExpiredBasedOn(cacheDateAsString: String,
                                      policy: CachePolicyConfig): Boolean {
            val latestCachedDate = formatDataAsString(cacheDateAsString)
            return latestCachedDate.isExpiredBasedOnCurrent(policy)
        }

        private fun formatDataAsString(cacheDateAsString: String): Date = try {
                val dateFormatter = DateFormatter()
                dateFormatter.formatFrom(cacheDateAsString)
            } catch (e: ParseException) {
                // Toma make sure the cache will be refresh we use a long period of time.
                val date = Calendar.getInstance()
                date.add(Calendar.YEAR, -1)
                date.time
            }
    }
}

private fun Date.isExpiredBasedOnCurrent(cachePolicy: CachePolicyConfig): Boolean {
    val difference = calculateTimeDifferenceBasedOn(cachePolicy)
    return difference > cachePolicy.expireTimeOut
}

private fun Date.calculateTimeDifferenceBasedOn(cachePolicy: CachePolicyConfig): Long {
    val now = Calendar.getInstance().time
    val differenceInMillies = abs(now.time - this.time)

    return when (cachePolicy.expireTimeUnit) {
        SECONDS -> {
            SECONDS.convert(differenceInMillies, MILLISECONDS)
        }
        MINUTES -> {
            MINUTES.convert(differenceInMillies, MILLISECONDS)
        }
        HOURS -> {
            HOURS.convert(differenceInMillies, MILLISECONDS)
        }
        DAYS -> {
            DAYS.convert(differenceInMillies, MILLISECONDS)
        }
        else -> { 0 }
    }
}

enum class CacheFlags {
    POSTS
}
