package models

import exceptions.{InvalidLinkException, MalformedNetworkException}
import extensions.Implicits._

import scala.collection.mutable

class Network {
  val nodes = mutable.Set[Node]()

  def add(element: Element): Unit = element match {
    case task: Task =>
      val node = Node(task)
      nodes.add(node)
    case link: Link if taskNodesExists(link.upstreamTaskName, link.downstreamTaskName) =>
      val upstreamNode   = nodes.findAndGet(_.task.name == link.upstreamTaskName)
      val downStreamNode = nodes.findAndGet(_.task.name == link.downstreamTaskName)
      downStreamNode.addParent(upstreamNode)
    case link: Link =>
      throw InvalidLinkException("[Error]: tasks to be linked not found")
  }

  def process(inputStream: Stream[String]) = inputStream.map(getNodeOutput(_, lastNode))

  private def lastNode = nodes.size match {
    case 0 => throw MalformedNetworkException("[Error]: No task added to the network")
    case 1 => nodes.head
    case _ => val upstreamNodes = nodes.flatMap(_.parents)
      val lastNode = nodes.diff(upstreamNodes).filter(_.parents.nonEmpty)
      if (lastNode.size == 1) lastNode.head
      else throw MalformedNetworkException("[Error]: Network is not correctly formed")
  }

  private def getNodeOutput(input: String, node: Node): String = node.parents match {
    case parents if parents.isEmpty => node.task.run(input)
    case parents                    => node.task.run(parents.map(getNodeOutput(input, _)).mkString(" "))
  }

  private def taskNodesExists(taskNames: String*) = taskNames.forall(name => nodes.exists(_.task.name == name))

  case class Node(task: Task) {
    val parents = mutable.Set.empty[Node]

    def addParent(node: Node) = parents.add(node)
  }
}