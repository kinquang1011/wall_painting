package services


import model._

import scala.concurrent.Future

/**
  * Created by quangctn on 15/12/2017.
  */
object DanhmucService {
  def listAll(): Future[Seq[String]] = {
    Danhmuc.listAll2
  }

}
