package io.skysail.server.app.bookmarks.resources

import akka.actor.{ActorSystem, Props}
import akka.http.scaladsl.model.HttpRequest
import akka.http.scaladsl.server.RequestContext
import akka.testkit.TestKit
import io.skysail.api.persistence.DbService
import io.skysail.domain.RequestEvent
import io.skysail.domain.messages.ProcessCommand
import io.skysail.server.actors.ControllerActor
import io.skysail.server.app.bookmarks.BookmarksApplication
import io.skysail.server.app.bookmarks.domain.{Bookmark, BookmarkList}
import org.assertj.core.api.Assertions._
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito._
import org.scalatest._
import org.scalatest.junit.JUnitRunner

/**
  * Unit Tests for all Bookmark Resources, calling the overridden methods and
  * mocking the dbService.
  */
@RunWith(classOf[JUnitRunner])
class BookmarksResourceTest(_system: ActorSystem)
  extends TestKit(_system) //with org.scalatest.FunSuite with BeforeAndAfterEach {
    with Matchers
    with FlatSpecLike
    with BeforeAndAfterAll
    with BeforeAndAfterEach {

  val re: RequestEvent = new RequestEvent(null, null)
  val dbService: DbService = Mockito.mock(classOf[DbService])
  val app: BookmarksApplication = new BookmarksApplication(null, dbService, null, null)

  when(dbService.findGraphs(
    classOf[Bookmark],
    "SELECT * from io_skysail_server_app_bookmarks_domain_Bookmark"))
    .thenReturn(List(Bookmark(Some("id"), "title", "url")))

  when(dbService.findGraphs(
    classOf[Bookmark],
    "SELECT * from io_skysail_server_app_bookmarks_domain_Bookmark where id='abc'"))
    .thenReturn(List(Bookmark(Some("abc"), "title", "url")))

  var bmr: BookmarkResource = null
  var bmsr: BookmarksResource = null
  var postBmr: PostBookmarkResource = null
  var putBmr: PutBookmarkResource = null

  def this() = this(ActorSystem("AkkaQuickstartSpec"))

  override def afterAll: Unit = {
    shutdown(system)
  }

  override def beforeEach() = {
    bmr = new BookmarkResource()
    bmr.setApplication(app)
    bmsr = new BookmarksResource()
    bmsr.setApplication(app)
    postBmr = new PostBookmarkResource()
    postBmr.setApplication(app)
    putBmr = new PutBookmarkResource()
    putBmr.setApplication(app)
  }

  // --- BookmarksResource ---

  "the listResource" should "return data from the repository" in {
    val res: BookmarkList = bmsr.getEntity(re).get //.iterator.asJava
    //assertThat(res.bookmarks).containsOnlyOnce(Bookmark(Some("id"), "title", "url"))
    assertThat(res.bookmarks(0)).isEqualTo(Bookmark(Some("id"), "title", "url"))
  }

  // --- PostBookmarkResource ---

  "a get request on postResource" should "return default bookmark entity" in {
    val res = postBmr.get(re)
    assertThat(res.entity.id).isEqualTo(None)
    assertThat(res.entity.title).isEqualTo("")
  }

  "a post request on postResource" should "return nonNull response event" in {
    val p: Props = Props.apply(classOf[ControllerActor])
    val ca = system.actorOf(p)
    var ctx = Mockito.mock(classOf[RequestContext])
    var request = HttpRequest()
    when(ctx.request).thenReturn(request)
    val entity = Bookmark(Some("id"), "title", "url")
    val re: RequestEvent = new RequestEvent(ProcessCommand(ctx, null, null, null, null, entity), ca)
    val res: Unit = postBmr.post(re)
    verify(dbService).persist(entity, app.appModel)
  }

  // --- PutBookmarkResource ---

  "a get request on putResource" should "return nonNull response event" in {
    val parameter: scala.List[_root_.scala.Predef.String] = List("abc")
    val re: RequestEvent = new RequestEvent(ProcessCommand(null, null, null, parameter, null, "entity"), null)
    assertThat(putBmr.get(re)).isNotNull
  }

  "a put request on putResource" should "return nonNull response event" in {
    var bookmark = Bookmark(Some("abc"), "new", "value")
    val ca = system.actorOf(Props.apply(classOf[ControllerActor]))
    val re: RequestEvent = new RequestEvent(ProcessCommand(null, null, null, List("abc"), null, bookmark), ca)
    val res: Unit = putBmr.put(re)
    verify(dbService).persist(bookmark, app.appModel)
  }

  // --- BookmarkResource ---

  "a get request on bookmarkResource" should "return nonNull response event" in {
    val parameter: scala.List[_root_.scala.Predef.String] = List("abc")
    val re: RequestEvent = new RequestEvent(ProcessCommand(null, null, null, parameter, null, "entity"), null)
    val res = bmr.getEntity(re)
    assertThat(res.get).isEqualTo(Bookmark(Some("abc"), "title", "url"))
  }


}
