package io.skysail.server.app.bundlerepo.resources

import akka.actor.ActorSystem
import io.skysail.api.osgi.bundlerepository.domain.{Repository, Resource}
import io.skysail.domain.{RequestEvent, ResponseEventBase}
import io.skysail.domain.resources.EntityResource
import io.skysail.server.app.bundlerepo.ObrApplication

case class ReposResource() extends EntityResource[ObrApplication, Repository] {

  //override def getEntity(requestEvent: RequestEvent) = getApplication().repoService.listRepos
  override def getEntity(re: RequestEvent): Option[Repository] = ???

  override def get(requestEvent: RequestEvent): ResponseEventBase = ???

  override def put(requestEvent: RequestEvent)(implicit system: ActorSystem): Unit = ???
}

case class ResourcesResource() extends EntityResource[ObrApplication, Resource] {

  //override def getList(requestEvent: RequestEvent) = getApplication().repoService.list("")
  override def getEntity(re: RequestEvent): Option[Resource] = ???

  override def get(requestEvent: RequestEvent): ResponseEventBase = ???
  override def put(requestEvent: RequestEvent)(implicit system: ActorSystem): Unit = ???
}
