package com.sorokinoleg.forecast

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.today_fragment.*
import org.json.JSONObject

class TodayFragment : Fragment() {

    private val weatherUrl = "https://api.darksky.net/forecast/9bbf096599dd872442195480bb377f4c/45.0393,38.9872"
    private val imageUrl = "https://via.placeholder.com/300"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        refreshData()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.today_fragment, container, false)
    }

    private fun refreshData() {
        val queue = Volley.newRequestQueue(context)

        val weatherRequest = JsonObjectRequest(Request.Method.GET, weatherUrl, null,
            Response.Listener { handleWeatherData(it) },
            Response.ErrorListener {
                Toast.makeText(context, "Could not load weather data!", Toast.LENGTH_LONG).show()
            })

        queue.add(weatherRequest)

        val weatherIconRequest = ImageRequest(imageUrl,
            Response.Listener {
                todayFragmentImage.setImageBitmap(it)
            }, 0, 0, null, Bitmap.Config.RGB_565,
            Response.ErrorListener {
                Toast.makeText(context, "Could not load weather image!", Toast.LENGTH_LONG).show()
            })

        queue.add(weatherIconRequest)
    }

    private fun handleWeatherData(data: JSONObject) {
        val currently = data.getJSONObject("currently")
        val hourly = data.getJSONObject("hourly")
        val daily = data.getJSONObject("daily")

        setSummary(currently.get("summary").toString())
        setTemperature(currently.get("temperature").toString())
        setHumidity(currently.get("humidity").toString())
        setPressure(currently.get("pressure").toString())

        setHourly(hourly.get("summary").toString())
        setDaily(daily.get("summary").toString())
    }

    private fun setTemperature(temperature: String) {
        todayFragmentTemperature.text = "Temperature: ${temperature}°F"
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

    private fun setHourly(hourly: String) {
        todayFragmentHourly.text = hourly
    }

    private fun setDaily(daily: String) {
        todayFragmentDaily.text = daily
    }
}
