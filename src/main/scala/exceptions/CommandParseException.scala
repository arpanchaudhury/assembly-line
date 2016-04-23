package exceptions

case class CommandParseException(message: String) extends Throwable {
  override def getMessage = message
}
