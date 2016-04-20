package models

trait Task {
  def run(input: String): String
}

case class Echo(name: String) extends Task {
  def run(input: String) = input + input
}

case class Reverse(name: String) extends Task {
  def run(input: String) = input.reverse
}

case class Delay(name: String) extends Task {
  def run(input: String) = s"tbb $input"
}

case class NoOp(name: String) extends Task {
  def run(input: String) = input
}
