package factory

import java.io.File

import io.AssemblyLineIO
import services.AssemblyLineService
import utils.CommandParser

class AssemblyLineServiceFactory {
  def getService(inputFile: File) = {
    val commandParser = new CommandParser
    val assemblyLineIO = new AssemblyLineIO(inputFile, commandParser)
    new AssemblyLineService(assemblyLineIO)
  }
}
