package models

case class User(id:Int, name:String, email:String)

object User {

  import play.api.db._
  import play.api.Play.current
  import anorm._
  import anorm.SqlParser._
  
  val user = {
    get[Int]("id") ~ get[String]("name") ~ get[String]("email") map{ case id~name~email => User(id, name, email) }
  }
  
  def all() : List[User] = DB.withConnection { implicit c =>
    SQL("select * from users").as(user *)
  }
  
  def create(name:String, email:String) {
      DB.withConnection{ implicit c =>
          SQL("insert into users (name, email) values ({name}, {email})").on(
              'name -> name,
              'email -> email
          ).executeUpdate()
      }
  }
  
}
