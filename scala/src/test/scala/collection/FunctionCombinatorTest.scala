package collection

import org.scalatest.{BeforeAndAfter, FunSuite}

class FunctionCombinatorTest extends FunSuite with BeforeAndAfter {

  var numbers: List[Int] = _

  before {
    numbers = List(1, 2, 3, 4)
  }

  test("map") {
    val listTimesTwo = numbers.map((i: Int) => i * 2)
    assert(listTimesTwo == List(2, 4, 6, 8))

    def timesTwo(i: Int) = i * 2
    assert(numbers.map(timesTwo) == List(2, 4, 6, 8))
  }

  test("foreach") {
    def printElement(i: Int) = print(i + " ")
    print("show element by foreach: ")
    numbers.foreach(printElement)
  }

  test("filter") {
    val evenNumbers = numbers.filter(_ % 2 == 0)
    assert(evenNumbers == List(2, 4))
  }

  test("zip") {
    val zipped = List(1, 2, 3, 4).zip(List("a", "b", "c", "d"))
    assert(zipped == List((1, "a"), (2, "b"), (3, "c"), (4, "d")))
  }

  test("partition") {
    val partitioned = List(1, 2, 3, 4, 5, 6).partition(_ % 2 == 0)
    assert(partitioned == (List(2, 4, 6), List(1, 3, 5)))
  }
}