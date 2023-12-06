package aoc.wait.forit

case class Race(time: Long, distance: Long):

  def waysToBeatTheRecord: Long =
    (1L until time)
      .map(t => (time - t) * t)
      .count(d => d > distance)