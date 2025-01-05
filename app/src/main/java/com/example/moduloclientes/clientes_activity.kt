package com.example.moduloclientes

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class ClientesActivity : AppCompatActivity() {

    val clienteArray = mutableListOf(
        Clientes.Cliente().apply {
            Nombre = "Andres"
            Apellido = "Lopez"
            Telefono = "0960849423"
            FechaNacimiento = "29/09/2000"
            Direccion = "Av. Ambato"
            Correo = "andresinose@gmail.com"
            Cedula = "1850631688"
        },
        Clientes.Cliente().apply {
            Nombre = "Eric"
            Apellido = "Mera"
            Telefono = "098854741"
            FechaNacimiento = "10/07/1998"
            Direccion = "Salcedo"
            Correo = "black@gmail.com"
            Cedula = "1802898690"
        },
        Clientes.Cliente().apply {
            Nombre = "Christian"
            Apellido = "Zurita"
            Telefono = "0984872421"
            FechaNacimiento = "05/07/1978"
            Direccion = "Ambato"
            Correo = "zuricata@gmail.com"
            Cedula = "1800185749"
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_clientes)
    }

    // Función para registrar un cliente
    fun registrarCliente(view: View) {
        // Obtener los campos de texto desde la interfaz
        val nombreEditText = findViewById<EditText>(R.id.et_nombres)
        val telefonoEditText = findViewById<EditText>(R.id.et_telefono)
        val direccionEditText = findViewById<EditText>(R.id.et_direccion)
        val correoEditText = findViewById<EditText>(R.id.et_correo)
        val cedulaEditText = findViewById<EditText>(R.id.et_cedula)

        // Leer los valores ingresados por el usuario
        val nombre = nombreEditText.text.toString()
        val telefono = telefonoEditText.text.toString()
        val direccion = direccionEditText.text.toString()
        val correo = correoEditText.text.toString()
        val cedula = cedulaEditText.text.toString()

        // Validar que los campos no estén vacíos
        if (nombre.isNotEmpty() && telefono.isNotEmpty() && direccion.isNotEmpty() && correo.isNotEmpty() && cedula.isNotEmpty()) {
            // Crear un nuevo cliente y agregarlo a la lista
            val nuevoCliente = Clientes.Cliente().apply {
                Nombre = nombre
                Telefono = telefono
                Direccion = direccion
                Correo = correo
                Cedula = cedula
            }

            // Agregar el cliente a la lista mutable
            clienteArray.add(nuevoCliente)

            // Confirmación
            Toast.makeText(this, "Cliente registrado exitosamente", Toast.LENGTH_SHORT).show()
        } else {
            // Mostrar un mensaje si falta algún dato
            Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show()
        }
    }
}
