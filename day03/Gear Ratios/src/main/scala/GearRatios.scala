package aoc.gear.ratios

import scala.io.Source
import scala.util.{Failure, Success, Try}
import scala.util.control.Breaks.{break, breakable}

@main def GearRatios(): Unit =

  println("################################################################################")
  println("#                  Advent of Code 2023 - Gear Ratios                           #")
  println("################################################################################")
  println("")

  //4361
  println(s" * Test 01: total sum of engine parts, ${getSumOfEngineParts("input_test.txt")}")
  //5***46
  println(s" * Puzzle 01: total sum of engine parts, ${getSumOfEngineParts("input_puzzle.txt")}")



def getSumOfEngineParts(inputResource: String): Int =
  tryGetData(inputResource) match
    case Failure(exception) => println(s"Failed. Reason: $exception"); 0
    case Success(value) => calcSumOfEngineParts(value)



def tryGetData(inputResource: String): Try[Array[Array[Char]]] =
  var data: Array[Array[Char]] = Array.empty
  val resource = Source.fromResource(inputResource)
  for (l <- resource.getLines)
    data :+= l.toCharArray
  resource.close

  if data.length == 0 || data(0).length == 0 then Failure(new RuntimeException("Input is not a matrix"))
  else Success(data)



def calcSumOfEngineParts(data: Array[Array[Char]]): Int =
  var partsSum = 0

  for (r <- data.indices)
    partsSum = calcRowSumOfEngineParts(data, r, partsSum)

  partsSum



def calcRowSumOfEngineParts(data: Array[Array[Char]], row: Int, prevPartsSum: Int): Int =
  var isDigit = false
  var number = 0
  var isPartNumber = false
  var partsSum = prevPartsSum

  for (c <- data(0).indices)
    if data(row)(c).isDigit then
      val currentValue = data(row)(c).toString.toInt
      number =
        if (isDigit) then number * 10 + currentValue
        else currentValue

      isDigit = true
      isPartNumber = isPartNumber || belongsToPart(data, row, c)

    else if isDigit then
      //number ends, add it?
      partsSum =
        if isPartNumber then partsSum + number
        else partsSum

      isDigit = false
      isPartNumber = false
    end if
  end for

  if isDigit then
  //number ends, add it?
    partsSum =
      if isPartNumber then partsSum + number
      else partsSum
  end if

  partsSum



def belongsToPart(data: Array[Array[Char]], r: Int, c: Int): Boolean =
  getSurroundingCoordinates(r, c)
    .filter(isInBounds(data,_))
    .map(coordinate => data(coordinate(0))(coordinate(1)))
    .exists(isSymbol)



def isInBounds(matrix: Array[Array[Char]], coordinate: (Int, Int)): Boolean =
  (matrix.indices contains coordinate(0)) && (matrix(0).indices contains coordinate(1))



def getSurroundingCoordinates(r: Int, c: Int): Array[(Int, Int)] =
  Array(
    (r - 1, c -1), (r - 1, c), (r - 1, c + 1),
    (r, c - 1), (r, c + 1),
    (r + 1, c - 1), (r + 1, c), (r + 1, c + 1)
  )



def isSymbol(value: Char): Boolean =
  !value.isDigit && value != '.'