package io.skysail.server.app.osgi.resources

import akka.actor.ActorSystem
import io.skysail.domain.resources.EntityResource
import io.skysail.domain.{RequestEvent, ResponseEventBase}
import io.skysail.server.app.osgi.OsgiApplication
import io.skysail.server.app.osgi.domain.BundleDescriptorList

class BundlesResource extends EntityResource[OsgiApplication, BundleDescriptorList] {

//  override def getList(requestEvent: RequestEvent): List[BundleDescriptor] = {
//    getApplication().getBundles()
//  }
  override def getEntity(re: RequestEvent): Option[BundleDescriptorList] = {
    Some(BundleDescriptorList(getApplication().getBundles()))
  }

  override def get(requestEvent: RequestEvent): ResponseEventBase = ???
  override def put(requestEvent: RequestEvent)(implicit system: ActorSystem): Unit = ???

}
