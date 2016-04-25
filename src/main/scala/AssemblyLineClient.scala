import factory.AssemblyLineServiceFactory

object AssemblyLineClient extends App {
  override def main(args: Array[String]): Unit = {
    val assemblyLineService = (new AssemblyLineServiceFactory).getService(args)
    assemblyLineService.serve()
  }
}
