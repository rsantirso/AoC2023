package aoc.wait.forit

import scala.io.Source
import scala.util.{Failure, Success, Try}


@main def WaitForIt(): Unit =

  println("################################################################################")
  println("#                  Advent of Code 2023 - Wait For It                           #")
  println("################################################################################")
  println("")

  //288
  println(s" * Test 01: product of number of ways to beat the records: ${calculateWaysToWin("input_test.txt").product}.")
  //58***8
  println(s" * Puzzle 01: product of number of ways to beat the records: ${calculateWaysToWin("input_puzzle.txt").product}.")
  //71503
  println(s" * Test 02: product of number of ways to beat the record: ${calculateWaysToWin("input_test_02.txt").product}.")
  //
  println(s" * Test 02: product of number of ways to beat the record: ${calculateWaysToWin("input_puzzle_02.txt").product}.")


  def calculateWaysToWin(input: String): Array[Long] =
    tryGetData(input) match
      case Failure(exception) => println(s"Failed. Reason: $exception"); Array.empty
      case Success(races) => races.map(r => r.waysToBeatTheRecord)


  def tryGetData(inputResource: String): Try[Array[Race]] =
    try {
      val resource = Source.fromResource(inputResource)
      val lines = resource.getLines

      //I cheat a little bit: don't wanna deal with 'Time' and 'Distance' words, so I
      // have manually edited input files with vim: :%s,\s\+, ,g
      val times = lines.next().split(" ")
      val distances = lines.next().split(" ")

      val races =
        for (t,d ) <- times zip distances
          yield Race(t.toLong, d.toLong)

      resource.close
      Success(races)
    } catch {
      case error: Throwable => Failure(error)
    }