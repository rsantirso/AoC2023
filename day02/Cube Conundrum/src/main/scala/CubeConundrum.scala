package aoc.cube.conundrum

import scala.io.Source

@main def CubeConundrum(): Unit =

  println("#               Advent of Code 2023 - Cube Conundrum                           #")
  println("################################################################################")

  val idsTest: Iterator[Int] = getPossibleGameIds("input_test.txt", 12, 13, 14)
  println(s"  Test 01: 12 red cubes, 13, green cubes, 14 blue cubes => ${idsTest.sum}")

  val idsPuzzle: Iterator[Int] = getPossibleGameIds("input_puzzle.txt", 12, 13, 14)
  println(s"  Puzzle 01: 12 red cubes, 13, green cubes, 14 blue cubes => ${idsPuzzle.sum}")


def getPossibleGameIds(inputFile: String, totalRed: Int, totalGreen: Int, totalBlue: Int): Iterator[Int] =
  val resource = Source.fromResource(inputFile)
  resource.getLines()
    .map(buildGame)
    .filter(isGamePossible(_, totalRed, totalGreen, totalBlue))
    .map(_.id)


def isGamePossible(game: Game, red: Int, green: Int, blue: Int): Boolean =
  game.sets.count(isSetPossible(_, red, green, blue)) == game.sets.length

def isSetPossible(set: SetOfCubes, red: Int, green: Int, blue: Int): Boolean =
  isAvailable(red, set.red) && isAvailable(green, set.green) && isAvailable(blue, set.blue)

def isAvailable(available: Int, requested: Int): Boolean =
  available - requested >= 0
