package aoc.gear.ratios

import scala.io.Source
import scala.util.control.Breaks.{break, breakable}

@main def GearRatios(): Unit =

  println("################################################################################")
  println("#                  Advent of Code 2023 - Gear Ratios                           #")
  println("################################################################################")
  println("")


  var data: Array[Array[Char]] = Array.empty
  val resource = Source.fromResource("input_test.txt")
  for (l <- resource.getLines)
    data :+= l.toCharArray

  //I feel better adding this
  if data.length == 0 || data(0).length == 0 then return

  val rows = data.length
  val cols = data(0).length

  var partsSum = 0

  for (r <- 0 until rows)
    var isDigit = false
    var number = 0
    var isPartNumber = false

    for (c <- 0 until cols)
      if data(r)(c).isDigit then
        if (isDigit) then
          number = number * 10 + data(r)(c).toString.toInt
        else
          number = data(r)(c).toString.toInt

        isDigit = true
        isPartNumber = belongsToPart(data, r, c)

      else if isDigit then
        //number ends, add it?
        if isPartNumber then
          println("Number " + number + " is part of the engine")
          partsSum += number;
        else
          println("Number " + number + " is not part of the engine")

        isDigit = false
        isPartNumber = false

  println("Total sum of parts is " + partsSum)


def belongsToPart(data: Array[Array[Char]], r: Int, c: Int): Boolean =
  val targetPositions: Array[(Int, Int)] = getSurroundingCoordinates(data.length, data(0).length, r, c)
  targetPositions.map(getValue(data, _)).exists(isSymbol)

def getValue(data: Array[Array[Char]], xy: (Int, Int)): Char =
  data(xy(0))(xy(1))

def getSurroundingCoordinates(totalRows: Int, totalCols: Int, r: Int, c: Int): Array[(Int, Int)] =
  var array: Array[(Int, Int)] = Array.empty
  array

def isSymbol(value: Char): Boolean =
  !value.isDigit && value != '.'