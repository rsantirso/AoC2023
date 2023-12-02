package aoc.cube.conundrum

import scala.io.Source

val INPUT_TEST_FILE = "input_test.txt"
val INPUT_PUZZLE_FILE = "input_puzzle.txt"
val AVAILABLE_RED = 12
val AVAILABLE_GREEN = 13
val AVAILABLE_BLUE = 14

@main def CubeConundrum(): Unit =

  println("################################################################################")
  println("#               Advent of Code 2023 - Cube Conundrum                           #")
  println("################################################################################")
  println("")

  val idsTest: Iterator[Int] = getPossibleGameIds(INPUT_TEST_FILE, AVAILABLE_RED, AVAILABLE_GREEN, AVAILABLE_BLUE)
  println(s" * Test 01: $AVAILABLE_RED red cubes, $AVAILABLE_GREEN green cubes, $AVAILABLE_BLUE blue cubes => ${idsTest.sum}")

  val idsPuzzle: Iterator[Int] = getPossibleGameIds(INPUT_PUZZLE_FILE, AVAILABLE_RED, AVAILABLE_GREEN, AVAILABLE_BLUE)
  println(s" * Puzzle 01: $AVAILABLE_RED red cubes, $AVAILABLE_GREEN green cubes, $AVAILABLE_BLUE blue cubes => ${idsPuzzle.sum}")

  val minPowersTest = buildGamesFromFile(INPUT_TEST_FILE).map(getMinimumPower)
  println(s" * Test 02: sum of the power of minimum set of powers => ${minPowersTest.sum}")

  val minPowersPuzzle = buildGamesFromFile(INPUT_PUZZLE_FILE).map(getMinimumPower)
  println(s" * Puzzle 02: sum of the power of minimum set of powers => ${minPowersPuzzle.sum}")


def getMinimumPower(game: Game): Int =
  var minRed = 1
  var minGreen = 1
  var minBlue = 1

  for (combination <- game.sets)
    minRed = if combination.red != 0 then Math.max(minRed, combination.red) else minRed
    minGreen = if combination.green != 0 then Math.max(minGreen, combination.green) else minGreen
    minBlue = if combination.blue != 0 then Math.max(minBlue, combination.blue) else minBlue

  minRed * minGreen * minBlue


def getPossibleGameIds(inputFile: String, totalRed: Int, totalGreen: Int, totalBlue: Int): Iterator[Int] =
  buildGamesFromFile(inputFile)
    .filter(isGamePossible(_, totalRed, totalGreen, totalBlue))
    .map(_.id)


def buildGamesFromFile(inputFile: String): Iterator[Game] =
  val resource = Source.fromResource(inputFile)
  resource.getLines()
    .map(buildGame)


def isGamePossible(game: Game, red: Int, green: Int, blue: Int): Boolean =
  game.sets.count(isSetPossible(_, red, green, blue)) == game.sets.length


def isSetPossible(set: SetOfCubes, red: Int, green: Int, blue: Int): Boolean =
  isAvailable(red, set.red) && isAvailable(green, set.green) && isAvailable(blue, set.blue)


def isAvailable(available: Int, requested: Int): Boolean =
  available - requested >= 0
