package extensions

import extensions.Implicits.RichSet
import org.specs2.mutable.Specification

import scala.collection.mutable

class RichSetSpec extends Specification {
  "Rich Set" >> {

    "should find and get an element" >> {
      val set = mutable.Set("element 1", "element 2", "element 3")

      set.findAndGet(_.endsWith("1")) mustEqual "element 1"
    }
  }
}
