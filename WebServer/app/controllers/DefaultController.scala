package controllers

/**
  * Created by vivia on 2016-10-04.
  */

import javax.inject._
import play.api._
import play.api.mvc._

class DefaultController @Inject() extends Controller {

  def index = Action {
    Ok(views.html.framework())
  }

}
