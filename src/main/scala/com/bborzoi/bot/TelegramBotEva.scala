package com.bborzoi.bot

import com.bborzoi.cli.Eva
import org.backuity.clist.Command

object TelegramBotEva extends Command(
  description = "Telegram bot demon",
  name = "telegram-bot"
) with Eva {
  def run(): Unit = {
    println("Running...")
  }
}
