package io.skysail.server.app.bookmarks.repository

import io.skysail.api.persistence.DbService
import io.skysail.domain.model.ApplicationModel
import io.skysail.server.app.bookmarks.domain.{Bookmark, HttpResource}

class BookmarksRepository (dbService: DbService, appModel: ApplicationModel) {

  dbService.createWithSuperClass("V",
    DbService.tableNameFor(classOf[Bookmark]),
    DbService.tableNameFor(classOf[HttpResource])
  )
  dbService.register(classOf[Bookmark])
  dbService.register(classOf[HttpResource])

  def save(entity: Any): String = {
    dbService.persist(entity, appModel)
  }

  def find( /*Filter filter, Pagination pagination*/ ): List[Bookmark] = {
    val sql = "SELECT * from " + DbService.tableNameFor(classOf[Bookmark])
    //                + (!StringUtils.isNullOrEmpty(filter.getPreparedStatement()) ? " WHERE " + filter.getPreparedStatement()
    //                        : "")
    //                + " " + limitClause(pagination);
    //pagination.setEntityCount(count(filter));
    dbService.findGraphs(classOf[Bookmark], sql, appModel) //, filter.getParams());
  }

  def find(id: String): Option[Bookmark] = {
    val sql = s"SELECT * from ${DbService.tableNameFor(classOf[Bookmark])} where id='${id}'"
    //println("executing sql " + sql)
    // Bookmark(None, "", "", created = Instant.MIN.getEpochSecond)

    val res: Seq[Bookmark] = dbService.findGraphs(classOf[Bookmark], sql, appModel) //, filter.getParams());
    if (res.size == 0) None else res.headOption
  }

  def delete(id: String): Boolean = {
    dbService.delete(classOf[Bookmark],id)
  }
}