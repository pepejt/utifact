package ec.uti.edu.utifact.ui

import android.content.Intent
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.preference.PreferenceManager
import androidx.room.Room
import ec.edu.uti.juego.dao.AppDatabase
import ec.uti.edu.utifact.R
import ec.uti.edu.utifact.entity.Configuracion


class MainActivity : AppCompatActivity() {

    companion object {
        var TOKEN_API = ""
    }

    lateinit var cardLogin: CardView;
    lateinit var cardFacturar: CardView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.main_toolbar))
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        verificarLogin()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    fun onClickFacturar(v: View){
        startActivity(Intent(this, FacturacionActivity::class.java))
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
    fun onClickInicio(v: View){
        startActivity(Intent(this, LoginActivity::class.java))
    }
    fun verificarLogin(){
        cardLogin = findViewById(R.id.cardLogin)
        cardFacturar = findViewById(R.id.cardFacturar)
        cardLogin.visibility = View.GONE
        cardFacturar.visibility = View.GONE
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val map = sharedPreferences.all
        if (!map.containsKey("token")) {
            TOKEN_API = ""
            cardLogin.visibility = View.VISIBLE
        }else {
            TOKEN_API = map.get("token").toString()
            cardFacturar.visibility = View.VISIBLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection.
        return when (item.itemId) {
            /*R.id.mn_producto -> {
                true
            }
            R.id.mn_cliente -> {
                startActivity(Intent(this, ClientesActivity::class.java))
                true
            }
            R.id.mn_factura -> {
                true
            }*/
            R.id.mn_configuracion -> {
                startActivity(Intent(this, SettingActivity::class.java))
                true
            }
            /*R.id.mn_ayuda -> {
                startActivity(Intent(this, HelpActivity::class.java))
                true
            }*/
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        verificarLogin()
    }
}