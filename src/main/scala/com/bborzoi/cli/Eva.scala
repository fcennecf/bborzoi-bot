package com.bborzoi.cli


import org.backuity.clist._


trait Eva {
  this: Command =>
  def run(): Unit
}
