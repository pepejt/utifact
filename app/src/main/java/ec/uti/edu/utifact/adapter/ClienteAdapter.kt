package ec.uti.edu.utifact.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ec.uti.edu.utifact.R
import ec.uti.edu.utifact.entity.Cliente

class ClienteAdapter(
    private val clientes: List<Cliente>,
    private val onEditClick: (Cliente) -> Unit,
    private val onDeleteClick: (Cliente) -> Unit
) : RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder>() {

    class ClienteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtCliente: TextView = view.findViewById(R.id.txtCliente)
        val btnEdit: ImageButton = view.findViewById(R.id.imgEdit)
        val btnDelete: ImageButton = view.findViewById(R.id.imgDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClienteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_clients, parent, false)
        return ClienteViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClienteViewHolder, position: Int) {
        val cliente = clientes[position]
        holder.txtCliente.text = cliente.nombre
        holder.btnEdit.setOnClickListener { onEditClick(cliente) }
        holder.btnDelete.setOnClickListener { onDeleteClick(cliente) }
    }

    override fun getItemCount(): Int = clientes.size
}
