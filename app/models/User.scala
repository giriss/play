package models

case class User(id:Int, name:String, email:String)

object User {

  import play.api.db._
  import play.api.Play.current
  import anorm._
  import anorm.SqlParser._
  
  val user = {
    get[Int]("id") ~ get[String]("name") ~ get[String]("email") map( case id~name~email => User(id, name, email) )
  }
  
  def all() : List[User] = {
    SQL("select * from users").as(user *)
  }
}
