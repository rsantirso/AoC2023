package aoc.pipe.maze

case class Maze(tiles: Array[Array[Tile]]):
  
  def walkLoop: Int =
    0