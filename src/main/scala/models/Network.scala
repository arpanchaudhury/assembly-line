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

  def process(inputStream: Stream[String]) = {
    inputStream.map(tasks.iterator.foldLeft(_)((inputString, task) => task.run(inputString)))
  }

  private def tasks = {
    var lastNodeCopy = lastNode
    var taskList = List(lastNodeCopy.task)

    while (lastNodeCopy.parents.nonEmpty) {
      val parents = lastNodeCopy.parents
      lastNodeCopy = parents.head
      taskList = CompoundTasks(parents.map(_.task).toSeq: _*) :: taskList
    }

    taskList
  }

  private def taskNodesExists(taskNames: String*) = taskNames.forall(name => nodes.exists(_.task.name == name))

  case class Node(task: Task) {
    val parents = mutable.Set.empty[Node]

    def addParent(node: Node) = parents.add(node)
  }
}