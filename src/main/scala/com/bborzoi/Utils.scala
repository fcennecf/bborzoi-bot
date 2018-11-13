package com.bborzoi

import java.time.{LocalDate, ZoneId, ZonedDateTime}

object DateTimeGMT {
  val zoneGreenwichId = ZoneId.of("Etc/Greenwich")

  def nowDate: ZonedDateTime = LocalDate.now.atStartOfDay(zoneGreenwichId)
}
