package controllers

import play.api._
import play.api.mvc._
import play.api.data.Forms._
import models.Task

val taskForm = Form(
  "label" -> nonEmptyText
)

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }
  
  def task = Action {
    Ok(views.html.task(Task.all(), taskForm))
  }
  
  def newTask = Action { implicit request =>
    taskForm.bindFromRequest()
  }
  
  def deleteTask(id : Long) = TODO

}
