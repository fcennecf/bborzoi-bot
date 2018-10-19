package com.bborzoi.exchanges

import com.bborzoi.cli.Eva
import org.backuity.clist.Command


object ExchangesEva extends Command(
  description = "Synchronize currency exchanges for financial markets",
  name = "exchanges"
) with Eva {
  def run(): Unit = {
    println("Sync...")
  }
}
