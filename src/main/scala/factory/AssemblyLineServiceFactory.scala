package factory

import java.io.File

import constants.UserMassages
import exceptions.ArgumentMissingException
import io.AssemblyLineIO
import services.AssemblyLineService
import utils.CommandParser

class AssemblyLineServiceFactory {
  def getService(args: Array[String]) = {
    if (args.length == 0) throw ArgumentMissingException("[Error]: You haven't provided input file path. Usage: run <input file path>")
    else {
      val inputFile = new File(args(0))
      val commandParser = new CommandParser
      val assemblyLineIO = new AssemblyLineIO(inputFile, commandParser)
      new AssemblyLineService(assemblyLineIO)
    }
  }
}
