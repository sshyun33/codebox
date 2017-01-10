package collection

import org.scalatest.FunSuite

class BasicCollectionTest extends FunSuite {

  test("Create a list") {
    val list = List(1, 2, 3, 4)
    assert(list == List(1, 2, 3, 4))
  }

  test("Create a set") {
    val set = Set(1, 2, 3, 4)
    assert(set == Set(1, 2, 3, 4))
  }

  test("Create a tuple") {
    val tuple = ("a", 1)
    assert(tuple == ("a", 1))
    assert(tuple._1 == "a")
    assert(tuple._2 == 1)

    val tuple2 = 1 -> 2
    assert(tuple2 == (1, 2))
  }

  test("Create a map") {
    val map = Map(1 -> "a", 2 -> "b", 3 -> "c")
    assert(map == Map((1, "a"), (2, "b"), (3, "c")))
  }
}
