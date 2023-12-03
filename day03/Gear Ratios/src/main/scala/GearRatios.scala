package aoc.gear.ratios

import scala.io.Source
import scala.util.control.Breaks.{break, breakable}

@main def GearRatios(): Unit =

  println("################################################################################")
  println("#                  Advent of Code 2023 - Gear Ratios                           #")
  println("################################################################################")
  println("")

  println(s" * Test 01: total sum of engine parts, ${getSumOfEngineParts("input_test.txt")}")


def getSumOfEngineParts(inputResource: String): Int =
  var data: Array[Array[Char]] = Array.empty
  val resource = Source.fromResource(inputResource)
  for (l <- resource.getLines)
    data :+= l.toCharArray

  //I feel better adding this
  if data.length == 0 || data(0).length == 0 then return -1

  val rows = data.length
  val cols = data(0).length

  var partsSum = 0

  for (r <- 0 until rows)
    var isDigit = false
    var number = 0
    var isPartNumber = false

    for (c <- 0 until cols)
      if data(r)(c).isDigit then
        val currentValue = data(r)(c).toString.toInt
        number =
          if (isDigit) then number * 10 + currentValue
          else currentValue

        isDigit = true
        isPartNumber = isPartNumber || belongsToPart(data, r, c)

      else if isDigit then
        //number ends, add it?
        if isPartNumber then
          partsSum += number;
        else
          println("Number " + number + " is not part of the engine")
        end if

        isDigit = false
        isPartNumber = false
      end if

  partsSum


def belongsToPart(data: Array[Array[Char]], r: Int, c: Int): Boolean =
  getSurroundingCoordinates(r, c)
    .filter(isInBounds(data,_))
    .map(getValue(data, _))
    .exists(isSymbol)

def isInBounds(matrix: Array[Array[Char]], coordinate: (Int, Int)): Boolean =
  coordinate(0) >= 0
    && coordinate(0) < matrix.length
    && coordinate(1) >= 0
    && coordinate(1) < matrix(0).length

def getSurroundingCoordinates(r: Int, c: Int): Array[(Int, Int)] =
  var array: Array[(Int, Int)] = Array.empty
  array :+= (r - 1, c -1)
  array :+= (r - 1, c)
  array :+= (r - 1, c + 1)
  array :+= (r, c - 1)
  array :+= (r, c + 1)
  array :+= (r + 1, c - 1)
  array :+= (r + 1, c)
  array :+= (r + 1, c + 1)
  array

def getValue(data: Array[Array[Char]], xy: (Int, Int)): Char =
  data(xy(0))(xy(1))

def isSymbol(value: Char): Boolean =
  !value.isDigit && value != '.'