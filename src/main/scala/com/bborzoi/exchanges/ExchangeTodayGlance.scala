package com.bborzoi.exchanges

import java.time.ZonedDateTime

import com.bborzoi.DateTimeGMT
import com.bborzoi.exchanges.cbr.{CbrXmlParser, Currency, ExchangeCourses}

class ExchangeTodayGlance(storageCbrFolder: String,
                          observableCurrencies: List[String])
  extends CbrXmlParser {

  def isObservableCurrency(c: Currency): Boolean = observableCurrencies.exists(c.isoName.contains)

  def lookAtObservableCurrenciesTodayFromFile: Either[ZonedDateTime, ExchangeCourses] =
    lookAtObservableCurrenciesFromFile(sourceCbrXmlFile)

  def lookAtObservableCurrenciesFromFile(xmlFilePath: String): Either[ZonedDateTime, ExchangeCourses] = {
    todayExchangeCoursesFromXml(xmlFilePath)
      .map(exchangeCourses => {
        val currencies = exchangeCourses.quotations
          .filter(isObservableCurrency)
          .map(c => c.copy(course = c.course / c.nominal, nominal = 1))
        ExchangeCourses(exchangeCourses.requested, currencies)
      })
  }

  def sourceCbrXmlFile: String = {
    val td = DateTimeGMT.nowDate
    val twoDigitDay = buildTwoDigitNumber(td.getDayOfMonth)
    val twoDigitMonth = buildTwoDigitNumber(td.getMonthValue)

    f"$storageCbrFolder/${td.getYear}/$twoDigitMonth/$twoDigitDay.xml"
  }

  private def buildTwoDigitNumber(number: Int) = {
    if (number > 9) f"$number" else f"0$number"
  }
}
