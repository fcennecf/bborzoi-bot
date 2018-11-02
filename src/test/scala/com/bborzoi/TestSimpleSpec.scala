package com.bborzoi

import org.scalatest.{FreeSpec, Matchers}

import scala.xml.XML




class TestSimpleSpec extends FreeSpec with Matchers {
  "Service" - {
    "equals" - {
      "success" in {
        val xml = XML.loadFile("./etc/cbr-2018-10-20.xml")
        val elems = (xml \\ "ValCurs" \\ "Valute")
        1 should be(1)
      }
    }
  }
}
