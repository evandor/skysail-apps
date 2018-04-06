package io.skysail.server.app.docker

import akka.actor.ActorSystem
import io.skysail.domain.resources.EntityResource
import io.skysail.domain.{RequestEvent, ResponseEventBase}

class DockerProcessesResource extends EntityResource[DockerApplication, DockerProcessDescriptorList] {

//  override def getList(requestEvent: RequestEvent): List[BundleDescriptor] = {
//    getApplication().getBundles()
//  }
  override def getEntity(re: RequestEvent): Option[DockerProcessDescriptorList] = {
    Some(DockerProcessDescriptorList(getApplication().getBundles()))
  }

  override def get(requestEvent: RequestEvent): ResponseEventBase = ???
  override def put(requestEvent: RequestEvent)(implicit system: ActorSystem): ResponseEventBase = ???

}
