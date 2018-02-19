package io.skysail.server.app.bookmarks.resources

import akka.http.scaladsl.model.ContentTypes._
import akka.http.scaladsl.model.FormData
import akka.http.scaladsl.model.StatusCodes._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import io.skysail.server.app.bookmarks.domain.Bookmark
import io.skysail.server.app.bookmarks.resources.BoorkmarksRoutesTest._

object BoorkmarksRoutesTest {
  val URL_ROOT = "/bookmarks/v1"
}

/**
  * Integration Tests of Bookmark Resources and their routes.
  */
@RunWith(classOf[JUnitRunner])
class BoorkmarksRoutesTest() extends DemoApplicationTest {

  "A GET request to the BookmarksResource" should {

    "return the html page if no accept header was set" in {
      Get(s"${URL_ROOT}/bms") ~> router ~> check {
        status shouldBe OK
        contentType shouldBe `text/html(UTF-8)`
        responseAs[String] should include("create new Bookmark")
      }
    }

//    "return the json representation if an accept header for application/json is sent" in {
//      Get("${URL_ROOT}/bms").addHeader(applicationJsonAcceptHeader) ~> router ~> check {
//        status shouldBe OK
//        // TODO
//        //contentType shouldBe `application/json`
//        responseAs[String] should include("[]")
//      }
//    }
  }

  //  "A POST request to ${URL_ROOT}/bms" should {
  //
  //    "return the html page if no accept header was set" in {
  //      Post("${URL_ROOT}/bms") ~> res ~> check {
  //        status shouldBe OK
  //        contentType shouldBe `text/html(UTF-8)`
  //        responseAs[String] should include("Create New Bookmark")
  //      }
  //    }
  //  }



  "A GET request to ${URL_ROOT}/bms/<id>/" should {

//    "return the html update page (if no accept header was set)" in {
//      create(Bookmark(None, "sdfdfds","sadsdff")) ~> check {
//        Thread.sleep(10)
//        val loc = header("location")
//        //val bm = parse(responseAs[String]).extract[Bookmark]
//        println(loc.get.value()) // TODO wrong url, without id!
//        Get(loc.get.value()) ~> router ~> check {
//          status shouldBe OK
//          contentType shouldBe `text/html(UTF-8)`
//          //responseAs[String] should include("update bookmark")
//        }
//      }
//    }

//    "return the json representation if an accept header for application/json is sent" in {
//      create(Bookmark(None, "getOnPutResource", "url")) ~> check {
//        Thread.sleep(10)
//        val bm = parse(responseAs[String]).extract[Bookmark]
//        println(bm)
//        Get("${URL_ROOT}/bms/" + bm.id.get + "/").addHeader(applicationJsonAcceptHeader) ~> router ~> check {
//          status shouldBe OK
//          //contentType shouldBe `text/html(UTF-8)`
//          responseAs[String] should include("\"id\":\"" + bm.id.get + "\"")
//        }
//      }
//    }
  }

  "A PUT request to ${URL_ROOT}/bms/<id>/" should {
    "update the entity" in {

    }
  }

  private def create(bm: Bookmark) = {
    Post("${URL_ROOT}/bms/").withEntity(FormData(Map("title" -> bm.title, "url" -> bm.url)).toEntity) ~> router
  }


}
