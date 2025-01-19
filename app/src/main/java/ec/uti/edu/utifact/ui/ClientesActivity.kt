package ec.uti.edu.utifact.ui

import ClienteAdapter
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ec.uti.edu.utifact.R

// Modelo de Cliente
data class Cliente(
    var nombre: String,
    var telefono: String,
    var direccion: String,
    var correo: String,
    var cedula: String
)

class ClientesActivity : AppCompatActivity() {
    // Lista mutable de clientes
    private val clienteList = mutableListOf<Cliente>()
    private lateinit var adapter: ClienteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_clientes)

        // Inicializar RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.rcvClientes)
        adapter = ClienteAdapter(clienteList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        // Agregar datos de ejemplo a la lista
        clienteList.add(
            Cliente(
                nombre = "Andres Lopez",
                telefono = "0960849423",
                direccion = "Av. Ambato",
                correo = "andresinose@gmail.com",
                cedula = "1850631688"
            )
        )
        clienteList.add(
            Cliente(
                nombre = "Eric Mera",
                telefono = "098854741",
                direccion = "Salcedo",
                correo = "black@gmail.com",
                cedula = "1802898690"
            )
        )
        clienteList.add(
            Cliente(
                nombre = "Christian Zurita",
                telefono = "0984872421",
                direccion = "Ambato",
                correo = "zuricata@gmail.com",
                cedula = "1800185749"
            )
        )

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Notificar cambios al adaptador
        adapter.notifyDataSetChanged()
    }
    // Función para registrar un cliente
    fun registrarCliente(view: View) {
        // Obtener los campos de texto desde la interfaz
        val nombreEditText = findViewById<EditText>(R.id.txtnombre)
        val telefonoEditText = findViewById<EditText>(R.id.txttelefono)
        val direccionEditText = findViewById<EditText>(R.id.txtdir)
        val correoEditText = findViewById<EditText>(R.id.txtcorreo)
        val cedulaEditText = findViewById<EditText>(R.id.txtcedula)

        // Leer los valores ingresados por el usuario
        val nombre = nombreEditText.text.toString()
        val telefono = telefonoEditText.text.toString()
        val direccion = direccionEditText.text.toString()
        val correo = correoEditText.text.toString()
        val cedula = cedulaEditText.text.toString()

        // Validar que los campos no estén vacíos
        if (nombre.isNotEmpty() && telefono.isNotEmpty() && direccion.isNotEmpty() && correo.isNotEmpty() && cedula.isNotEmpty()) {
            // Crear un nuevo cliente y agregarlo a la lista
            val nuevoCliente = Cliente(nombre, telefono, direccion, correo, cedula)

            // Agregar el cliente a la lista y notificar al adaptador
            clienteList.add(nuevoCliente)
            adapter.notifyItemInserted(clienteList.size - 1)

            // Confirmación
            Toast.makeText(this, "Cliente registrado exitosamente", Toast.LENGTH_SHORT).show()

            // Limpiar los campos de texto
            nombreEditText.text.clear()
            telefonoEditText.text.clear()
            direccionEditText.text.clear()
            correoEditText.text.clear()
            cedulaEditText.text.clear()
        } else {
            // Mostrar un mensaje si falta algún dato
            Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show()
        }
    }

    fun onBuscarClick(view: View) {
        // Lógica para el botón de búsqueda
        val buscar = findViewById<EditText>(R.id.etFacturaNumber).text.toString()
        if (buscar.isNotEmpty()) {
            // Filtrar la lista de clientes por coincidencias en la cédula
            val listaFiltrada = clienteList.filter { cliente ->
                cliente.cedula.contains(buscar, ignoreCase = true)
            }

            // Actualizar el adaptador con la lista filtrada
            adapter.updateData(listaFiltrada)

            // Mostrar mensaje si no se encontraron resultados
            if (listaFiltrada.isEmpty()) {
                Toast.makeText(this, "No se encontraron resultados", Toast.LENGTH_SHORT).show()
            }
        } else {
            // Restaurar la lista completa si no hay texto en el campo de búsqueda
            adapter.updateData(clienteList)
        }
    }
}