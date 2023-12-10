package aoc.pipe.maze

import scala.io.Source
import scala.util.{Failure, Success, Try}

@main def main(): Unit =

  //8
  println(s" * Test 01: farthest distance in the loop: ${calculateFarthestDistance("input_test.txt")}.")
  //
  println(s" * Puzzle 01: farthest distance in the loop: ${calculateFarthestDistance("input_puzzle.txt")}.")


def calculateFarthestDistance(input: String): Int =
  tryGetData(input) match
    case Failure(exception) => println(s"Failed. Reason: $exception"); 0
    case Success(mace) => getFarthestDistance(mace)


def tryGetData(inputResource: String): Try[Maze] =
  try {
    val resource = Source.fromResource(inputResource)
    val lines = resource.getLines



    resource.close
    Success(Maze(Array.empty))
  } catch {
    case error: Throwable => Failure(error)
  }


def getFarthestDistance(maze: Maze): Int =
  maze.walkLoop / 2