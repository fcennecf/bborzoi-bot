package com.bborzoi.exchanges.cbr


object ValuteValue2Decimal {

  trait ValuteValue2Decimal[V] {
    def toDecimal: BigDecimal
  }

  def instance[V](value: V)(func: V => BigDecimal): ValuteValue2Decimal[V] =
    new ValuteValue2Decimal[V] {
      def toDecimal: BigDecimal = func(value)
    }

  implicit def stringToDecimal(x: String): ValuteValue2Decimal[String] = instance(x) {
    value => BigDecimal(value.replace(",", "."))
  }
}

