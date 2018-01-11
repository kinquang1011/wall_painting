package services

import models.Sub_Menu

/**
  * Created by quangctn on 21/12/2017.
  */
object Util {

  import java.text.Normalizer
  import java.util.regex.Pattern

  def removeAccent(s: String): String = {
    val temp = Normalizer.normalize(s, Normalizer.Form.NFD)
    val pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+")
    pattern.matcher(temp).replaceAll("").trim.replace(" ", "").toLowerCase
  }

}
