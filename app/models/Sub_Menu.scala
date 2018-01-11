package models

import play.api.Play
import play.api.data.Form
import play.api.data.Forms._
import play.api.db.slick.DatabaseConfigProvider
import scala.concurrent.Future
import slick.driver.JdbcProfile
import slick.driver.MySQLDriver.api._
import scala.concurrent.ExecutionContext.Implicits.global

case class Sub_Menu(subId: Int, subName: String, menuId: Int)


class Sub_MenuTableDef(tag: Tag) extends Table[Sub_Menu](tag, "Sub_Menu") {

  def subId = column[Int]("Sub_Id", O.PrimaryKey, O.AutoInc)

  def subName = column[String]("Sub_Name")

  def menuId = column[Int]("Menu_Id")

  override def * =
    (subId, subName, menuId) <> (Sub_Menu.tupled, Sub_Menu.unapply)
}

object Sub_Menus {

  val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)

  val subMenus = TableQuery[Sub_MenuTableDef]

  def add(itemSubMenu: Sub_Menu): Future[String] = {
    dbConfig.db.run(subMenus += itemSubMenu).map(res => "Sub Menu successfully added").recover {
      case ex: Exception => ex.getCause.getMessage
    }
  }

  def delete(id: Int): Future[Int] = {
    dbConfig.db.run(subMenus.filter(_.subId === id).delete)
  }

  def get(id: Int): Future[Option[Sub_Menu]] = {
    dbConfig.db.run(subMenus.filter(_.subId === id).result.headOption)
  }

  def getSubMenuByMenuId(menuId: Int): Future[Seq[Sub_Menu]] = {
    dbConfig.db.run(subMenus.filter(_.menuId === menuId).result)
  }


  def listAll: Future[Seq[Sub_Menu]] = {
    dbConfig.db.run(subMenus.result)
  }

}
