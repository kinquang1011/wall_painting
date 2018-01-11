package services

import models._

import scala.concurrent.Future

object MenuService {

  def addUser(menu: Menu): Future[String] = {
    Menus.add(menu)
  }

  def deleteUser(id: Int): Future[Int] = {
    Menus.delete(id)
  }

  def getUser(id: Int): Future[Option[Menu]] = {
    Menus.get(id)
  }


  def listAllUsers: Future[Seq[Menu]] = {
    Menus.listAll
  }
  def fullMenu: Future[Seq[Menu]] = {
    Menus.listAll
  }
}
