package io.skysail.server.app.bookmarks.resources

import java.io.FileReader

import org.json4s
import org.json4s._
import org.json4s.native.JsonMethods._

import scala.io.Source

object Main extends App {
  Console.println("Hello World!")

  val jsonFile = "C:\\Users\\graefca\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Bookmarks"
  for (line <- Source.fromFile(jsonFile).getLines) {
    //    println(line)
  }
  val content = Source.fromFile(jsonFile).getLines.mkString

  val parsed: JObject = parse(content).asInstanceOf[JObject]

  println(parsed \\ "checksum")

  val children: JObject = (parsed \\ "roots").asInstanceOf[JObject]

  println(children.values.keys)

  parseElements(Set("chromeImport"), children)

  def parseElements(tags: Set[String], parent: JValue): Unit = {
    println("Analysis of " + parent)
    parent match {
      case o: JObject => checkObject(tags, o)
      case a: JArray => checkArray(tags, a)
      case x: Any => checkChildren(tags, parent)
    }
  }

  private def checkObject(tags: Set[String], parent: JObject): Unit = {
    for (child <- parent.obj) {
      val f = child.asInstanceOf[JField]
      println("checkObject " + f)
      val a: String = f._1
      val b: _root_.org.json4s.JsonAST.JValue = f._2
      parseElements(tags + a, b)
    }
  }

  private def checkArray(tags: Set[String], parent: JArray): Unit = {
    for (child: JValue <- parent.arr) {
      println("checkArray " + child)
      parseElements(tags, child)
    }
  }

  private def checkChildren(tags: Set[String], parent: JValue) = {
    for (child <- parent.children) {
      child match {
        case o: JObject => {
          //println(s"$tags: $o")
          val q = (o \ "name")
          val t = (o \ "type")
          println("type1: " + t)
          val newTags = q match {
            case j: JString => (tags + j.s)
            case _ => (tags + "unknown")
          }
          parseElements(newTags, o)
        }
        case a: JArray => {
          //println(s"$tags: $a")
          val t = (a \ "type")
          println("type2: " + t)
          println(a.children)
          println(a.values)
          for (c <- a.children) {
            val newTags = (tags + "hier")
            parseElements(newTags, c.asInstanceOf[JObject])
          }
        }
        case s: JString => {
          println("XXX: " + s.s)
        }
        case x: Any => {
          val t = (x \ "type")
          println("type3: " + t)
          println("unknown: " + child.getClass.getName)
        }
        case _: Any => {
          println("really unknown: " + child.getClass.getName)
        }
      }
    }

  }
}

