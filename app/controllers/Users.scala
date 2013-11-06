package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.User


object Users extends Controller {
    
    val userForm = Form(
        mapping(
            "id" -> number,
            "name" -> text,
            "email" -> text
        )(User.apply)(User.unapply)
    )
    
    def index = Action {
        Ok(views.html.user(User.all(), userForm))
    }
    
    def post = Action{ implicit request =>
        var data:User = userForm.bindFromRequest.get
        Ok(data.name)
    }
    
}