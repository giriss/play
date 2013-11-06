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
            "name" -> nonEmptyText(minLength=5, maxLength=55),
            "email" -> email
        )(User.apply)(User.unapply)
    )
    
    def index = Action {
        Ok(views.html.user(User.all(), userForm))
    }
    
    def post = Action{ implicit request =>
        userForm.bindFromRequest.fold(
            errors => BadRequest(views.html.user(User.all(), errors)),
            user => {
                User.create(user.name, user.email)
                Redirect(routes.Users.index)
            }
        )
    }
    
}