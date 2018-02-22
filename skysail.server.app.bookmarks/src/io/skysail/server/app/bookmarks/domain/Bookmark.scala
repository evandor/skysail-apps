package io.skysail.server.app.bookmarks.domain

import java.time.Instant
import io.skysail.api.ddd.Entity
import io.skysail.api.ui._

object State {
  sealed trait EnumVal
  case object NEW extends EnumVal
  case object NOT_FOUND extends EnumVal
  case object UNCHANGED extends EnumVal
  case object CHANGED extends EnumVal
}

case class Bookmark(
                     id: Option[String],
                     title: String,
                     url: String,
                     favIcon: Option[String] = Some("http://www.spiegel.de/favicon.ico"),
                     hash: String = "",
                     created: Long = Instant.MIN.getEpochSecond,
                     clicked: Integer = 0,
                     root: HttpResource = null,
                     state: Option[State.EnumVal] = Some(State.NEW)
                   ) extends Entity[String] with Linkable {

  override val _links: List[Link] = List(
    IconLink("show",   "fas fa-eye",   "/bookmarks/v1/bms/${id}",  "color:#000066", "show entity"),
    IconLink("update", "fa fa-edit",   "/bookmarks/v1/bms/${id}/", "", "update entity"),
    IconLink("delete", "fas fa-trash", "/bookmarks/v1/bms/${id}/", "color:Tomato", "delete entity")
  )

}


case class BookmarkList(
                         bookmarks: List[Bookmark]
                       ) extends Linkable {

  override val _links: List[Link] = List(
    ButtonLink("create-form", "create new Bookmark", "/bookmarks/v1/bms/", style = "btn btn-outline-primary")
  )
}
