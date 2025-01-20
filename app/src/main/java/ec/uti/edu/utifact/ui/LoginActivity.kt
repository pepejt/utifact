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
import androidx.room.Room
import com.google.android.material.textfield.TextInputEditText
import ec.edu.uti.juego.dao.AppDatabase
import ec.uti.edu.utifact.R
import ec.uti.edu.utifact.entity.Configuracion
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import java.util.concurrent.TimeUnit

class LoginActivity : AppCompatActivity() {

    private val client = OkHttpClient()
    private val apiLoginUrl = "https://demo.factbit.bsxlabs.com/demo/api/users/login"
    lateinit var txtUser: TextInputEditText;
    lateinit var txtPassword: TextInputEditText;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtUser = findViewById(R.id.txtUser)
        txtPassword = findViewById(R.id.txtPassword)
    }

    fun onClickLogin(view: View){
        val params = JSONObject()
        params.put("email", txtUser.text.toString())
        params.put("password", txtPassword.text.toString())
        val postBody = params.toString()
        val MEDIA_TYPE_JSON = "application/json".toMediaType()
        val request = Request.Builder()
            .url(apiLoginUrl)
            .header("User-Agent", ".header(\"User-Agent\", \"OkHttp Headers.java\")")
            .addHeader("Accept", "application/json, text/plain, */*")
            .addHeader("content-type", "application/json")
            .post(postBody.toRequestBody(MEDIA_TYPE_JSON))
            .build()


        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) {
                        Log.v("LOGIN", response.message)
                    }else {
                        val db = Room.databaseBuilder(
                            applicationContext,
                            AppDatabase::class.java, "utifact"
                        ).allowMainThreadQueries().build()

                        val configuracionDao = db.configuracionDao()
                        val configuracion = configuracionDao.getById(1);
                        if (configuracion == null){
                            configuracionDao.insert(Configuracion(1,"1803728151001","BsxLabs","001-101"))
                        }

                        var resp = response.body!!.string()
                        try {
                            val jsonRes = JSONObject(resp)
                            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
                            var editor: Editor = sharedPreferences.edit()
                            editor.putString("token", jsonRes.get("access_token").toString())
                            editor.putString("ruc",configuracion.ruc)
                            editor.putString("empresa",configuracion.empresa)
                            editor.putString("punto_emision",configuracion.puntoEmision)
                            editor.commit()
                            finish()
                        }catch (e: Exception){
                            Log.e("HTTP BSX", e.message.toString());
                        }

                    }
                }
            }
        })
        /*

        client1.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                Log.v("LOGIN", response.message)
            }else {
                val db = Room.databaseBuilder(
                    this,
                    AppDatabase::class.java, "utifact"
                ).allowMainThreadQueries().build()

                val configuracionDao = db.configuracionDao()
                val configuracion = configuracionDao.getById(1);
                if (configuracion == null){
                    configuracionDao.insert(Configuracion(1,"1803728151001","BsxLabs","001-101"))
                }

                var resp = response.body!!.string()
                try {
                    val jsonRes = JSONObject(resp)
                    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
                    var editor: Editor = sharedPreferences.edit()
                    editor.putString("token", jsonRes.get("access_token").toString())
                    editor.putString("ruc",configuracion.ruc)
                    editor.putString("empresa",configuracion.empresa)
                    editor.putString("punto_emision",configuracion.puntoEmision)
                    editor.commit()
                    finish()
                }catch (e: Exception){
                    Log.e("HTTP BSX", e.message.toString());
                }

            }
        }*/
    }
}