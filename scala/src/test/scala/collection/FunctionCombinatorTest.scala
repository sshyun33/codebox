package collection

import org.scalatest.{BeforeAndAfter, FunSuite}

class FunctionCombinatorTest extends FunSuite with BeforeAndAfter {

  var numbers: List[Int] = _

  before {
    numbers = List(1, 2, 3, 4, 5)
  }

  test("map") {
    val listTimesTwo = numbers.map((i: Int) => i * 2)
    assert(listTimesTwo == List(2, 4, 6, 8, 10))

    def timesTwo(i: Int) = i * 2

    assert(numbers.map(timesTwo) == List(2, 4, 6, 8, 10))
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

  test("find") {
    // 앞에서부터 조건에 가장 먼저 일치되는 원소 반환
    val result01: Option[Int] = numbers.find(_ > 2)
    assert(result01.getOrElse(-1) == 3)

    val result02: Option[Int] = numbers.find(_ > 10)
    assert(result02.getOrElse(-1) == -1)
  }

  test("drop or dropWile") {
    // 앞에서 3개의 원소 삭제
    assert(numbers.drop(3) == List(4, 5))
    // 앞에서부터 조건을 만족하지 않는 원소가 나올 때까지 삭제
    assert(numbers.dropWhile(_ % 2 != 0) == List(2, 3, 4, 5))
  }

  test("foldLeft") {
    val result = numbers.foldLeft(1)((x, y) => 2 * x * y)
    assert(result == 3840)
  }
}