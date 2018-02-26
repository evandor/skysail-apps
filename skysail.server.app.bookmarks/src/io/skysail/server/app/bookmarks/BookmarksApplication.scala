package io.skysail.server.app.bookmarks

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Directive.addByNameNullaryApply
import akka.http.scaladsl.server.Directives.{getFromResourceDirectory, pathPrefix}
import akka.http.scaladsl.server.PathMatcher._segmentStringToPathMatcher
import akka.http.scaladsl.server.PathMatchers._
import akka.http.scaladsl.server.{PathMatcher, Route}
import io.skysail.api.persistence.DbService
import io.skysail.domain.routes.RouteMapping
import io.skysail.server.RoutesCreatorTrait
import io.skysail.server.app.bookmarks.domain.HttpResource
import io.skysail.server.app.bookmarks.repository.BookmarksRepository
import io.skysail.server.app.bookmarks.resources.{BookmarkResource, BookmarksResource, PostBookmarkResource, PutBookmarkResource}
import io.skysail.server.app.bookmarks.services.{BookmarkSchedulerService, EventService}
import io.skysail.server.app.{ApplicationProvider, BackendApplication}
import org.osgi.framework.BundleContext
import org.osgi.service.event.EventAdmin

import scala.concurrent.ExecutionContextExecutor

class BookmarksApplication(
  bundleContext: BundleContext,
  dbService: DbService,
  system: ActorSystem,
  routesCreator: RoutesCreatorTrait) extends BackendApplication(bundleContext, routesCreator, system) with ApplicationProvider {

  var eventService: EventService = new EventService(null)

  def setEventAdmin(eventAdmin: EventAdmin) = {
    eventService = new EventService(eventAdmin)
  }

  val repo = new BookmarksRepository(dbService, appModel)

  appModel.addEntity(classOf[HttpResource])

  BookmarkSchedulerService.checkBookmarks(system)

  override def name = "bookmarks"
  override def desc = "Skysail Bookmarks Application"

  override def routesMappings = {
    val root: PathMatcher[Unit] = PathMatcher("bookmarks") / PathMatcher("v1")
    List(
      RouteMapping("", root ~ PathEnd, classOf[BookmarksResource]),

      RouteMapping("/bms", root / PathMatcher("bms") ~ PathEnd, classOf[BookmarksResource]),
      RouteMapping("/bms/", root / PathMatcher("bms") / PathEnd, classOf[PostBookmarkResource]),
      RouteMapping("/bms/:id", root / PathMatcher("bms") / Segment ~ PathEnd, classOf[BookmarkResource]),
      RouteMapping("/bms/:id/", root / PathMatcher("bms") / Segment / PathEnd, classOf[PutBookmarkResource])
    )

  }

  override def nativeRoute(): Route = {
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher
    pathPrefix("") {
      akka.http.scaladsl.server.Directives.get {
        getFromResourceDirectory("assets", this.getClass.getClassLoader)
      }
    }
  }

}