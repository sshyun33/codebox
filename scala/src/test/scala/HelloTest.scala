import org.scalatest.FunSuite

class HelloTest extends FunSuite {

  test("Hello Scala Test") {

    val msg = "Hello, Scala~!!!"
    assert(msg == "Hello, Scala~!!!")
  }
}

