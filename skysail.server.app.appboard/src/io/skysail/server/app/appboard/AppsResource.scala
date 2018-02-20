package io.skysail.server.app.appboard

import akka.actor.ActorSystem
import io.skysail.domain.{RequestEvent, ResponseEventBase}
import io.skysail.domain.app.{Application, ApplicationList}
import io.skysail.domain.resources.EntityResource
import org.slf4j.LoggerFactory

import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future}

class AppsResource() extends EntityResource[AppboardApplication, ApplicationList] {

  private val log = LoggerFactory.getLogger(this.getClass)

  override def getEntity(re: RequestEvent): Option[ApplicationList] = {
    val appService = getApplication().appService
    val apps: Future[List[Application]] = appService.getAllApplications(this.actorContext.system)

    val r = Await.result(apps, 1.seconds)
    Some(ApplicationList(r))
  }

  override def delete(requestEvent: RequestEvent): ResponseEventBase = null

  override def put(requestEvent: RequestEvent)(implicit system: ActorSystem): Unit = ???
}