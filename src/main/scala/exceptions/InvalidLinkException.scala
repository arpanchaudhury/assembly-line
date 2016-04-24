package exceptions

case class InvalidLinkException(message: String) extends Throwable {
  override def getMessage = message
}
