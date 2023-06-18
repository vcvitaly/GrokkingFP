package io.github.vcvitaly.grokkingfp
package ch7

import ch7.model.YearsActive.{ActiveBetween, StillActive}
import ch7.model.{Artist, YearsActive}

/**
 * ActiveYearCalculator.
 *
 * @author Vitalii Chura
 */
class ActiveYearsCalculator {

  def numberOfYearsActive(artist: Artist, currentYear: Int): Int = {
    artist.yearsActive
      .map(period => numberOfYearsActiveInASinglePeriod(period, currentYear))
      .sum
  }

  private def numberOfYearsActiveInASinglePeriod(period: YearsActive, currentYear: Int): Int = {
    val yearsActive = period match
      case StillActive(since) => currentYear - since
      case ActiveBetween(start, end) => end - start

    if (yearsActive < 0) 0 else yearsActive
  }
}
