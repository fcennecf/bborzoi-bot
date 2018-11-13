package com.bborzoi

import org.scalatest.{FreeSpec, Matchers}

class TestSimpleSpec extends FreeSpec with Matchers {
  "Service" - {
    "equals" - {
      "success" in {
        1 should be(1)
      }
    }
  }
}
