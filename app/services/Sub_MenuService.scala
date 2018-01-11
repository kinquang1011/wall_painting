package services

import models._

import scala.concurrent.Future

/**
  * Created by quangctn on 18/12/2017.
  */
object Sub_MenuService {
  def add(itemSub: Sub_Menu): Future[String] = {
    Sub_Menus.add(itemSub)
  }

  def delete(id: Int): Future[Int] = {
    Sub_Menus.delete(id)
  }

  def get(id: Int): Future[Option[Sub_Menu]] = {
    val subMenu = Sub_Menus.get(id)
    subMenu
  }

  def getSubMenuByMenuId(menuId: Int): Future[Seq[Sub_Menu]] = {
    Sub_Menus.getSubMenuByMenuId(menuId)

  }


  def listAll: Future[Seq[Sub_Menu]] = {
    Sub_Menus.listAll
  }

}
