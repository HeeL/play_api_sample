import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._


@RunWith(classOf[JUnitRunner])
class BooksSpec extends Specification {

  "Books" should {

    "render the list of books" in new WithApplication{
      val books = route(FakeRequest(GET, "/books")).get

      status(books) must equalTo(OK)
      contentType(books) must beSome.which(_ == "application/json")
      contentAsString(books) must contain ("Title 1")
      contentAsString(books) must contain ("Title 2")
    }
  }
}
