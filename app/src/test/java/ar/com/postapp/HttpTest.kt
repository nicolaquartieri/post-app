package ar.com.postapp

import ar.com.infrastructure.repositories.utils.InfrastructureException
import ar.com.infrastructure.repositories.utils.infrastructureExceptionHandler
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class HttpTest {

    @Test
    fun should_return_httpError() = runBlockingTest {
        launch(retry) {
            getError()
        }
    }

    private fun getError(): InfrastructureException {
        throw InfrastructureException(Exception("Something happen in the network"))
    }

    private val retry = infrastructureExceptionHandler { exception ->
        println(exception.message)
    }
}