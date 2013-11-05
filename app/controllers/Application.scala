package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.Task


object Application extends Controller {
  val taskForm = Form(
    "label" -> nonEmptyText
  )

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }
  
  def json = Action {
    import play.api.libs.json._
    Ok(JsObject(
      Seq(
        "message" -> JsString("It works !!")
      )
    ))
  }
  
  def task = Action {
    Ok(views.html.task(Task.all(), taskForm))
  }
  
  def newTask = Action { implicit request =>
    taskForm.bindFromRequest.fold(
      errors => BadRequest(views.html.task(Task.all(), errors)),
      label => {
        Task.create(label)
        Redirect(routes.Application.task)
      }
    )
  }
  
  def deleteTask(id : Long) = Action {
    Task.delete(id)
    Redirect(routes.Application.task)
  }
  
  def find = Action {
    Ok(views.html.find(Task.getFirst(label = "Hi...")))
  }

}
