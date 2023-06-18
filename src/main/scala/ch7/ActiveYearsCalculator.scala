package io.github.vcvitaly.grokkingfp
package ch7

import ch7.model.Artist

import io.github.vcvitaly.grokkingfp.ch7.model.YearsActive.{ActiveBetween, StillActive}

/**
 * ActiveYearCalculator.
 *
 * @author Vitalii Chura
 */
class ActiveYearsCalculator {

  def numberOfYearsActive(artist: Artist, currentYear: Int): Int = {
    val yearsActive = artist.yearsActive match
      case StillActive(since) => currentYear - since
      case ActiveBetween(start, end) => end - start
    
    if (yearsActive < 0) 0 else yearsActive
  } 
}
