package utils

import constants.ElementPatterns
import exceptions.NetworkElementParseException
import models._

class NetworkElementsParser {

  def parse(line: String): Element = line.trim match {
    case ElementPatterns.Task(name, operation) => operation match {
      case ElementPatterns.Echo()     => Echo(name)
      case ElementPatterns.Reverse()  => Reverse(name)
      case ElementPatterns.Delay()    => Delay(name)
      case ElementPatterns.NoOp()     => NoOp(name)
      case others                     => throw NetworkElementParseException("[Error]: malformed task specification")
    }
    case ElementPatterns.Link(upstreamTaskName, downstreamTaskName) => Link(upstreamTaskName, downstreamTaskName)
    case ElementPatterns.Process(inputStream) => Process(inputStream.split("\\s+").toStream)
    case others => throw NetworkElementParseException("[Error]: malformed element specification")
  }
}
