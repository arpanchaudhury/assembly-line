package models

import exceptions.InvalidLinkException
import extensions.Implicits._

import scala.collection.mutable

class Network {
  private var lastNode: Node = null
  val nodes = mutable.Set[Node]()

  def add(element: Element): Unit = element match {
    case task: Task =>
      val node = Node(task)
      if (nodes.isEmpty) lastNode = node
      nodes.add(node)

    case link: Link if taskNodesExists(link.upstreamTaskName, link.downstreamTaskName) =>
      val upstreamNode   = nodes.findAndGet(_.task.name == link.upstreamTaskName)
      val downStreamNode = nodes.findAndGet(_.task.name == link.downstreamTaskName)
      downStreamNode.addParent(upstreamNode)
      if (lastNode == upstreamNode) lastNode = downStreamNode

    case link: Link =>
      throw InvalidLinkException("[Error]: tasks to be linked not found")
  }

  def process(inputStream: Stream[String]) = inputStream.map(getNodeOutput(_, lastNode))

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