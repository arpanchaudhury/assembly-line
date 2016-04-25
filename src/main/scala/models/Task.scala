package models

trait Task extends Command with Element {
  def name: String
  def run(input: String): String
}

case class Echo(name: String) extends Task {
  def run(input: String) = s"$input $input"

  override def toString = s"Echo($name)"
}

case class Reverse(name: String) extends Task {
  def run(input: String) = input.reverse

  override def toString = s"Reverse($name)"
}

case class Delay(name: String) extends Task {
  def run(input: String) = s"tbb $input"

  override def toString = s"Delay($name)"
}

case class NoOp(name: String) extends Task {
  def run(input: String) = input

  override def toString = s"NoOp($name)"
}
