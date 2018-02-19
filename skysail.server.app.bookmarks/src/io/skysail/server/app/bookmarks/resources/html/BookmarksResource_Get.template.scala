
package io.skysail.server.app.bookmarks.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel

object BookmarksResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[RepresentationModel,ResponseEventBase,io.skysail.server.app.bookmarks.domain.BookmarkList,play.twirl.api.HtmlFormat.Appendable] {

  /*************************************
* Home page.                        *
*                                   *
* @param msg The message to display *
*************************************/
  def apply/*6.2*/(rep: RepresentationModel, response: ResponseEventBase, bms: io.skysail.server.app.bookmarks.domain.BookmarkList):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*6.115*/("""

"""),_display_(/*8.2*/main(response)/*8.16*/ {_display_(Seq[Any](format.raw/*8.18*/("""

"""),format.raw/*10.1*/("""<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
    <h1 class="h2">"""),_display_(/*11.21*/rep/*11.24*/.model.name),format.raw/*11.35*/("""</h1>
    <div class="btn-toolbar mb-2 mb-md-0">
        <div class="btn-group mr-2">
            <button class="btn btn-sm btn-outline-secondary">Share</button>
            <button class="btn btn-sm btn-outline-secondary">Export</button>
        </div>
        <button class="btn btn-sm btn-outline-secondary dropdown-toggle">
            <span data-feather="calendar"></span>
            This week
        </button>
    </div>
</div>

<div class="row">
    <table class="table table-sm">
        <thead>
        <tr>
            <th>Title</th>
            <th>Url</th>
            <th>Created</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        """),_display_(/*35.10*/for(p <- bms.bookmarks) yield /*35.33*/ {_display_(Seq[Any](format.raw/*35.35*/("""
        """),format.raw/*36.9*/("""<tr>
            <th scope="row">"""),_display_(/*37.30*/p/*37.31*/.title),format.raw/*37.37*/("""</th>
            <td><img src='"""),_display_(/*38.28*/p/*38.29*/.favIcon.getOrElse("thesource")),format.raw/*38.60*/("""'>&nbsp;<a href='"""),_display_(/*38.78*/p/*38.79*/.url),format.raw/*38.83*/("""' target='demo_bms_"""),_display_(/*38.103*/p/*38.104*/.title),format.raw/*38.110*/("""'>"""),_display_(/*38.113*/p/*38.114*/.url),format.raw/*38.118*/("""</a></td>
            <td><script>document.write(moment.unix("""),_display_(/*39.53*/p/*39.54*/.created),format.raw/*39.62*/(""").format("DD.MM.YYYY"));</script></td>
            <td>
                """),_display_(/*41.18*/for(l <- p._links) yield /*41.36*/ {_display_(Seq[Any](format.raw/*41.38*/("""
                """),_display_(/*42.18*/Html(l.toHtml(p.id.get))),format.raw/*42.42*/("""&nbsp;
                """)))}),format.raw/*43.18*/("""
            """),format.raw/*44.13*/("""</td>
        </tr>
        """)))}),format.raw/*46.10*/("""

        """),format.raw/*48.9*/("""</tbody>

    </table>
    <hr>
    """),_display_(/*52.6*/for(l <- bms._links) yield /*52.26*/ {_display_(Seq[Any](format.raw/*52.28*/("""
    """),_display_(/*53.6*/Html(l.toHtml)),format.raw/*53.20*/("""
    """)))}),format.raw/*54.6*/("""

    """),format.raw/*56.5*/("""<hr>
    <a href="/doc/v1/index.html" target="_docs">Doc</a>


</div>

""")))}))
      }
    }
  }

  def render(rep:RepresentationModel,response:ResponseEventBase,bms:io.skysail.server.app.bookmarks.domain.BookmarkList): play.twirl.api.HtmlFormat.Appendable = apply(rep,response,bms)

  def f:((RepresentationModel,ResponseEventBase,io.skysail.server.app.bookmarks.domain.BookmarkList) => play.twirl.api.HtmlFormat.Appendable) = (rep,response,bms) => apply(rep,response,bms)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Mon Feb 19 12:39:19 CET 2018
                  SOURCE: C:/git/skysail-apps/skysail.server.app.bookmarks/./src/io/skysail/server/app/bookmarks/resources/BookmarksResource_Get.scala.html
                  HASH: a27b1cae65d0f7b30e63bbd921a28897cb5253b2
                  MATRIX: 779->193|988->306|1016->309|1038->323|1077->325|1106->327|1266->460|1278->463|1310->474|2022->1159|2061->1182|2101->1184|2137->1193|2198->1227|2208->1228|2235->1234|2295->1267|2305->1268|2357->1299|2402->1317|2412->1318|2437->1322|2485->1342|2496->1343|2524->1349|2555->1352|2566->1353|2592->1357|2681->1419|2691->1420|2720->1428|2820->1501|2854->1519|2894->1521|2939->1539|2984->1563|3039->1587|3080->1600|3140->1629|3177->1639|3240->1676|3276->1696|3316->1698|3348->1704|3383->1718|3419->1724|3452->1730
                  LINES: 16->6|21->6|23->8|23->8|23->8|25->10|26->11|26->11|26->11|50->35|50->35|50->35|51->36|52->37|52->37|52->37|53->38|53->38|53->38|53->38|53->38|53->38|53->38|53->38|53->38|53->38|53->38|53->38|54->39|54->39|54->39|56->41|56->41|56->41|57->42|57->42|58->43|59->44|61->46|63->48|67->52|67->52|67->52|68->53|68->53|69->54|71->56
                  -- GENERATED --
              */
          