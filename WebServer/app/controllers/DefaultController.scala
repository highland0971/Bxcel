package controllers

/**
  * Created by vivia on 2016-10-04.
  */

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import java.util.UUID

class DefaultController @Inject() extends Controller {

  def index = Action {
    Ok(views.html.framework())
  }

  def subscriptionMgmt = Action{
    Ok(views.html.subscriptionMgmt(UUID.randomUUID().toString()))
  }

  def getSubscriptionList = Action{
    val json: JsValue = JsArray(
      Seq(
        JsObject(
          Seq(
            "id" -> JsString("subscribed"),
            "text" -> JsString("订阅列表"),
            "children" -> JsArray(
              Seq(
                JsObject(
                  Seq(
                    "id" -> JsString("S1_MME_XDR"),
                    "text" -> JsString("S1-MME XDR"),
                    "children" -> JsArray(
                      Seq(
                        JsObject(
                          Seq("id" -> JsString("高掉话网元"),
                              "text" -> JsString("高掉话网元"))
                        ),
                        JsObject(
                          Seq("id" -> JsString("位置区分析"),
                            "text" -> JsString("位置区分析"))
                        )
                      )
                    )
                  )
                ),
                JsObject(
                  Seq(
                    "id" -> JsString("S1_U_XDR"),
                    "text" -> JsString("S1-U XDR"),
                    "children" -> JsArray(
                      Seq(
                        JsObject(
                          Seq("id" -> JsString("高掉话网元"),
                            "text" -> JsString("高掉话网元"))
                        ),
                        JsObject(
                          Seq("id" -> JsString("位置区分析"),
                            "text" -> JsString("位置区分析"))
                        )
                      )
                    )
                  )
                )
              )
            )
          )
        ),
        JsObject(
          Seq(
            "id" -> JsString("rawData"),
            "text" -> JsString("数据源"),
            "children" -> JsArray(
              Seq(
                JsObject(
                  Seq(
                    "id" -> JsString("S1_MME_XDR"),
                    "text" -> JsString("S1-MME XDR")
                  )
                ),
                JsObject(
                  Seq(
                    "id" -> JsString("S1_U_XDR"),
                    "text" -> JsString("S1-U XDR")
                  )
                )
              )
            )
          )
        )
      )
    )
    Ok(json)
  }

  def getDataBrowser(sourceName:String) = Action{
    Ok(views.html.dataBrowser(UUID.randomUUID().toString(),sourceName))
  }

  def getPivotAnalyzer(targetQuery:String) = Action{
    Ok(views.html.pivotAnalyzer(UUID.randomUUID().toString()))
  }

  def dataSourceMgmt() = Action{
    Ok(views.html.dataSourceMgmt(UUID.randomUUID().toString()))
  }

  def getDataSourceList() = Action{
    val json: JsValue = JsArray(
      Seq(
        JsObject(
          Seq(
            "id" -> JsString("rawData"),
            "text" -> JsString("数据源"),
            "children" -> JsArray(
              Seq(
                JsObject(
                  Seq(
                    "id" -> JsString("S1_MME_XDR"),
                    "text" -> JsString("S1-MME XDR")
                  )
                ),
                JsObject(
                  Seq(
                    "id" -> JsString("S1_U_XDR"),
                    "text" -> JsString("S1-U XDR")
                  )
                )
              )
            )
          )
        )
      )
    )
    Ok(json)
  }

  def verifyDataSourceConfig = Action(parse.multipartFormData) { request =>
    request.body.file("manifest").map { picture =>
      import java.io.File
      val filename = picture.filename
      val contentType = picture.contentType
      picture.ref.moveTo(new File(s"./public/uploaded/$filename"))
      Ok("File uploaded")
    }.getOrElse {
      NotFound("File not found")
    }
  }
}
