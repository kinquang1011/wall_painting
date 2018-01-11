package models

import play.api.Play
import play.api.data.Form
import play.api.data.Forms._
import play.api.db.slick.DatabaseConfigProvider
import scala.concurrent.Future
import slick.driver.JdbcProfile
import slick.driver.MySQLDriver.api._
import scala.concurrent.ExecutionContext.Implicits.global

case class Menu(id: Int, name: String)

case class MenuFull()

class MenuTableDef(tag: Tag) extends Table[Menu](tag, "Menu") {

  def id = column[Int]("Menu_Id", O.PrimaryKey, O.AutoInc)

  def name = column[String]("Name")

  override def * =
    (id, name) <> (Menu.tupled, Menu.unapply)
}

object Menus {

  val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)

  val menus = TableQuery[MenuTableDef]

  def add(itemMenu: Menu): Future[String] = {
    dbConfig.db.run(menus += itemMenu).map(res => "Menu successfully added").recover {
      case ex: Exception => ex.getCause.getMessage
    }
  }

  def delete(id: Int): Future[Int] = {
    dbConfig.db.run(menus.filter(_.id === id).delete)
  }

  def get(id: Int): Future[Option[Menu]] = {
    dbConfig.db.run(menus.filter(_.id === id).result.headOption)
  }

  def listAll: Future[Seq[Menu]] = {
    dbConfig.db.run(menus.result)
  }

  def listFull: Future[Seq[(String, String)]] = {
    val action = sql"SELECT Menu.Name, Sub_Menu.Sub_Name  FROM `Menu` left join Sub_Menu on Menu.`Menu_Id` = Sub_Menu.Menu_Id"
      .as[(String, String)]
    dbConfig.db.run(action)
  }


}
