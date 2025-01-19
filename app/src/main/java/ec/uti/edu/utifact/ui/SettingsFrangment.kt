package ec.uti.edu.utifact.ui

import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.util.Log
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import ec.uti.edu.utifact.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
        val rucPreference: Preference? = findPreference("ruc")
        val empresaPreference: Preference? = findPreference("empresa")
        val puntoEmisionPreference: Preference? = findPreference("punto_emision")
    }
}