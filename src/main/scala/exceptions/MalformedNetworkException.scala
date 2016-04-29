package exceptions

case class MalformedNetworkException(message: String) extends Throwable {
  override def getMessage = message
}
