package models

case class Task(id : Long, label : String)

object Task {
  
  import play.api.db._
  import play.api.Play.current
  import anorm._
  import anorm.SqlParser._

  val task = {
    get[Long]("id") ~ 
    get[String]("label") map {
      case id~label => Task(id, label)
    }
  }

  def all(): List[Task] = DB.withConnection { implicit c =>
    SQL("select * from task").as(task *)
  }
  
  def create(label : String){
      DB.withConnection { implicit c =>
      SQL("insert into task (label) values ({label})").on(
        'label -> label
      ).executeUpdate()
    } 
  }
  
  def delete(id : Long){
      DB.withConnection { implicit c =>
      SQL("delete from task where id = {id}").on(
        'id -> id
      ).executeUpdate()
    }
  }
  
  def getFirst(id:Int=0, label:String=null) : Task = DB.withConnection { implicit c =>
    var query:String = " where "
    if(id != 0 && label != null){
      query += s"id = '${id}' and label = '${label}'"
    }else if(id != 0){
      query += s"id = '${id}'"
    }else if(label != null){
      query += s"label = '${label}'"
    }else{
      query = ""
    }
    SQL(s"select * from task${query} limit 1").as(task.single)
  }
  
}
