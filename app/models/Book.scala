package models

import play.api.libs.json.Json

object Book {

  case class Book(id: Int, name: String, author: String)

  implicit val bookWrites = Json.writes[Book]
  implicit val bookReads = Json.reads[Book]

  var books = List(Book(1, "Title 1", "Author 1"), Book(2, "Title 2", "Author 2"))

  def find(id: Int) = books.filter(_.id == id).head
}