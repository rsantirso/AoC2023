package aoc.scratchcards

import scala.collection.immutable.Set

case class Scratchcard(winningNumbers: Set[Int], haveNumbers: Set[Int]):

  def matchingNumbers: Array[Int] =
    haveNumbers.intersect(winningNumbers).toArray


object Scratchcard:
  //Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
  def apply(line: String): Scratchcard =
    val numbers = line.split(":")(1).split("\\|")
    val win = toSet(numbers(0))
    val have = toSet(numbers(1))
    Scratchcard(win, have)

  private def toSet(numbers: String): Set[Int] =
    numbers
      .split(" ")
      .filter(s => !s.isBlank)
      .map(s => s.toInt)
      .toSet
