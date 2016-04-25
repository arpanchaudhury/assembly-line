package exceptions

case class ArgumentMissingException(message: String) extends Throwable {
  override def getMessage = message
}
