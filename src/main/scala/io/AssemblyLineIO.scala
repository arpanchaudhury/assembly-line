package io

import java.io.File

import utils.CommandParser

class AssemblyLineIO(inputFile: File, commandParser: CommandParser) {
  def getCommandsIterator = scala.io.Source.fromFile(inputFile).getLines().map(commandParser.parse)
}
