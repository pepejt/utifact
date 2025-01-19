import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ec.uti.edu.utifact.R
import ec.uti.edu.utifact.ui.Cliente

// Modelo Cliente
data class Cliente(
    val nombre: String,
    val cedula: String,
    val telefono: String,
    val correo: String
)

// ClienteAdapter para RecyclerView
class ClienteAdapter(private val clienteList: MutableList<Cliente>) :
    RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder>() {

    // ViewHolder: Representa un elemento del RecyclerView
    class ClienteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreTextView: TextView = itemView.findViewById(R.id.txtnombre)
        val cedulaTextView: TextView = itemView.findViewById(R.id.txtcedula)
        val telefonoTextView: TextView = itemView.findViewById(R.id.txttelefono)
        val correoTextView: TextView = itemView.findViewById(R.id.txtcorreo)
    }

    // Infla el layout para cada elemento del RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClienteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cliente, parent, false)
        return ClienteViewHolder(view)
    }

    // Vincula los datos del cliente a los elementos de la vista
    override fun onBindViewHolder(holder: ClienteViewHolder, position: Int) {
        val cliente = clienteList[position]
        holder.nombreTextView.text = cliente.nombre
        holder.cedulaTextView.text = cliente.cedula
        holder.telefonoTextView.text = cliente.telefono
        holder.correoTextView.text = cliente.correo
    }

    // Devuelve la cantidad de elementos en la lista
    override fun getItemCount(): Int {
        return clienteList.size
    }

    // Método para actualizar la lista del adaptador
    fun updateData(newList: List<Cliente>) {
        clienteList.clear()
        clienteList.addAll(newList)
        notifyDataSetChanged()
    }
}
