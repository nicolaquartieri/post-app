package ar.com.postapp

import ar.com.infrastructure.repositories.utils.Http.httpError
import ar.com.infrastructure.repositories.utils.InfrastructureException
import ar.com.infrastructure.repositories.utils.infrastructureExceptionHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

@ExperimentalCoroutinesApi
class HttpTest {

    @Test
    fun should_return_httpError() = runBlockingTest {
        launch(retry) {
            val value = doSomething()
            assertEquals("Any value", value)
        }
    }

    private suspend fun doSomething(): String {
        return getValue(false)
    }

    private suspend fun getValue(state: Boolean): String = httpError {
        if (state) "Any value"
        else throw InfrastructureException(Exception("Something happen in the network"))

    }

    private fun getError(): InfrastructureException {
        throw InfrastructureException(Exception("Something happen in the network"))
    }

    private val retry = infrastructureExceptionHandler { exception ->
        println(exception.message)
    }
}