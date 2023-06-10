package io.github.vcvitaly.grokkingfp
package ch6

/**
 * TvShowAttributesParser.
 *
 * @author Vitalii Chura
 */
class TvShowAttributesParser {

  def extractYearStart(rawShow: String): Either[String, Int] = {
    val bracketOpen = rawShow.indexOf('(')
    val dash = rawShow.indexOf('-')
    for {
      yearStr <- if (bracketOpen != -1 && dash > bracketOpen + 1)
        Right(rawShow.substring(bracketOpen + 1, dash))
      else Left(s"Can't extarct start year from $rawShow")
      year <- yearStr.toIntOption.toRight(s"Can't parse $yearStr")
    } yield year
  }

  def extractYearEnd(rawShow: String): Either[String, Int] = {
    val dash = rawShow.indexOf('-')
    val bracketClose = rawShow.indexOf(')')
    for {
      yearStr <- if (dash != -1 && bracketClose > dash + 1)
        Right(rawShow.substring(dash + 1, bracketClose))
      else Left(s"Can't extract year end from $rawShow")
      year <- yearStr.toIntOption.toRight(s"Can't parse $yearStr")
    } yield year
  }

  def extractSingleYear(rawShow: String): Either[String, Int] = {
    val dash = rawShow.indexOf('-')
    val bracketOpen = rawShow.indexOf('(')
    val bracketClose = rawShow.indexOf(')')
    for {
      yearStr <- if (dash == -1 && bracketOpen != -1 && bracketClose > bracketOpen + 1)
        Right(rawShow.substring(bracketOpen + 1, bracketClose))
      else Left(s"Can't extract single year from $rawShow")
      year <- yearStr.toIntOption.toRight(s"Can't parse $yearStr")
    } yield year
  }

  def extractName(rawShow: String): Either[String, String] = {
    val bracketOpen = rawShow.indexOf('(')
    if (bracketOpen > 1) Right(rawShow.substring(0, bracketOpen).trim)
    else Left(s"Can't extract name from $rawShow")
  }
}
