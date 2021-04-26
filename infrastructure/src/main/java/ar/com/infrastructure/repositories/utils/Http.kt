package ar.com.infrastructure.repositories.utils

import kotlinx.coroutines.CoroutineExceptionHandler
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object Http {
    suspend fun <T> httpError(call: suspend () -> T): T {
        try {
            return call.invoke()
        } catch (e: HttpException) {
            throw InfrastructureException(e)
        } catch (e: SocketTimeoutException) {
            throw InfrastructureException(e)
        } catch (e: UnknownHostException) {
            throw InfrastructureException(e)
        } catch (e: ConnectException) {
            throw InfrastructureException(e)
        }
    }
}

class InfrastructureException(e: Exception? = null) : Exception(e)

fun infrastructureExceptionHandler(onError: () -> Unit) = CoroutineExceptionHandler { _, t ->
    when (t) {
        is InfrastructureException -> onError.invoke()
        else -> throw t
    }
}
//private val retry = infrastructureExceptionHandler { view.showRetry { loadFilters() } }
