package com.sorokinoleg.forecast

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.today_fragment.*
import kotlin.math.round

class TodayFragment : Fragment() {

    private val weatherUrl = "https://api.darksky.net/forecast/9bbf096599dd872442195480bb377f4c/45.0393,38.9872"
    private val imageUrl = "https://via.placeholder.com/300"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val queue = Volley.newRequestQueue(context)

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, weatherUrl, null,
            Response.Listener {
                val currently = it.getJSONObject("currently")

                setSummary(currently.get("summary").toString())
                setTemperature(currently.get("temperature").toString())
                setHumidity(currently.get("humidity").toString())
                setPressure(currently.get("pressure").toString())
            },
            Response.ErrorListener {
                todayFragmentSummary.text = it.toString()
            })

        val imageRequest = ImageRequest(imageUrl,
            Response.Listener {
                todayFragmentImage.setImageBitmap(it)
            }, 0, 0, null,
            Response.ErrorListener {
                todayFragmentImage.setImageResource(R.drawable.ic_error_black_24dp)
            })

        queue.add(jsonObjectRequest)
        queue.add(imageRequest)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.today_fragment, container, false)
    }

    private fun setTemperature(temperature: String) {
        todayFragmentTemperature.text = "Temperature: ${temperature} °F"
    }

    private fun setHumidity(humidity: String) {
        todayFragmentHumidity.text = "Humidity: ${humidity}%"
    }

    private fun setSummary(summary: String) {
        todayFragmentSummary.text = summary
    }

    private fun setPressure(pressure: String) {
        todayFragmenPressure.text = "Pressure: ${pressure}"
    }
}
