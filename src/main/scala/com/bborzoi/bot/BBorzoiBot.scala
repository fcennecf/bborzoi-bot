package com.bborzoi.bot

import com.bot4s.telegram.api.declarative.Commands
import com.bot4s.telegram.api.{ChatActions, Polling, TelegramBot}
import com.bot4s.telegram.clients.ScalajHttpClient
import com.bot4s.telegram.models._
import com.typesafe.config.Config


case class Negotiators(
                        dog: DogParley,
                        witcher: WitcherParley
                      )


class BBorzoiBot(val token: String, val negotiators: Negotiators) extends TelegramBot
  with Polling
  with Commands
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
}


object BBorzoiBot {
  def apply(config: Config): BBorzoiBot = {
    new BBorzoiBot(
      config.getString("bot.token"),
      Negotiators(
        DogParley(),
        WitcherParley(config.getString("bot.quotes-withcer"))
      )
    )
  }
}