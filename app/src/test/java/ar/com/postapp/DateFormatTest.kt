package ar.com.postapp

import ar.com.utils.DateFormatter
import org.junit.Assert
import org.junit.Test
import java.text.ParseException
import java.util.*

class DateFormatTest {

    @Test
    fun it_should_return_the_same_date_when_comes_from_Date_type() {
        val dateFormatter = DateFormatter()
        val currentDate = GregorianCalendar(2020, Calendar.MARCH, 1, 13, 1).time

        val actualStringDate: String = dateFormatter.formatFrom(currentDate)

        Assert.assertEquals("2020-03-01T13:01:00", actualStringDate)
    }

    @Test
    fun it_should_return_the_same_date_when_comes_from_String_type() {
        val dateFormatter = DateFormatter()
        val expectedDate = GregorianCalendar(2020, Calendar.MARCH, 1, 13, 1).time
        val currentStringDate = "2020-03-01T13:01:00"

        val currentDate: Date = dateFormatter.formatFrom(currentStringDate)

        Assert.assertEquals(expectedDate, currentDate)
    }

    @Test(expected = ParseException::class)
    fun it_should_return_an_exception() {
        val dateFormatter = DateFormatter()
        val currentStringDate = "------"

        dateFormatter.formatFrom(currentStringDate)
    }
}