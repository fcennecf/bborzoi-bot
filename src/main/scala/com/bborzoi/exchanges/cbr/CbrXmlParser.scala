package com.bborzoi.exchanges.cbr

import java.time.ZonedDateTime

import com.bborzoi.DateTimeGMT

import scala.util.Try
import scala.xml.{Elem, Node, XML}

trait CbrXmlParser {

  import ValuteValue2Decimal._

  def todayExchangeCoursesFromXml(xmlFilePath: String): Either[ZonedDateTime, ExchangeCourses] = {
    Try(XML.loadFile(xmlFilePath))
      .map(xml => Right(todayExchangeCoursesFromXml(xml)))
      .getOrElse(Left(DateTimeGMT.nowDate))
  }

  def todayExchangeCoursesFromXml(xml: Elem): ExchangeCourses = {
    val quotations = (xml \\ "ValCurs" \\ "Valute")
      .map(transformValuteNodeToCurrency)
      .sortBy(_.isoName)
      .toList
    ExchangeCourses(DateTimeGMT.nowDate, quotations)
  }

  def transformValuteNodeToCurrency(valute: Node) =
    Currency(
      (valute \ "Name").text.toString,
      (valute \ "NumCode").text.toInt,
      (valute \ "CharCode").text.toString,
      (valute \ "Nominal").text.toInt,
      (valute \ "Value").text.toDecimal,
    )
}


