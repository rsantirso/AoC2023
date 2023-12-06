package aoc.wait.forit

import scala.io.Source
import scala.util.{Failure, Success, Try}


@main def WaitForIt(): Unit =

  println("################################################################################")
  println("#                  Advent of Code 2023 - Wait For It                           #")
  println("################################################################################")
  println("")

  //288
  println(s" * Test 01: product of number of ways to beat the record: ${calculateWaysToWin("input_test.txt").product}.")



  def calculateWaysToWin(input: String): Array[Int] =
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
        for t: String <- times
            d: String <- distances
          yield Race(t.toInt, d.toInt)

      resource.close
      Success(races)
    } catch {
      case error: Throwable => Failure(error)
    }