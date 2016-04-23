package models

case class Link(upstreamTaskName: String, downstreamTaskName: String) extends Command
