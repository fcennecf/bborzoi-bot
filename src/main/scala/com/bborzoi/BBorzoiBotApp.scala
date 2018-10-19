package com.bborzoi


import com.bborzoi.bot.TelegramBotEva
import com.bborzoi.exchanges.ExchangesEva
import org.backuity.clist._


object BBorzoiBotApp {
  def main(args: Array[String]): Unit = {
    Cli.parse(args)
      .withProgramName("BorzoiBot")
      .withCommands(TelegramBotEva, ExchangesEva)
      .foreach(_.run())
  }
}
