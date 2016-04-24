package models

import org.specs2.mutable.Specification

class TaskSpec extends Specification {

  "Tasks" >> {

    "Echo" >> {
      "should concatenate the input string" >> {
        Echo("echoTask").run("blah") mustEqual "blah blah"
      }
    }

    "Reverse" >> {
      "should reverse the input string" >> {
        Reverse("reverseTask").run("blah") mustEqual "halb"
      }
    }

    "Delay" >> {
      "should add delay to the input string" >> {
        Delay("delayTask").run("blah") mustEqual "tbb blah"
      }
    }

    "NoOp" >> {
      "should return input string as-is" >> {
        NoOp("noopTask").run("blah") mustEqual "blah"
      }
    }
  }

  "Compound Task" >> {

    "should perform multiple tasks in sequence" >> {
      val tasks = Seq(Echo("echo"), Reverse("reverse"), Delay("delay"))
      CompoundTasks(tasks: _*).run("test") mustEqual "test test tset tbb test"
    }
  }
}
