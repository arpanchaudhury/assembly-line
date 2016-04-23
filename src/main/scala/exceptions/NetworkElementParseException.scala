package exceptions

case class NetworkElementParseException(message: String) extends Throwable {
  override def getMessage = message
}
