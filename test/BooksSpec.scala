import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._
import models.Book


@RunWith(classOf[JUnitRunner])
class BooksSpec extends Specification {

  "Books" should {

    "render the list of books" in new WithApplication{
      val books = route(FakeRequest(GET, "/books")).get

      status(books) must equalTo(OK)
      contentType(books) must beSome.which(_ == "application/json")
      contentAsString(books) must contain (Book.books.head.name)
      contentAsString(books) must contain (Book.books.last.name)
    }

    "render the requested book" in new WithApplication{
      val books = route(FakeRequest(GET, s"/books/${Book.books.head.id}")).get

      status(books) must equalTo(OK)
      contentType(books) must beSome.which(_ == "application/json")
      contentAsString(books) must contain (Book.books.head.name)
      contentAsString(books) must not contain (Book.books.last.name)
    }

    "delete the requested book" in new WithApplication{
      Book.books.length must equalTo(2)
      val books = route(FakeRequest(DELETE, s"/books/${Book.books.head.id}")).get

      status(books) must equalTo(OK)
      contentType(books) must beSome.which(_ == "application/json")
      contentAsString(books) must contain ("OK")
      Book.books.length must equalTo(1)
    }

  }
}
