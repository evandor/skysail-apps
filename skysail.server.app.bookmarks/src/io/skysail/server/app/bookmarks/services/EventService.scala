package io.skysail.server.app.bookmarks.services

import org.osgi.service.event.{Event, EventAdmin}
import java.util
import scala.collection.JavaConverters._

class EventService(eventAdmin: EventAdmin) {


  def send(msg: String) = {
//    val properties = new util.Hashtable[String, Any]
//    properties.put("time", System.currentTimeMillis())
//    properties.put("message", msg)
    var properties = scala.collection.mutable.Map[String,String]();
    val reportGeneratedEvent = new org.osgi.service.event.Event("io/skysail/server/bookmarks/services/GENERATED", properties.asJava)
    if (eventAdmin != null) {
      eventAdmin.sendEvent(reportGeneratedEvent)
    }
  }

}
