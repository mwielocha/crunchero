package controllers

import play.api.mvc.{Action, Controller}

/**
 * Created by mwielocha on 06.07.2014.
 */
trait AnalyticsController {
  self: Controller =>

  import app.Global.applicationContext._

  def analyze = Action {

    analyticsService.analyze

    Ok
  }


}

object AnalyticsController extends Controller with AnalyticsController
