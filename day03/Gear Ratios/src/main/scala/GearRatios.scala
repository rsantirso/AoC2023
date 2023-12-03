package aoc.gear.ratios

import scala.io.Source
import scala.util.control.Breaks.{break, breakable}

@main def GearRatios(): Unit =

  println("################################################################################")
  println("#                  Advent of Code 2023 - Gear Ratios                           #")
  println("################################################################################")
  println("")


  var data = Array.empty[Array[Char]]
  val resource = Source.fromResource("input_test.txt")
  for (l <- resource.getLines)
    data :+= l.toCharArray

  //I feel better adding this
  if data.length == 0 || data(0).length == 0 then return

  val rows = data.length
  val cols = data(0).length

  var isDigit = false
  var number = 0
  for (r <- 0 until rows)

    for (c <- 0 until cols)
      if data(r)(c).isDigit then
        if (isDigit) then
          number = number * 10 + data(r)(c).toString.toInt
        else
          number = data(r)(c).toString.toInt

        isDigit = true
      else if isDigit then
        //number ends, add it?
        println("Number found " + number)
        isDigit = false




  println("aa")