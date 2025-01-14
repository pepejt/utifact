package com.example.moduloclientes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R

class ClienteAdapter(private val clienteList: List<Clientes.Cliente>) : RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder>() {

    // Clase ViewHolder para manejar las vistas
    class ClienteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreTextView: TextView = view.findViewById(R.id.nombreTextView)
        val apellidosTextView: TextView = view.findViewById(R.id.apellidosTextView)
        val correoTextView: TextView = view.findViewById(R.id.correoTextView)
        // Agregar otros TextViews según sea necesario
    }

    // Crear un nuevo ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClienteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cliente, parent, false)
        return ClienteViewHolder(view)
    }

    // Bind los datos del cliente a las vistas
    override fun onBindViewHolder(holder: ClienteViewHolder, position: Int) {
        val cliente = clienteList[position]
        holder.nombreTextView.text = cliente.Nombre
        holder.apellidosTextView.text = cliente.Apellidos
        holder.correoTextView.text = cliente.Correo
        // Asignar otros datos a sus correspondientes TextViews
    }

    // Retornar el tamaño de la lista de clientes
    override fun getItemCount(): Int {
        return clienteList.size
    }
}