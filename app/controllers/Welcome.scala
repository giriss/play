package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

object Welcome extends Controller {
  
  var form = Form(
    tuple(
      "name" -> text,
      "password" -> text
      )
    )

  def index = Action {
    Ok(views.html.form())
  }
  
  def formSubmit = Action { implicit request =>
    var param = form.bindFromRequest
    Ok(param._name)
  }

}
