package controllers

import play.api._
import play.api.mvc._

object Welcome extends Controller {

  def index = Action {
    Ok("Hello Scala!!")
  }
  
  def formSubmit = Action {
    
  }

}
