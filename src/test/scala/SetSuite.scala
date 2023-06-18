package io.github.vcvitaly.grokkingfp

import org.scalatest.funsuite.AnyFunSuite

/**
 * SetSuite.
 *
 * @author Vitalii Chura
 */
class SetSuite extends AnyFunSuite {

  test("An empty Set should have size 0") {
    assert(Set.empty.size == 0)
  }

  test("Invoking head on an empty Set should produce NoSuchElementException") {
    assertThrows[NoSuchElementException] {
      Set.empty.head
    }
  }
}
