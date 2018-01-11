package controllers

import models.Sub_Menu
import play.api.mvc._
import services.{MenuService, Sub_MenuService}

import scala.collection.mutable
import scala.concurrent.ExecutionContext.Implicits.global

import scala.collection._

class HomeController extends Controller {

  /*def index = Action.async { implicit request =>
    UserService.listAllUsers map { users =>
      Ok(views.html.index(UserForm.form, users))
    }
  }*/

  def index = Action.async { implicit request =>
    var lstSubMenu = scala.collection.mutable.Map[Int, Seq[Sub_Menu]]()

    MenuService.listAllUsers map { lstMenu =>
      for (menu <- lstMenu) {
        Sub_MenuService.getSubMenuByMenuId(menu.id) map { lstsubMenu =>
          lstSubMenu += (menu.id -> lstsubMenu)

        }
      }
      Ok(views.html.layout(lstMenu, lstSubMenu))
    }
  }

  def index2(menuId: Int) = Action {
    Ok(views.html.test(menuId, "abc"))
  }

  def getSubsMenuByMenuId(menuId: Int) = Action.async { implicit request =>
    Sub_MenuService.getSubMenuByMenuId(menuId) map { subMenu =>
      Ok(views.html.subMenu(subMenu))
    }
  }

  def getContentSub() = Action.async { implicit request =>
    System.out.print(request.path)
    MenuService.listAllUsers map { lstMenu =>

      Ok(views.html.page(lstMenu))
    }
  }

}

