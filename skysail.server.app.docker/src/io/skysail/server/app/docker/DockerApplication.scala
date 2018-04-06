package io.skysail.server.app.docker

import akka.actor.ActorSystem
import akka.http.scaladsl.server.PathMatcher
import akka.http.scaladsl.server.PathMatchers._
import io.skysail.domain.routes.RouteMapping
import io.skysail.server.RoutesCreatorTrait
import io.skysail.server.app.docker.domain.DockerProcessDescriptor
import io.skysail.server.app.{ApplicationProvider, BackendApplication}
import org.osgi.framework.BundleContext

class DockerApplication(bundleContext: BundleContext, routesCreator: RoutesCreatorTrait, system: ActorSystem) extends BackendApplication(bundleContext, routesCreator, system) with ApplicationProvider {

  override def name = "docker"

  override def desc = "Docker Introspection Application"

  val dockerClient = DockerClientBuilder.getInstance().build()

  override def routesMappings = {
    val root: PathMatcher[Unit] = PathMatcher(name) / PathMatcher("v1")
    List(
      RouteMapping("", root ~ PathEnd, classOf[DockerProcessesResource]),
      RouteMapping("/processes", root / PathMatcher("processes") ~ PathEnd, classOf[DockerProcessesResource])
    )
  }

  def getBundles(): List[DockerProcessDescriptor] = {
    val bundles = bundleContext.getBundles().toList
    bundles.map(DockerProcessDescriptor(_))
  }

}