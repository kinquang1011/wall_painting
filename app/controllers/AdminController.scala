package controllers

import java.util.Map
import play.api.mvc._
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by quangctn on 07/01/2018.
  */
class AdminController extends Controller {
  def index = Action { request =>
    if (request.session.isEmpty) {
      Ok(views.html.adminLogin())
    } else {
      val user = request.session.get("nhukom")
      Ok("Hello")
    }
  }

  def logout = Action { request =>
    Ok(views.html.adminLogin()).withSession(request.session - ("nhukom"))
  }

  def index2 = Action {
    Ok(views.html.adminLogin())
  }

  def login = Action { implicit request =>
    val gameInfo = request.body.asFormUrlEncoded
    val userName = gameInfo.get("userName").apply(0)
    val passWord = gameInfo.get("password").apply(0)
    if (userName.equalsIgnoreCase("nhukom") && passWord.equalsIgnoreCase("kinquang")) {
      Redirect(routes.AdminController.index()).withSession(request.session + ("nhukom" -> "yes"))
    } else {
      Redirect(routes.AdminController.index())
    }
  }

  def showData = Action { implicit request =>
    Ok(views.html.adminLogin())

  }
}
