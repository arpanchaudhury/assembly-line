import java.io.File

import constants.UserMassages
import factory.AssemblyLineServiceFactory

object AssemblyLineClient extends App {

  override def main(args: Array[String]): Unit = {
    if (args.length == 0) println(UserMassages.ArgumentMissingMessage)
    else {
      val inputFile = new File(args(0))
      val assemblyLineService = (new AssemblyLineServiceFactory).getService(inputFile)
      assemblyLineService.serve()
    }
  }
}
