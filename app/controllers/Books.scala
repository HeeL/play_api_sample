package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._
import models.Book

class Books extends Controller {

  def index = Action {
    Ok(Json.toJson(Book.books))
  }

  def show(id: Int) = Action {
    Ok(Json.toJson(Book.find(id)))
  }

  def create = Action(BodyParsers.parse.json) { request =>
    val b = request.body.validate[Book.Book]
    b.fold(
      errors => {
        BadRequest(Json.obj("status" -> "OK", "message" -> JsError.toJson(errors)))
      },
      book => {
        Book.create(book)
        Ok(Json.obj("status" -> "OK"))
      }
    )
  }

  def delete(id: Int) = Action {
    Book.delete(id)
    Ok(Json.obj("status" -> "OK"))
  }

}
