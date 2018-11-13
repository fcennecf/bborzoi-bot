package com.bborzoi.exchanges.cbr

import java.time.ZonedDateTime


case class Currency(
                     name: String,
                     isoCode: Int,
                     isoName: String,
                     nominal: Int,
                     course: BigDecimal
                   )

case class ExchangeCourses(
                            requested: ZonedDateTime,
                            quotations: List[Currency]
                          )
