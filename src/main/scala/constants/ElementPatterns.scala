package constants

object ElementPatterns {
  val Task = "(?i)task\\s+(\\w+)\\s+(\\w+)".r

  val Echo = "(?i)echo".r
  val Reverse = "(?i)reverse".r
  val Delay = "(?i)delay".r
  val NoOp = "(?i)noop".r
}
