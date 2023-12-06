package aoc.wait.forit

case class Race(time: Int, distance: Int):

  def waysToBeatTheRecord: Int =
    (1 until time)
      .map(t => (time - t) * t)
      .count(d => d > distance)