package aoc.scratchcards

import scala.collection.immutable.Set
import scala.io.Source
import scala.util.{Failure, Success, Try}



@main def ScratchcardsWorth(): Unit =

  println("################################################################################")
  println("#                  Advent of Code 2023 - Scratchcards                           #")
  println("################################################################################")
  println("")

  //13
  println(s" * Test 01: pile of scratchcards is worth ${calculateScratchcardPoints("input_test.txt")} points.")
  //2***4
  println(s" * Puzzle 01: pile of scratchcards is worth ${calculateScratchcardPoints("input_puzzle.txt")} points.")
  //30
  println(s" * Test 01: pile of scratchcards has ${calculateNumberOfScratchcards("input_test.txt")} cards.")
  //64***79
  println(s" * Puzzle 01: pile of scratchcards has ${calculateNumberOfScratchcards("input_puzzle.txt")} cards.")


def calculateScratchcardPoints(inputResource: String): Int = {
  tryGetData(inputResource) match
    case Failure(exception) => println(s"Failed. Reason: $exception"); 0
    case Success(cards) => calculateCardsWorth(cards)
}


def tryGetData(inputResource: String): Try[Array[Scratchcard]] =
  try {
    val resource = Source.fromResource(inputResource)
    var cards: Array[Scratchcard] = Array.empty
    for (l <- resource.getLines)
      cards :+= Scratchcard(l)
    resource.close
    Success(cards)
  } catch {
    case error: Throwable => Failure(error)
  }


def calculateCardsWorth(cards: Array[Scratchcard]): Int =
  var worth = 0
  for(card <- cards)
    worth = worth + Math.pow(2, card.matchingNumbers.length - 1).toInt
  worth


def calculateNumberOfScratchcards(inputResource: String): Int =
  tryGetData(inputResource) match
    case Failure(exception) => println(s"Failed. Reason: $exception"); 0
    case Success(cards) => calculateCardsNumber(cards)


def calculateCardsNumber(cards: Array[Scratchcard]): Int =
  val cardsHits = Array.fill(cards.length) { 0 }
  for(card <- cards)
    val howManyMatches = card.matchingNumbers.length

    cardsHits(card.order - 1) += 1
    val howManyInstance = 1 to cardsHits(card.order - 1)
    howManyInstance.foreach(_ =>
      for (winCopy <- card.order until card.order + howManyMatches)
        cardsHits(winCopy) += 1
    )

  cardsHits.sum