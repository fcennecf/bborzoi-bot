package com.bborzoi.bot

import java.io.IOException

import io.circe
import io.circe.generic.JsonCodec
import io.circe.parser.decode

import scala.io.Source
import scala.util.Random


trait Parley

class DogParley extends Parley {
  def say(message: String): Option[String] = {
    val keys = List("@tdmuninn", "tdmuninn", "muninn", "michael", "миша", "михаил")
    val isExists = keys.exists(message.toLowerCase.contains)
    if (isExists) Some("Ты чё, Пёс!") else None
  }
}

object DogParley {
  def apply(): DogParley = new DogParley()
}

@JsonCodec case class WitcherQuotes(quotes: List[String])

class WitcherParley(dictionary: WitcherQuotes) extends Parley {

  def say(message: String, authorName: Option[String]): Option[String] = {
    val keys = List("!", "нюанс")
    if (keys.exists(message.toLowerCase.contains)) {
      val quote: String = getRandomQuoteTemplate
      val jaskier = authorName.getOrElse("Лютик")
      Some(quote.replace("%1s", jaskier))
    } else
      None
  }

  def getRandomQuoteTemplate: String = {
    val quoteIndex = Random.nextInt(dictionary.quotes.length)
    dictionary.quotes(quoteIndex)
  }
}


object WitcherParley {
  def apply(quotesFile: String): WitcherParley = {
    val quotes = this.loadQuotes(quotesFile)
    new WitcherParley(quotes)
  }

  def loadQuotes(quotesFile: String): WitcherQuotes = {
    val fileContents = Source.fromFile(quotesFile).getLines.mkString
    val resultQuotes: Either[circe.Error, WitcherQuotes] = decode[WitcherQuotes](fileContents)
    resultQuotes match {
      case Right(quotes) => quotes
      case _ => throw new IOException("Error happen when loaded quotes from json")
    }
  }
}