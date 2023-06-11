package io.github.vcvitaly.grokkingfp
package ch7

import ch7.model.{Artist, Genre, Location, YearsActiveEnd, YearsActiveStart}

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

  private def filterByGenres(genre: Genre, expectedGenres: List[String]): Boolean = {
    expectedGenres.isEmpty || expectedGenres.contains(genre.name)
  }

  private def filterByLocations(location: Location, expectedLocations: List[String]): Boolean = {
    expectedLocations.isEmpty || expectedLocations.contains(location.name)
  }

  private def filterByYearsActive(yearsActiveStart: YearsActiveStart,
                                  yearsActiveEnd: YearsActiveEnd,
                                  activeAfter: Int,
                                  activeBefore: Int): Boolean = {
    (activeAfter <= yearsActiveStart.value && activeBefore >= yearsActiveStart.value) ||
      (activeAfter >= yearsActiveStart.value && (activeBefore <= yearsActiveEnd.value || yearsActiveEnd.value == 0)) ||
      (activeAfter <= yearsActiveEnd.value && activeBefore >= yearsActiveEnd.value)
  }
}
