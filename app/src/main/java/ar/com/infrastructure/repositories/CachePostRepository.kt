package ar.com.infrastructure.repositories

import ar.com.domain.entities.Post
import ar.com.infrastructure.repositories.providers.LocalPostProvider
import ar.com.infrastructure.repositories.providers.configuration.ConfigurationProvider
import ar.com.infrastructure.repositories.providers.local.db.CacheFlags
import ar.com.infrastructure.repositories.providers.local.db.CachePolicy
import ar.com.infrastructure.repositories.providers.remote.RemotePostProvider
import ar.com.utils.DateFormatter
import java.util.*
import java.util.concurrent.TimeUnit

class CachePostRepository(
    private val localPostProvider: LocalPostProvider,
    private val remotePostProvider: RemotePostProvider,
    private val configurationProvider: ConfigurationProvider)
    : PostRepository(remotePostProvider) {

    override suspend fun getAllPost(rowsOfPage: Int, page: Int): List<Post> {
        return if (shouldAcquireMoreDataFor(CacheFlags.POSTS))  {
            fetchAndCache(rowsOfPage, page)
        } else  {
            fetchFromCache(rowsOfPage, page)
        }
    }

    private suspend fun fetchFromCache(rowsOfPage: Int, page: Int) =
        localPostProvider.getAllPost(rowsOfPage, page)

    private fun shouldAcquireMoreDataFor(cacheFlag: CacheFlags): Boolean {
        return checkIfNeededBasedOn(cacheFlag, CachePolicy.expireAfter(
            expireTimeUnit = TimeUnit.DAYS,
            expireTimeOut = 1
        ))
    }

    private fun checkIfNeededBasedOn(cacheFlag: CacheFlags, policy: CachePolicy.CachePolicyConfig): Boolean {
        val latestRefresh = configurationProvider.getValue(cacheFlag.name, "")
        return CachePolicy.isThePolicyExpiredBasedOn(latestRefresh, policy)
    }

    private suspend fun fetchAndCache(rowsOfPage: Int, page: Int): List<Post> {
        val posts = super.getAllPost(rowsOfPage, page)
        cacheAllAndRefreshTTL(posts, CacheFlags.POSTS)
        return posts
    }

    private suspend fun cacheAllAndRefreshTTL(posts: List<Post>, cacheFlag: CacheFlags) {
        localPostProvider.insertAllPost(posts)
        refreshTTLWithKey(cacheFlag)
    }

    private fun refreshTTLWithKey(cacheFlag: CacheFlags) {
        val dateFormatter = DateFormatter()
        val dateToString = dateFormatter.formatFrom(Calendar.getInstance().time)
        configurationProvider.storeValue(cacheFlag.name, dateToString)
    }
}