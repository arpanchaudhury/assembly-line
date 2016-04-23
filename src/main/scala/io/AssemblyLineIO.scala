package io

import java.io.File

import utils.NetworkElementsParser

class AssemblyLineIO(inputFile: File, networkElementsParser: NetworkElementsParser) {
  def getElementsIterator = scala.io.Source.fromFile(inputFile).getLines().map(networkElementsParser.parse)
}
