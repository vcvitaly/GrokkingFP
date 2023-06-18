package io.github.vcvitaly.grokkingfp

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should

/**
 * SampleTest.
 *
 * @author Vitalii Chura
 */
class SampleTest extends AnyFunSuite {
  val input = List((1, 1), (4, 2), (9, 3))

  input.foreach { i =>
    test(s"Test of math.sqrt(${i._1})") {
      assert(math.sqrt(i._1) === i._2)
    }
  }
}
