package io.skysail.server.app.bookmarks.resources

import java.util.UUID

import akka.actor.{ActorSelection, ActorSystem}
import akka.http.scaladsl.model.HttpMethods
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import io.skysail.domain.messages.ProcessCommand
import io.skysail.domain.resources.{EntityResource, PostResource, PutResource}
import io.skysail.domain.{RedirectResponseEvent, RequestEvent, ResponseEvent, ResponseEventBase}
import io.skysail.server.app.bookmarks.BookmarksApplication
import io.skysail.server.app.bookmarks.domain.{Bookmark, BookmarkList}
import io.skysail.server.app.bookmarks.services.BookmarksService

class BookmarksResource extends EntityResource[BookmarksApplication, BookmarkList] {
  override def getEntity(re: RequestEvent) = Some(BookmarkList(getApplication().repo.find()))

  override def put(requestEvent: RequestEvent)(implicit system: ActorSystem): Unit = ???
}

class PostBookmarkResource extends PostResource[BookmarksApplication, Bookmark] {

  def get(requestEvent: RequestEvent): ResponseEvent[Bookmark] = {
    ResponseEvent(requestEvent, Bookmark(None, "", ""))
  }

  def post(requestEvent: RequestEvent) {
    var bookmark = requestEvent.cmd.entity.asInstanceOf[Bookmark]
    val bmWithMetadata = BookmarksService.addMetadata(bookmark)
    val b = getApplication().repo.save(bmWithMetadata)
    // getApplication().eventService.send("bookmark created")
    val redirectTo = Some("/bookmarks/v1/bms")
    val newRequest = requestEvent.cmd.ctx.request.copy(method = HttpMethods.GET)
    requestEvent.controllerActor ! RedirectResponseEvent(requestEvent, "", redirectTo)
  }

  override def createRoute(applicationActor: ActorSelection, processCommand: ProcessCommand)(implicit system: ActorSystem): Route = {
    formFieldMap { map =>
      val entity = Bookmark(Some(UUID.randomUUID().toString), map.getOrElse("title", "Unknown"), map.getOrElse("url", "Unknown"))
      super.createRoute(applicationActor, processCommand.copy(entity = entity))
    }
  }
  override def put(requestEvent: RequestEvent)(implicit system: ActorSystem): Unit = ???

}

class PutBookmarkResource extends PutResource[BookmarksApplication, Bookmark] {

  override def get(requestEvent: RequestEvent): ResponseEvent[Bookmark] = {
    val optionalBookmark = getApplication().repo.find(requestEvent.cmd.urlParameter.head)
    ResponseEvent(requestEvent, optionalBookmark.get)
  }

  override def put(requestEvent: RequestEvent)(implicit system: ActorSystem): Unit = {
    val optionalBookmark = getApplication().repo.find(requestEvent.cmd.urlParameter.head)
    val updatedBookmark = requestEvent.cmd.entity.asInstanceOf[Bookmark]
    val bookmarkToSave = updatedBookmark.copy(id = optionalBookmark.get.id)
    getApplication().repo.save(bookmarkToSave)
  }

  override def createRoute(applicationActor: ActorSelection, processCommand: ProcessCommand)(implicit system: ActorSystem): Route = {
    formFieldMap { map =>
      val entity = Bookmark(Some(UUID.randomUUID().toString), map.getOrElse("title", "Unknown"), map.getOrElse("url", "Unknown"))
      super.createRoute(applicationActor, processCommand.copy(entity = entity))
    }
  }

  override def delete(requestEvent: RequestEvent): ResponseEventBase = {
    getApplication().repo.delete(requestEvent.cmd.urlParameter.head)
    RedirectResponseEvent(requestEvent, "", Some("/bookmarks/v1/bms"))
  }

}

class BookmarkResource extends EntityResource[BookmarksApplication, Bookmark] {

  //override def get(requestEvent: RequestEvent) = {
  override def getEntity(re: RequestEvent): Option[Bookmark] = {
    val app: BookmarksApplication = getApplication()
    val optionalBookmark = app.repo.find(re.cmd.urlParameter.head)
    val bm = optionalBookmark.getOrElse(Bookmark(None, "undef", "undef"))
    //    if (bm.url.contains("$")) {
    //      val pattern = new Regex("\\$\\{(.*?)}")
    //      val matchList = (pattern findAllIn bm.url).toList
    //      println((pattern findAllIn bm.url).mkString(","))
    //      val hits = matchList.map(hit => hit.substring(2, hit.length - 1)).map(hit => hit -> app.getList(hit))
    //      println(hits)
    //      val variant1 = new Bookmark(None, "var", "1")
    //
    //      val lists = hits
    //        .map(hit => hit._2.map(sub => bm.url.replace("${" + hit._1 + "}", sub)))
    //        .flatten.toList
    //
    //      val variants = lists.map(l => Bookmark(None, "-", l)).toList
    //
    //
    //      val bmWithVariants = new Bookmark(bm.id, "*" + bm.title + "*", bm.url/*, variants*/)
    //
    //      //ResponseEvent(requestEvent, bmWithVariants)
    //      Some(bmWithVariants)
    //    } else {
    //ResponseEvent(requestEvent, bm)
    Some(bm)
    //}
  }

  override def put(requestEvent: RequestEvent)(implicit system: ActorSystem): Unit = ???




}