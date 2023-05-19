package io.github.vcvitaly.grokkingfp
package ch4

import scala.collection.immutable.List

/**
 * CurryingPracticeBook.
 *
 * @author Vitalii Chura
 */
object CurryingPracticeBook {

  def main(args: Array[String]): Unit = {
    println(List(5, 1, 2, 4, 0).filter(largerThan(4)))
    println(List(5, 1, 2, 4, 15).filter(divisibleBy(5)))
    println(List("scala", "ada").filter(shorterThan(4)))
    println(List("rust", "ada").filter(containsS(2)))
  }

  def largerThan(n: Int)(i: Int): Boolean = i > n

  def divisibleBy(n: Int)(i: Int): Boolean = i % n == 0

  def shorterThan(n: Int)(word: String): Boolean = word.length < n

  def containsS(moreThan: Int)(word: String): Boolean = word.split("").count(c => c == "s") > moreThan
}
