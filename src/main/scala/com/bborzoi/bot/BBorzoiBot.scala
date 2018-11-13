package com.bborzoi.bot

import com.bborzoi.DateTimeGMT
import com.bot4s.telegram.api.declarative.{Commands, InlineQueries}
import com.bot4s.telegram.api.{ChatActions, Polling, TelegramBot}
import com.bot4s.telegram.clients.ScalajHttpClient
import com.bot4s.telegram.methods.ParseMode
import com.bot4s.telegram.models.{InputTextMessageContent, _}
import com.typesafe.config.Config


case class Negotiators(
                        dog: DogParley,
                        witcher: WitcherParley,
                        stock: ExchangeCoursesParley
                      )


class BBorzoiBot(val token: String, val negotiators: Negotiators) extends TelegramBot
  with Polling
  with Commands
  with InlineQueries
  with ChatActions {
  val client = new ScalajHttpClient(token)

  onMessage { implicit msg: Message =>
    msg.text
      .flatMap(negotiators.dog.say)
      .map(s => reply(s))
      .getOrElse(None)
  }

  onMessage { implicit msg: Message =>
    val authorName: Option[String] = msg.from.map(u => u.firstName)
    msg.text
      .flatMap(authorMessage => negotiators.witcher.say(authorMessage, authorName))
      .map(s => reply(s))
      .getOrElse(None)
  }

  onCommand('clips | 'clip | 'binder) { implicit msg =>
    withArgs { args =>
      replyMd(negotiators.stock.say())
    }
  }

  onInlineQuery { implicit iq =>

    val textMessage = InputTextMessageContent(
      negotiators.stock.say(),
      Some(ParseMode.Markdown),
      Some(true)
    )

    val clips = InlineQueryResultArticle(
      DateTimeGMT.nowDate.toString,
      "clips",
      textMessage
    )

    val results = Seq(clips)

    answerInlineQuery(results, Some(3600))

  }
}


object BBorzoiBot {
  def apply(config: Config): BBorzoiBot = {
    new BBorzoiBot(
      config.getString("bot.token"),
      Negotiators(
        DogParley(),
        WitcherParley(
          config.getString("bot.quotes-withcer")
        ),
        ExchangeCoursesParley(
          config.getString("bot.cbr-storage"),
          "CHF" :: "EUR" :: "USD" :: Nil
        )

      )
    )
  }
}