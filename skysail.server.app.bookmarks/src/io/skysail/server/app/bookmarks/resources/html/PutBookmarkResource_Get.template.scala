
package io.skysail.server.app.bookmarks.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel

object PutBookmarkResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[RepresentationModel,ResponseEventBase,io.skysail.server.app.bookmarks.domain.Bookmark,play.twirl.api.HtmlFormat.Appendable] {

  /*************************************
* Home page.                        *
*                                   *
* @param msg The message to display *
*************************************/
  def apply/*6.2*/(rep: RepresentationModel, response: ResponseEventBase, bm: io.skysail.server.app.bookmarks.domain.Bookmark):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*6.110*/("""

"""),_display_(/*8.2*/main(response)/*8.16*/ {_display_(Seq[Any](format.raw/*8.18*/("""

"""),format.raw/*10.1*/("""<br><br><br>

<div class="container">
    <div class="starter-template">
        <h1>Bookmarks</h1>
        <p class="lead">update bookmark:</p>
        <span>"""),_display_(/*16.16*/rep/*16.19*/.rawData),format.raw/*16.27*/("""</span>
        <form action='.?_method=PUT' method="POST">

            <div class="form-group">
                <label for="title">Title</label>
                <input type="text"  class="form-control" id="title" name="title" placeholder="(Optional) Title" value=""""),_display_(/*21.121*/bm/*21.123*/.title),format.raw/*21.129*/("""">
            </div>
            <div class="form-group">
                <label for="url">URL</label>
                <input type="text"  class="form-control" id="title" name="url" value=""""),_display_(/*25.88*/bm/*25.90*/.url),format.raw/*25.94*/("""">
            </div>
            <div class="form-group">
                <label for="url">Created</label>
                <input type="text"  class="form-control-plaintext" id="url" name="url" placeholder="URL" value=""""),_display_(/*29.114*/bm/*29.116*/.created),format.raw/*29.124*/("""">
            </div>
            <div class="form-group">
                <input type="submit">
            </div>

        </form>

        <hr>
        <form action='.?_method=DELETE' method="POST">
            <th colspan="2">
                <input type="submit" value="delete">
            </th>
        </form>

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
                  DATE: Mon Feb 19 15:22:03 CET 2018
                  SOURCE: C:/git/skysail-apps/skysail.server.app.bookmarks/./src/io/skysail/server/app/bookmarks/resources/PutBookmarkResource_Get.scala.html
                  HASH: f815dfd3541a18d9422c120682e39cf025df4c51
                  MATRIX: 777->193|981->301|1009->304|1031->318|1070->320|1099->322|1286->482|1298->485|1327->493|1622->760|1634->762|1662->768|1880->959|1891->961|1916->965|2165->1186|2177->1188|2207->1196
                  LINES: 16->6|21->6|23->8|23->8|23->8|25->10|31->16|31->16|31->16|36->21|36->21|36->21|40->25|40->25|40->25|44->29|44->29|44->29
                  -- GENERATED --
              */
          