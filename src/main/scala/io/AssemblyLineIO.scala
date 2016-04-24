package io

import java.io.File

import utils.CommandParser

class AssemblyLineIO(val inputFile: File, commandParser: CommandParser) {
  def getCommandsIterator = scala.io.Source.fromFile(inputFile).getLines().map(commandParser.parse)

  def printLine(line: String) = println(line)
}
