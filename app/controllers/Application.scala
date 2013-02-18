package controllers

import play.api.data.Forms._
import play.api.data._
import play.api.mvc._
import play.api._
import views._
import com.salutr.declinator.DeclinationService
import play.api.libs.iteratee.Enumerator
import play.api.libs.json.Json.toJson

object Application extends Controller {

  val declineForm = Form(
    "name" -> text
  )

  // -- Actions

  def index = Action {
    Ok(html.index(declineForm, "", ""))
  }

  def declineToVocative = Action { implicit request =>
    declineForm.bindFromRequest.fold(
      formWithErrors => BadRequest(html.index(formWithErrors, "", "")),
      {
        case (name) => {
          val vocative = new DeclinationService().declineToVocative(name)
          Ok(html.index(declineForm.fill(name), name, vocative))
        }
      }
    )
  }

  def declineToVocativeJson(name: String) = Action { request =>
      val vocative = new DeclinationService().declineToVocative(name)
      Ok(toJson(Map("vocative" -> vocative)))
  }
}
