package io.skysail.server.app.bookmarks.services

import java.net.URL
import java.time.Instant
import java.util.UUID
import java.util.concurrent.TimeUnit

import akka.actor.{ActorSelection, ActorSystem}
import io.skysail.domain.RequestEvent
import io.skysail.domain.messages.ProcessCommand
import io.skysail.server.adapter.JSoupAdapter
import io.skysail.server.app.bookmarks.BookmarksApplication
import io.skysail.server.app.bookmarks.domain.{Bookmark, HttpResource}
import io.skysail.server.app.bookmarks.resources.PostBookmarkResource
import io.skysail.server.routes.RoutesCreator
import org.json4s.native.JsonMethods.parse
import org.json4s.{JArray, JField, JObject, JString, JValue}
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import org.osgi.framework.BundleContext
import org.slf4j.LoggerFactory

import scala.concurrent.duration.Duration
import scala.io.Source
import scala.util.{Failure, Success, Try}

object BookmarkSchedulerService {

  private val log = LoggerFactory.getLogger(this.getClass)

  def checkBookmarks(actorSystem: ActorSystem) = {
    if (actorSystem == null) {
      log warn s"cannot check bookmarks as actorSystem was null"
    } else {
      val scheduler = actorSystem.scheduler
      val task = new Runnable {
        def run() {
          log.info("Hello")
        }
      }
      implicit val executor = actorSystem.dispatcher

      scheduler.schedule(
        initialDelay = Duration(5, TimeUnit.SECONDS),
        interval = Duration(10, TimeUnit.SECONDS),
        runnable = task)
    }
  }

  def importBookmarks(app: BookmarksApplication, bundleContext: BundleContext)(implicit actorSystem: ActorSystem) = {

    val appActor: ActorSelection = RoutesCreator.getApplicationActorSelection(actorSystem, classOf[BookmarksApplication].getName)

    //val jsonFile = "C:\\Users\\graefca\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Bookmarks"
    //val jsonFile = "/Users/carsten/Library/Application Support/Google/Chrome/Default/Bookmarks"
    val jsonFile = "./Book"

    val importFile: URL = bundleContext.getBundle().getResource("BookmarksSmall")

    val is = importFile.openConnection().getInputStream;
    //val content = Source.fromFile(jsonFile).getLines.mkString
    val content = Source.fromInputStream(is, "UTF-8").mkString
    val parsed: JObject = parse(content).asInstanceOf[JObject]

    val roots: JObject = (parsed \\ "roots").asInstanceOf[JObject]
    parseElements(Set("chromeImport"), roots, app, appActor)
  }

  def parseElements(tags: Set[String], parent: JValue, app: BookmarksApplication, appActor: ActorSelection): Unit = {
    //println("Analysis of " + parent)
    parent match {
      case o: JObject => checkObject(tags, o, app, appActor)
      case a: JArray => checkArray(tags, a, app, appActor)
      case x: Any => println("Autsch") //checkChildren(tags, parent)
    }
  }

  private def checkObject(tags: Set[String], parent: JObject, app: BookmarksApplication, appActor: ActorSelection): Unit = {
    //println("typeO: " + parent \\ "type")

    if ((parent \\ "type").isInstanceOf[JString]) {
      //counter += 1
      println(s"URL:  $parent")
      val url = (parent \\ "url")
      if (url.isInstanceOf[JString]) {
        val bm = Bookmark(Some(UUID.randomUUID().toString), "", url.asInstanceOf[JString].s)
        val r = HttpResource(url.asInstanceOf[JString].s)
        val re = new RequestEvent(ProcessCommand(null, null, null, null, null, bm.copy(root = r), null, null), null)
        val pbr = new PostBookmarkResource()
        pbr.setApplication(app)
        pbr.post(re)(null)
      }
      //appActor ! ProcessCommand(null,null,null,null,null,cm,null,null)
    } else {
      for (child <- parent.obj) {
        val f = child.asInstanceOf[JField]
        //println("checkObject " + f)
        val a: String = f._1
        val b: _root_.org.json4s.JsonAST.JValue = f._2
        parseElements(tags + a, b, app, appActor)
      }
    }
  }

  private def checkArray(tags: Set[String], parent: JArray, app: BookmarksApplication, appActor: ActorSelection): Unit = {
    //println("typeA: " + parent \\ "type")
    for (child: JValue <- parent.arr) {
      //println("checkArray " + child)
      parseElements(tags, child, app, appActor)
    }
  }

}

object BookmarksService {

  private val log = LoggerFactory.getLogger(this.getClass)

  def addMetadata(bookmark: Bookmark) = {
    //val root = HttpResource(bookmark.url)
    var bm = bookmark//.copy(root = root)

    //.copy()
    val metadata = new JSoupAdapter().readFrom(bookmark.url).asInstanceOf[Try[Document]]
    metadata match {
      case Success(v) =>
        bm = bm.copy(title = v.title())
        val favicon = v.head().select("link[href~=.*\\.(ico|png)]").first()
        if (favicon != null) {
          bm = bm.copy(favIcon = Some(bookmark.url + favicon.attr("href")))
        }
        bm = bm.copy(hash = generateHash(v))

        val links: Elements = v.select("a[href]")
        //println ("links: " + links)
        import scala.collection.JavaConversions._
        for (link <- links) {
          print(" * a: <%s>  (%s)", link.attr("abs:href"), link.text)
        }
      //        for (link: AnyRef <- links.traverse()) {
      //          print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35)))
      //        }


      case Failure(f) => log info s"problem getting metadata for ${bookmark.url}"
    }
    bm.copy(created = Instant.now.getEpochSecond)
  }

  private def convertByteArrayToHexString(hashedBytes: Array[Byte]) = {
    val sb = new StringBuffer()
    for (i <- 0 to hashedBytes.length) {
      sb.append(Integer.toString((hashedBytes(i) & 0xff) + 0x100, 16).substring(1))
    }
    sb.toString
  }

  private def generateHash(v: Document) = {
    val bytes = v.body().toString.getBytes(v.charset())
    String.format(
      "%064x",
      new java.math.BigInteger(1, java.security.MessageDigest.getInstance("SHA-1").digest(bytes)))
  }

}
