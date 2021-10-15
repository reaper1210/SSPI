package com.petblowmachine.sspi

import android.app.Activity
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.firestore.FirebaseFirestore
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

    fun sendNotifications() {

        val db = FirebaseFirestore.getInstance()
        db.collection("admin").document("fcm_server_key").get().addOnCompleteListener {

            if(it.isSuccessful){
                val key = it.result?.data?.get("fcm_server_key").toString()

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
                            header["authorization"] = "key=$key"
                            return header

                        }

                    }
                    requestQueue.add(request)

                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            }
            else{
                Toast.makeText(mActivity,"Some Error Occurred", Toast.LENGTH_SHORT).show()
            }

        }

    }

}