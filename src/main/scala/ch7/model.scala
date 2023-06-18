package io.github.vcvitaly.grokkingfp
package ch7

/**
 * module.
 *
 * @author Vitalii Chura
 */
object model {

  enum MusicGenre {
    case HeavyMetal
    case Pop
    case HardRock
  }

  enum SearchCondition {
    case SearchByGenre(genres: List[MusicGenre])
    case SearchByOrigin(locations: List[Location])
    case SearchByActiveYears(start: Int, end: Int)
    case SearchByNumberOfActiveYears(numberOfYearsActive: Int, currentYear: Int)
  }

  opaque type Location = String
  object Location {
    def apply(value: String): Location = value
    extension(a: Location) def name: String = a
  }

  enum YearsActive {
    case StillActive(since: Int)
    case ActiveBetween(start: Int, end: Int)
  }

  case class Artist(
                     name: String,
                     genre: MusicGenre,
                     origin: Location,
                     yearsActive: List[YearsActive]
                   )
}
