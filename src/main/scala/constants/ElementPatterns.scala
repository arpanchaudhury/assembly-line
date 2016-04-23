package constants

object ElementPatterns {
  val Echo    = "(?i)echo".r
  val NoOp    = "(?i)noop".r
  val Delay   = "(?i)delay".r
  val Reverse = "(?i)reverse".r

  val Task    = "(?i)task\\s+(\\w+)\\s+(\\w+)".r
  val Link    = "(?i)link\\s+(\\w+)\\s+(\\w+)".r
  val Process = "(?i)process\\s+(.*)".r
}
