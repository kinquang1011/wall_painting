package model

import play.api.Play
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile
import slick.driver.MySQLDriver.api._

import scala.concurrent.Future

/**
  * Created by quangctn on 15/12/2017.
  */
case class danhmuc(CodeCatalogy: String, Name: String)

object Danhmuc {
  val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)

  val dm = TableQuery[DanhmucTable]

  def listAll: Future[Seq[danhmuc]] = {
    dbConfig.db.run(dm.result)
  }
  def listAll2: Future[Seq[String]] = {
    val action = sql"select * from danhmuc".as[String]
    dbConfig.db.run(action)
  }
}

class DanhmucTable(tag: Tag) extends Table[danhmuc](tag, "danhmuc") {
  def code = column[String]("CodeCatalogy")

  def name = column[String]("Name")

  override def * = (code, name) <> (danhmuc.tupled, danhmuc.unapply)
}
