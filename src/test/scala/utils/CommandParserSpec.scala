package utils

import exceptions.CommandParseException
import models._
import org.specs2.mutable.Specification

class CommandParserSpec extends Specification {

  "Command Parser" >> {

    val commandParser = new CommandParser

    "should parse echo task" >> {
      commandParser.parse("task copy_cat echo") mustEqual Echo("copy_cat")
    }

    "should parse reverse task" >> {
      commandParser.parse("task flipper reverse") mustEqual Reverse("flipper")
    }

    "should parse delay task" >> {
      commandParser.parse("task slacker delay") mustEqual Delay("slacker")
    }

    "should parse noop task" >> {
      commandParser.parse("task agnostic noop") mustEqual NoOp("agnostic")
    }

    "should throw exception for malformed task" >> {
      commandParser.parse("task agnostic noob") must throwA[CommandParseException]
    }

    "should parse link" >> {
      commandParser.parse("link taskA taskB") mustEqual Link("taskA", "taskB")
    }

    "should parse process" >> {
      commandParser.parse("process input1 input2 input3") mustEqual Process(Stream("input1", "input2", "input3"))
    }

    "should throw exception for malformed element" >> {
      commandParser.parse("malloc builder allocate") must throwA[CommandParseException]
    }
  }
}
