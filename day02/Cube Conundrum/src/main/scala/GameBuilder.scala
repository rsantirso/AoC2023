package aoc.cube.conundrum


//input: 'Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green'
def buildGame(input: String): Game =
  val mainSplit: Array[String] = input.split(":")

  Game(getId(mainSplit(0)), getSetsOfCubes(mainSplit(1)))

//input: 'Game 1'
private def getId(input: String): Int =
  input.split(" ")(1).toInt

//input: ' 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green'
private def getSetsOfCubes(input: String): Array[SetOfCubes] =
  var sets = Array.empty[SetOfCubes]
  val setsSplit: Array[String] = input.split(";")

  for (combination <- setsSplit)
    sets :+= createSet(combination)

  sets

//input: ' 3 blue, 14 red'
private def createSet(input: String): SetOfCubes =
  SetOfCubes(
    getColour(input, "red"),
    getColour(input, "green"),
    getColour(input, "blue"))

private def getColour(input: String, colour: String): Int =
  val index: Int = input.indexOf(colour)
  if index == -1 then return 0

  val uds: Int = input(index - 2).toString.toInt
  val dec: Int =
    if input(index - 3) != ' ' then input(index - 3).toString.toInt * 10
    else 0

  dec + uds

