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

}
