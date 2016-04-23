package utils

import exceptions.NetworkElementParseException
import models._
import org.specs2.mutable.Specification

class NetworkElementsParserSpec extends Specification {

  "Network Elements Parser" >> {

    val networkElementsParser = new NetworkElementsParser

    "should parse echo task" >> {
      networkElementsParser.parse("task copy_cat echo") mustEqual Echo("copy_cat")
    }

    "should parse reverse task" >> {
      networkElementsParser.parse("task flipper reverse") mustEqual Reverse("flipper")
    }

    "should parse echo task" >> {
      networkElementsParser.parse("task lagger delay") mustEqual Delay("lagger")
    }

    "should parse noop task" >> {
      networkElementsParser.parse("task agnostic noop") mustEqual NoOp("agnostic")
    }

    "should throw exception for malformed task" >> {
      networkElementsParser.parse("task agnostic noob") must throwA[NetworkElementParseException]
    }

    "should parse link" >> {
      networkElementsParser.parse("link taskA taskB") mustEqual Link("taskA", "taskB")
    }

    "should parse process" >> {
      networkElementsParser.parse("process input1 input2 input3") mustEqual Process(Stream("input1", "input2", "input3"))
    }

    "should throw exception for malformed element" >> {
      networkElementsParser.parse("malloc builder allocate") must throwA[NetworkElementParseException]
    }
  }
}
