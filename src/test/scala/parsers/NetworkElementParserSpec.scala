package parsers

import exceptions.CommandParseException
import models.{Delay, Echo, NoOp, Reverse}
import org.specs2.mutable.Specification

class NetworkElementParserSpec extends Specification {

  "Network Element Parser" >> {

    val networkElementParser = new NetworkElementParser

    "should parse echo task" >> {
      networkElementParser.parse("task copy_cat echo") mustEqual Echo("copy_cat")
    }

    "should parse reverse task" >> {
      networkElementParser.parse("task flipper reverse") mustEqual Reverse("flipper")
    }

    "should parse echo task" >> {
      networkElementParser.parse("task lagger delay") mustEqual Delay("lagger")
    }

    "should parse echo task" >> {
      networkElementParser.parse("task agnostic noop") mustEqual NoOp("agnostic")
    }

    "should throw exception for malformed task" >> {
      networkElementParser.parse("task agnostic noob") must throwA[CommandParseException]
    }

    "should throw exception for malformed element" >> {
      networkElementParser.parse("malloc builder allocate") must throwA[CommandParseException]
    }
  }
}
