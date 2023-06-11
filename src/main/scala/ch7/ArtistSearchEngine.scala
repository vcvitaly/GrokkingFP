package io.github.vcvitaly.grokkingfp
package ch7

/**
 * ArtistSearchEngine.
 *
 * @author Vitalii Chura
 */
class ArtistSearchEngine {

  def searchArtists(
                     artists: List[Artist],
                     genres: List[String],
                     locations: List[String],
                     searchByActiveYears: Boolean,
                     activeAfter: Int,
                     activeBefore: Int
                   ): List[Artist] = {
    artists
      .filter(a => filterByGenres(a.genre, genres))
      .filter(a => filterByLocations(a.origin, locations))
      .filter(a => !searchByActiveYears ||
        filterByYearsActive(a.yearsActiveStart, a.yearsActiveEnd, activeAfter, activeBefore)
      )
  }

  private def filterByGenres(genre: String, expectedGenres: List[String]): Boolean = {
    expectedGenres.isEmpty || expectedGenres.contains(genre)
  }

  private def filterByLocations(location: String, expectedLocations: List[String]): Boolean = {
    expectedLocations.isEmpty || expectedLocations.contains(location)
  }

  private def filterByYearsActive(yearsActiveStart: Int, yearsActiveEnd: Int, activeAfter: Int, activeBefore: Int): Boolean = {
    (activeAfter <= yearsActiveStart && activeBefore >= yearsActiveStart) ||
      (activeAfter >= yearsActiveStart && (activeBefore <= yearsActiveEnd || yearsActiveEnd == 0)) ||
      (activeAfter <= yearsActiveEnd && activeBefore >= yearsActiveEnd)
  }
}
