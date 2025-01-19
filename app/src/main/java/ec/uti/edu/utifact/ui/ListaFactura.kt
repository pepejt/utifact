package ec.uti.edu.utifact.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moduloclientes.R
import ec.uti.edu.utifact.adapter.ListaFactAdapter
import ec.uti.edu.utifact.entity.Factura

class ListaFactura : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lista_factura)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Datos de ejemplo para la lista de facturas
        val facturas = listOf(
            Factura(id = 1, cliente = "Andrés López", fecha = "2025-01-01", total = 120.50),
            Factura(id = 2, cliente = "Erick Mera", fecha = "2025-01-02", total = 240.99),
            Factura(id = 3, cliente = "Christian Zurita", fecha = "2025-01-03", total = 80.75),
            Factura(id = 4, cliente = "María Pérez", fecha = "2025-01-04", total = 150.00)
        )

        // Inicializar el RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.rcvLista)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ListaFactAdapter(facturas)
    }

}