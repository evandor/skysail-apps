
package io.skysail.server.app.bookmarks.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel

object PostBookmarkResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[RepresentationModel,ResponseEventBase,Object,play.twirl.api.HtmlFormat.Appendable] {

  /*************************************
* Home page. *
* *
* @param msg The message to display *
*************************************/
  def apply/*6.2*/(rep: RepresentationModel, response: ResponseEventBase, e: Object):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*6.68*/("""

"""),_display_(/*8.2*/main(response)/*8.16*/ {_display_(Seq[Any](format.raw/*8.18*/("""

    """),format.raw/*10.5*/("""<br>
    <br>
    <br>

    <div class="container">
        <div class="starter-template">
            <h1>Bookmarks</h1>
            <p class="lead">add bookmark:</p>
            <form action='"""),_display_(/*18.28*/rep/*18.31*/.linkFor("io.skysail.app.bookmarks.PostBookmarkResource", None)),format.raw/*18.94*/("""' method="post">

                <div class="form-group">
                    <label for="title">Title</label>
                    <input type="text" class="form-control" id="title" name="title" aria-describedby="emailHelp" placeholder="(Optional) Title">
                    <small id="titleHelp" class="form-text text-muted">The title will be read from the URL if left empty</small>
                </div>
                <div class="form-group">
                    <label for="url">URL</label>
                    <input type="text" class="form-control" id="url" name="url" placeholder="URL" value="https://" autofocus>
                </div>
                <button type="submit" class="btn btn-primary">Anlegen</button>

            </form>

        </div>
    </div>

""")))}))
      }
    }
  }

  def render(rep:RepresentationModel,response:ResponseEventBase,e:Object): play.twirl.api.HtmlFormat.Appendable = apply(rep,response,e)

  def f:((RepresentationModel,ResponseEventBase,Object) => play.twirl.api.HtmlFormat.Appendable) = (rep,response,e) => apply(rep,response,e)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Mon Feb 19 12:48:07 CET 2018
                  SOURCE: C:/git/skysail-apps/skysail.server.app.bookmarks/./src/io/skysail/server/app/bookmarks/resources/PostBookmarkResource_Get.scala.html
                  HASH: 8693732335301a4c41269b8de3affa272c2b767b
                  MATRIX: 680->136|841->202|869->205|891->219|930->221|963->227|1185->422|1197->425|1281->488
                  LINES: 16->6|21->6|23->8|23->8|23->8|25->10|33->18|33->18|33->18
                  -- GENERATED --
              */
          