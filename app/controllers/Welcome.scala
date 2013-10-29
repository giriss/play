package controllers

import play.api._
import play.api.mvc._

object Welcome extends Controller {

  def index = Action {
    Ok(views.html.form)
  }
  
  def formSubmit = Action {
    DynamicForm params = form.bindFromRequest
    Ok()
  }

}
