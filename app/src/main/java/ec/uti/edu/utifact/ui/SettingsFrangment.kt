package ec.uti.edu.utifact.ui

import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import ec.uti.edu.utifact.R

class SettingsFragment : PreferenceFragmentCompat()  {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
        val logedPreference: Preference? = findPreference("loged")
        val rucPreference: Preference? = findPreference("ruc")
        val empresaPreference: Preference? = findPreference("empresa")
        val puntoEmisionPreference: Preference? = findPreference("punto_emision")
        if (logedPreference != null) {
            logedPreference.setOnPreferenceClickListener({
                var pref = PreferenceManager.getDefaultSharedPreferences(requireActivity().application)
                pref.edit().clear().commit()
                getActivity()?.finish()
                true
            })

        }

    }

}