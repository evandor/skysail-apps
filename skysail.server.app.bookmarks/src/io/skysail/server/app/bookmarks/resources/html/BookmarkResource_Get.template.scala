
package io.skysail.server.app.bookmarks.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel

object BookmarkResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[RepresentationModel,ResponseEventBase,io.skysail.server.app.bookmarks.domain.Bookmark,play.twirl.api.HtmlFormat.Appendable] {

  /*************************************
* Home page.                        *
*                                   *
* @param msg The message to display *
*************************************/
  def apply/*6.2*/(rep: RepresentationModel, response: ResponseEventBase, bm: io.skysail.server.app.bookmarks.domain.Bookmark):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {

def /*8.2*/render/*8.8*/(json: String):play.twirl.api.HtmlFormat.Appendable = {_display_(

Seq[Any](format.raw/*8.26*/("""
  """),format.raw/*9.3*/("""render(json)
  "hi"
""")))};
Seq[Any](format.raw/*6.110*/("""

"""),format.raw/*11.2*/("""

"""),_display_(/*13.2*/main(response)/*13.16*/ {_display_(Seq[Any](format.raw/*13.18*/("""

"""),format.raw/*15.1*/("""<br><br><br>

<div class="container">
    <div class="starter-template">
        <h1><a href="/bookmarks/v1/bms">Bookmarks</a> &gt; """),_display_(/*19.61*/rep/*19.64*/.rawData.head.get("id")),format.raw/*19.87*/("""</h1>
        <p class="lead">show bookmark:</p>

        <div class="form-group">
            <label for="title">Title</label>
            <input type="text"  class="form-control-plaintext" id="title" name="title" aria-describedby="emailHelp" placeholder="(Optional) Title" value=""""),_display_(/*24.156*/bm/*24.158*/.title),format.raw/*24.164*/("""">
        </div>
        <div class="form-group">
            <label for="url">URL</label>
            <a href=""""),_display_(/*28.23*/bm/*28.25*/.url),format.raw/*28.29*/("""">"""),_display_(/*28.32*/bm/*28.34*/.url),format.raw/*28.38*/("""</a>
        </div>
        <div class="form-group">
            <label for="url">Created</label>
            <input type="text"  class="form-control-plaintext" id="url" name="url" placeholder="URL" value=""""),_display_(/*32.110*/bm/*32.112*/.created),format.raw/*32.120*/("""">
        </div>
        <div class="form-group">
            <a href="./"""),_display_(/*35.25*/bm/*35.27*/.id.get),format.raw/*35.34*/("""/">update</a>
        </div>

    </div>
</div>

""")))}))
      }
    }
  }

  def render(rep:RepresentationModel,response:ResponseEventBase,bm:io.skysail.server.app.bookmarks.domain.Bookmark): play.twirl.api.HtmlFormat.Appendable = apply(rep,response,bm)

  def f:((RepresentationModel,ResponseEventBase,io.skysail.server.app.bookmarks.domain.Bookmark) => play.twirl.api.HtmlFormat.Appendable) = (rep,response,bm) => apply(rep,response,bm)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Mon Feb 19 13:59:41 CET 2018
                  SOURCE: C:/git/skysail-apps/skysail.server.app.bookmarks/./src/io/skysail/server/app/bookmarks/resources/BookmarkResource_Get.scala.html
                  HASH: 05e725cb1fb96fe76a4d1853c0f9292e4e1f9c5c
                  MATRIX: 774->193|960->304|973->310|1067->328|1096->331|1157->301|1186->352|1215->355|1238->369|1278->371|1307->373|1467->506|1479->509|1523->532|1834->815|1846->817|1874->823|2015->937|2026->939|2051->943|2081->946|2092->948|2117->952|2352->1159|2364->1161|2394->1169|2496->1244|2507->1246|2535->1253
                  LINES: 16->6|20->8|20->8|22->8|23->9|26->6|28->11|30->13|30->13|30->13|32->15|36->19|36->19|36->19|41->24|41->24|41->24|45->28|45->28|45->28|45->28|45->28|45->28|49->32|49->32|49->32|52->35|52->35|52->35
                  -- GENERATED --
              */
          