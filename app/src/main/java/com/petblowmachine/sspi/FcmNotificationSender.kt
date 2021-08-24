package com.petblowmachine.sspi

import android.app.Activity
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import java.util.HashMap

class FcmNotificationSender(
    private var userFcmToken: String?,
    var title: String?,
    var body: String?,
    var mActivity: Activity?
) {

    private val postUrl = "https://fcm.googleapis.com/fcm/send"
    private val fcmServerKey = "AAAADY5fDVM:APA91bHK1gZ0WihfANL7BErrJ-upB8f0BZxT_1a2OnanzjY9jqgkbKWWraDhXjU2L2N3-Dt8zOZ_UqznFXLMxmkFWC5wef9C0_GragxOdVzPUkCEMtNQJ91m60LZzsHF4Ia8gP1AHAA6"

    fun sendNotifications() {
        val requestQueue = Volley.newRequestQueue(mActivity)
        val mainObj = JSONObject()
        try {
            mainObj.put("to", userFcmToken)
            val notiObject = JSONObject()
            notiObject.put("title", title)
            notiObject.put("body", body)
            notiObject.put("icon", "sspi_logo")
            mainObj.put("notification", notiObject)

            val request: JsonObjectRequest = object : JsonObjectRequest(
                Method.POST, postUrl,
                mainObj,
                Response.Listener{},
                Response.ErrorListener {}){

                override fun getHeaders(): MutableMap<String, String> {

                    val header: MutableMap<String, String> = HashMap()
                    header["content-type"] = "application/json"
                    header["authorization"] = "key=$fcmServerKey"
                    return header

                }

            }
            requestQueue.add(request)

        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

}