package com.bborzoi.bot

trait Parley

class DogParley extends Parley {
  def say(message: String): Option[String] = {
    val keys = List("@tdmuninn", "tdmuninn", "muninn", "michael", "миша", "михаил")
    val isExists = keys.exists(message.toLowerCase.contains)
    if (isExists) Some("Ты чё, Пёс!") else None
  }
}