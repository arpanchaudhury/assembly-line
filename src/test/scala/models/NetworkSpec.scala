package models

import exceptions.InvalidLinkException
import org.specs2.mutable.Specification

class NetworkSpec extends Specification {

  "Network" >> {
    val echo = Echo("echo")
    val noop = NoOp("noop")
    val delay = Delay("delay")
    val reverse = Reverse("reverse")

    "should throw InvalidLinkException when link is malformed" >> {
      val network = new Network()

      network.add(echo)

      network.add(Link(echo.name, reverse.name)) must throwA[InvalidLinkException]
    }

    "should process input stream" >> {
      val network = new Network()
      val inputStream = Stream("input1", "input2")

      network.add(echo)
      network.add(reverse)
      network.add(delay)
      network.add(Link(echo.name, reverse.name))
      network.add(Link(reverse.name, delay.name))

      network.process(inputStream).toList mustEqual List("tbb 1tupni 1tupni", "tbb 2tupni 2tupni")
    }

    "should process input stream in complex network" >> {
      val network = new Network()
      val inputStream = Stream("input1", "input2")

      network.add(echo)
      network.add(reverse)
      network.add(noop)
      network.add(delay)

      network.add(Link(echo.name, noop.name))
      network.add(Link(echo.name, reverse.name))
      network.add(Link(reverse.name, delay.name))
      network.add(Link(noop.name, delay.name))

      network.process(inputStream).toList mustEqual List("tbb input1 input1 1tupni 1tupni", "tbb input2 input2 2tupni 2tupni")
    }

    "should process input stream in more complex network" >> {
      val network = new Network()
      val inputStream = Stream("input")

      val echo1 = Echo("echo1")
      val noop1 = NoOp("noop1")
      val delay1 = Delay("delay1")
      val reverse1 = Reverse("reverse1")
      val echo2 = Echo("echo2")
      val delay2 = Delay("delay2")

      network.add(noop1)
      network.add(reverse1)
      network.add(echo1)
      network.add(echo2)
      network.add(delay1)
      network.add(delay2)

      network.add(Link(echo1.name, noop1.name))
      network.add(Link(noop1.name, delay1.name))
      network.add(Link(delay1.name, delay2.name))
      network.add(Link(echo1.name, reverse1.name))
      network.add(Link(reverse1.name, echo2.name))
      network.add(Link(echo2.name, delay2.name))

      network.process(inputStream).toList mustEqual List("tbb tbb input input tupni tupni tupni tupni")
    }.pendingUntilFixed
  }
}
