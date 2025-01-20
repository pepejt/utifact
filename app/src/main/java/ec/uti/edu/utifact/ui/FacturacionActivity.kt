package ec.uti.edu.utifact.ui

import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.preference.PreferenceManager
import ec.uti.edu.utifact.R
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException


class FacturacionActivity : AppCompatActivity() {

    private val client = OkHttpClient()
    private val apiLoginUrl = "https://demo.factbit.bsxlabs.com/demo/api/users/login"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_facturacion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun onClickFacturar(view: View){
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val postBody = "{\"email\":\"besixplus\",\"password\":\"123456\"}".trim()
        val MEDIA_TYPE_JSON = "application/json".toMediaType()
        val request = Request.Builder()
            .url(apiLoginUrl)
            .post(postBody.toRequestBody(MEDIA_TYPE_JSON))
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            var resp = response.body!!.string()
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
            val editor: Editor = sharedPreferences.edit()
            editor.putString("ruc","configuracion.ruc")
            editor.commit()

        }
    }
}