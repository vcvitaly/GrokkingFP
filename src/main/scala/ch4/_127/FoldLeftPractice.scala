package io.github.vcvitaly.grokkingfp
package ch4._127

/**
 * FoldLeftPractice.
 *
 * @author Vitalii Chura
 */
object FoldLeftPractice {

  def main(args: Array[String]): Unit = {
    println(
      List(5, 1, 2, 4, 100).foldLeft(0)((sum, i) => sum + i)
    )
    println(
      List("scala", "rust", "ada").foldLeft(0)((charCount, word) => charCount + word.length)
    )
    println(
      List("scala", "haskell", "rust", "ada")
        .foldLeft(0)((countOfS, word) => countOfS + word.split("").count(c => c == "s"))
    )
    println(
      List(5, 1, 2, 4, 15).foldLeft(Int.MinValue)((max, i) => if (i > max) i else max)
    )
  }

}
