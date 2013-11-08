package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.data.validation._
import models.User


object Users extends Controller{

    val nonEmptyCheck: Constraint[String] = Constraint("Required")({
        plainText =>
            val errors = plainText match {
                case "" => Seq(ValidationError("Cannot be empty"))
                case _ => Nil
            }
        if (errors.isEmpty) {
            Valid
        } else {
            Invalid(errors)
        }
    })

    val nonEmptyEmail : Mapping[String] = email.verifying(nonEmptyCheck)
    
    val userForm = Form(
        mapping(
            "id" -> ignored(0),
            "name" -> nonEmptyText(maxLength=60),
            "email" -> nonEmptyEmail
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