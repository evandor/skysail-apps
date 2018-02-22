package io.skysail.server.app.bookmarks.domain

case class HttpResource(
                         url: String,
                         links: Set[HttpResource] = Set()
                       )
