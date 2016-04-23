package utils

import constants.CommandPatterns
import exceptions.CommandParseException
import models._

class CommandParser {

  def parse(line: String): Command = line.trim match {
    case CommandPatterns.Task(name, operation) => operation match {
      case CommandPatterns.Echo()     => Echo(name)
      case CommandPatterns.Reverse()  => Reverse(name)
      case CommandPatterns.Delay()    => Delay(name)
      case CommandPatterns.NoOp()     => NoOp(name)
      case others                     => throw CommandParseException("[Error]: malformed task specification")
    }
    case CommandPatterns.Link(upstreamTaskName, downstreamTaskName) => Link(upstreamTaskName, downstreamTaskName)
    case CommandPatterns.Process(inputStream) => Process(inputStream.split("\\s+").toStream)
    case others => throw CommandParseException("[Error]: malformed command specification")
  }
}
