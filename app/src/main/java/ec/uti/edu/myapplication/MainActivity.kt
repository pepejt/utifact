package ec.uti.edu.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import ec.edu.uti.juego.dao.AppDatabase
import ec.uti.edu.myapplication.adapter.ListaFactAdapter
import ec.uti.edu.myapplication.obj.Sala
import ec.uti.edu.utifact.entity.Factura

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerview: RecyclerView
    private lateinit var facturas: ListaFactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar base de datos
        val db = Room.databaseBuilder(
            this,
            AppDatabase::class.java, "listado-factura"
        ).allowMainThreadQueries().build()
        val salaDao = db.salaDao()

        // Llenar RecyclerView con datos de la base de datos
        recyclerview = findViewById(R.id.recyclerViewFacturas)
        recyclerview.adapter = ListaFactAdapter(salaDao.getAll())
        recyclerview.layoutManager = LinearLayoutManager(this)

        facturas = ListaFactAdapter(
            salaDao,
            onClickEdit = { facturas ->
                // Lógica para editar cliente
                Toast.makeText(this, "Editar: ${facturas.id}", Toast.LENGTH_SHORT).show()
            }
        )



        findViewById<ImageButton>(R.id.btnAdd).setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

    }
}
