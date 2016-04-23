package io

import org.specs2.mutable.Specification
import java.io.File

import models.{Echo, Link, Reverse, Process}
import org.specs2.mock.Mockito
import utils.CommandParser

class AssemblyLineIOSpec extends Specification with Mockito {

  "Assembly Line IO" >> {

    "should get command iterator from a file" >> {
      val inputFile = new File(getClass.getResource("/inputFile.txt").getPath)
      val commandParser = mock[CommandParser]

      val assemblyLineIO = new AssemblyLineIO(inputFile, commandParser)

      commandParser.parse("task echo echo") returns Echo("echo")
      commandParser.parse("task reverse reverse") returns Reverse("reverse")
      commandParser.parse("link echo reverse") returns Link("echo", "reverse")
      commandParser.parse("process input1 input2") returns Process(Stream("input1", "input2"))

      assemblyLineIO.getCommandsIterator.toList mustEqual List(Echo("echo"), Reverse("reverse"), Link("echo", "reverse"), Process(Stream("input1", "input2")))
    }
  }
}
