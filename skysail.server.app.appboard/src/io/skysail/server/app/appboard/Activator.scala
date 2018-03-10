package io.skysail.server.app.appboard

import akka.actor.ActorSystem
import domino.DominoActivator
import io.skysail.server.RoutesCreatorTrait
import io.skysail.server.app.ApplicationProvider
import org.slf4j.LoggerFactory

class Activator extends DominoActivator {

  private var log = LoggerFactory.getLogger(this.getClass)

  var app: AppboardApplication = _
  //var logServiceTracker: ServiceTracker[LogService,LogService] = _

  whenBundleActive {

    whenServicesPresent[RoutesCreatorTrait, ActorSystem] { (routesCreator, actorSystem) =>
      log info s"dbService available in ${this.getClass.getName}"
      app = new AppboardApplication(bundleContext, routesCreator, actorSystem)
      app.providesService[ApplicationProvider]
    }


  }
}
