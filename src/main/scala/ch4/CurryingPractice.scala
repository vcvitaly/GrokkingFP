package io.github.vcvitaly.grokkingfp
package ch4

/**
 * CurryingPractice.
 *
 * @author Vitalii Chura
 */
object CurryingPractice {

  def main(args: Array[String]): Unit = {
    println(largerThan(List(5, 1, 2, 4, 0))(4))
    println(divisibleBy(List(5, 1, 2, 4, 15))(5))
    println(shorterThan(List("scala", "ada"))(4))
    println(containsS(List("rust", "ada"))(2))
  }

  def largerThan(ints: List[Int])(i: Int): List[Int] = {
    ints.filter(anInt => anInt > i)
  }


  def divisibleBy(ints: List[Int])(i: Int): List[Int] = {
    ints.filter(anInt => anInt % 5 == 0)
  }

  def shorterThan(words: List[String])(i: Int): List[String] = {
    words.filter(word => word.length < i)
  }

  def containsS(words: List[String])(i: Int): List[String] = {
    words.filter(word => word.split("").count(c => c == "s") > i)
  }
}
