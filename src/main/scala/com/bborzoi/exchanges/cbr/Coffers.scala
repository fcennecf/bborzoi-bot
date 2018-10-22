package com.bborzoi.exchanges.cbr

import java.util.Date


case class Currency(
                     name: String,
                     isoCode: Int,
                     isoName: String,
                     nominal: Int,
                     course: BigDecimal
                   )

case class ExchangeCourses(
                            requested: Date,
                            quotations: List[Currency]
                          )
