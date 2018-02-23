package io.skysail.server.app.bookmarks.domain

case class HttpResource(
                         id: String, // = url
                         links: Set[HttpResource] = Set()
                       )
