package io.skysail.server.app.bookmarks.services

import java.time.Instant
import java.util.concurrent.TimeUnit

import akka.actor.ActorSystem
import io.skysail.server.adapter.JSoupAdapter
import io.skysail.server.app.bookmarks.domain.{Bookmark, HttpResource}
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import org.slf4j.LoggerFactory

import scala.concurrent.duration.Duration
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

}

object BookmarksService {

  private val log = LoggerFactory.getLogger(this.getClass)

  def addMetadata(bookmark: Bookmark) = {
    val root = HttpResource(bookmark.url)
    var bm = bookmark.copy(root = root)

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
