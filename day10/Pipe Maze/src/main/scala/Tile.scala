package aoc.pipe.maze

enum Connection:
  case N,E,S,W
  
case class Tile(a: Connection, b: Connection)
