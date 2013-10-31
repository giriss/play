package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }
  
  def task = Action {
    Ok(views.html.task(Task.all(), taskForm))
  }
  
  def newTask = TODO
  
  def deleteTask(id : Long) = TODO

}
