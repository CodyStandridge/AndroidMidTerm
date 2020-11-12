package com.example.androidmidterm

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.androidmidterm.R
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Volley uses internet permissions which needs to be enabled in the AndroidManifest.xml
        val queue = Volley.newRequestQueue(this)

        // TODO: change this to your url after you have endpoints
        val url = "https://exam1-292319.uc.r.appspot.com/?size="

        // button is the id for the button in activity_main.xml
        button.setOnClickListener {
            //val parameters = JSONObject()
            var size: String = ""
            try {
                size = sizeEnter.text.toString()
              //  parameters.put("size", size)
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            // OU calendar returns a Json Array, if your website returns a Json Object then use JsonObjectRequest
            val stringRequest = JsonObjectRequest(
                Request.Method.GET,
                url+size,
                null,
                Response.Listener<JSONObject> { response ->
                    modelTxt.text = "${response.getInt("model")}"
                    effortTxt.text  = "${response.getInt("effort")}"
                    timeTxt.text  = "${response.getInt("time")}"
                    staffTxt.text = "${response.getInt("staff")}"

                },
                Response.ErrorListener {
                        error ->  print("onCreate: ${error.message}")
                }
            )

            queue.add(stringRequest)
        }

    }
}
