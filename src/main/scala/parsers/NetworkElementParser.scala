package parsers

import constants.ElementPatterns
import exceptions.CommandParseException
import models.{Delay, Echo, NoOp, Reverse}

class NetworkElementParser {

  def parse(line: String) = line.trim match {
    case ElementPatterns.Task(name, operation)
      =>
        operation match {
          case ElementPatterns.Echo()     => Echo(name)
          case ElementPatterns.Reverse()  => Reverse(name)
          case ElementPatterns.Delay()    => Delay(name)
          case ElementPatterns.NoOp()     => NoOp(name)
          case others                     => throw CommandParseException("[Error] : malformed task specification")
        }
    case others
      =>
        throw CommandParseException("[Error] : malformed element specification")
  }
}
