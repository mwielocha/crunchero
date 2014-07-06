package controllers

import play.api.mvc.{Action, Controller}

/**
 * author mikwie
 *
 */
trait MaintenanceController {
  self: Controller =>

}

object MaintenanceController extends Controller with MaintenanceController
