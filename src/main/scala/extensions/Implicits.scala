package extensions

object Implicits {

  implicit class RichSet[T](val set: scala.collection.mutable.Set[T]) extends AnyVal {
    def findAndGet(predicate: T => Boolean) = set.find(predicate).get
  }
}
