package ec.uti.edu.utifact.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.preference.PreferenceManager
import androidx.room.Room
import ec.edu.uti.juego.dao.AppDatabase
import ec.uti.edu.utifact.R
import ec.uti.edu.utifact.entity.Configuracion


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.main_toolbar))

        val db = Room.databaseBuilder(
            this,
            AppDatabase::class.java, "utifact"
        ).allowMainThreadQueries().build()

        val configuracionDao = db.configuracionDao()
        val configuracion = configuracionDao.getById(1);
        if (configuracion == null){
            configuracionDao.insert(Configuracion(1,"1803728151001","BsxLabs","001-101"))
        }

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val editor: Editor = sharedPreferences.edit()
        editor.putString("ruc",configuracion.ruc)
        editor.putString("empresa",configuracion.empresa)
        editor.putString("punto_emision",configuracion.puntoEmision)
        editor.commit()

        sharedPreferences.all.forEach {
            Log.d("Preferences", "${it.key} -> ${it.value}")
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    fun onClickFacturar(v: View){
        startActivity(Intent(this, HelpActivity::class.java))
    }
    fun onClickVentas(v: View){
        startActivity(Intent(this, HelpActivity::class.java))
    }
    fun onClickProducto(v: View){
        startActivity(Intent(this, HelpActivity::class.java))
    }
    fun onClickCliente(v: View){
        startActivity(Intent(this, ClientesActivity::class.java))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection.
        return when (item.itemId) {
            R.id.mn_producto -> {
                true
            }
            R.id.mn_cliente -> {
                startActivity(Intent(this, ClientesActivity::class.java))
                true
            }
            R.id.mn_factura -> {
                true
            }
            R.id.mn_configuracion -> {
                startActivity(Intent(this, SettingActivity::class.java))
                true
            }
            R.id.mn_ayuda -> {
                startActivity(Intent(this, HelpActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}