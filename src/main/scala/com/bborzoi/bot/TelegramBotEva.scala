package com.bborzoi.bot

import java.io.File

import com.bborzoi.cli.Eva
import com.typesafe.config.{Config, ConfigFactory}
import org.backuity.clist.Command

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object TelegramBotEva
  extends Command(description = "Telegram bot demon", name = "telegram-bot")
    with Eva {
  def run(): Unit = {
    val defaultConfig = ConfigFactory.load("bot.conf")
    val config: Config = Option(System.getProperty("bot.conf"))
      .map(cfg => ConfigFactory.parseFile(new File(cfg)))
      .getOrElse(ConfigFactory.empty)
      .withFallback(defaultConfig)
      .resolve()

    val bot = BBorzoiBot(config)
    val eol = bot.run()

    sys.addShutdownHook({
      println("Shutdown bot ...")
      bot.shutdown()
      Await.result(eol, Duration.Inf)
    })

    println("Bot running ...")
    while (true) {}
  }
}
