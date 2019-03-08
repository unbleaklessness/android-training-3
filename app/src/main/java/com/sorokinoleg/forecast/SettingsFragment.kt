package com.sorokinoleg.forecast

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

class SettingsFragment : Fragment() {

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.settings_fragment, container, false)

        view.findViewById<Button?>(R.id.settingsFragmentUpdate)?.setOnClickListener {
            if (activity is MainActivity) {
                val parent = activity as MainActivity
                val lat = view.findViewById<EditText?>(R.id.settingsFragmentLatitude)?.text.toString()
                val lon = view.findViewById<EditText?>(R.id.settingsFragmentLongitude)?.text.toString()
                parent.updateCity("$lat, $lon")
            }
        }

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
