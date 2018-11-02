package com.bborzoi.bot

import com.bot4s.telegram.api.declarative.Commands
import com.bot4s.telegram.api.{ChatActions, Polling, TelegramBot}
import com.bot4s.telegram.clients.ScalajHttpClient
import com.bot4s.telegram.models._
import com.typesafe.config.Config


case class Negotiators(
                        dog: DogParley,
                      )


class BBorzoiBot(val token: String, val negotiators: Negotiators) extends TelegramBot
  with Polling
  with Commands
  with ChatActions {
  val client = new ScalajHttpClient(token)

  onMessage { implicit msg: Message =>
    msg.text
      .flatMap(negotiators.dog.say)
      .map(d => reply(d))
      .getOrElse(None)
  }

  onMessage { implicit msg: Message =>
    println("onMessage - 2")
  }
}


object BBorzoiBot {
  def apply(config: Config): BBorzoiBot = {
    new BBorzoiBot(
      config.getString("bot.token"),
      Negotiators(new DogParley)
    )
  }
}