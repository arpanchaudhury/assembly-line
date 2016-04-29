package services

import constants.UserMassages
import io.AssemblyLineIO
import models.{Element, Network, Process}

class AssemblyLineService(assemblyLineIO: AssemblyLineIO) {
  val network = new Network

  def serve() = {
    assemblyLineIO.printLine(UserMassages.WelcomeMessage(assemblyLineIO.inputFile))
    val commandsIterator = assemblyLineIO.getCommandsIterator
    while(commandsIterator.hasNext) {
      commandsIterator.next match {
        case element: Element => network.add(element)
        case process: Process => assemblyLineIO.printLine(UserMassages.InputMessage(process.inputStream.mkString(" | ")))
                                 assemblyLineIO.printLine(UserMassages.OutputMessage(network.process(process.inputStream).mkString(" | ")))
      }
    }
    assemblyLineIO.printLine(UserMassages.ExitMessage)
  }
}
