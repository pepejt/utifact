package ec.uti.edu.utifact.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ec.uti.edu.utifact.R
import ec.uti.edu.utifact.entity.User


class UsuarioAdapter(
    private val usuarios: List<User>,
    private val onEditClick: (User) -> Unit,
    private val onDeleteClick: (User) -> Unit
) : RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UsuarioViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        val producto = usuarios[position]
        holder.bind(producto)
        holder.itemView.findViewById<ImageButton>(R.id.imgEdit).setOnClickListener {
            onEditClick(producto)
        }
        holder.itemView.findViewById<ImageButton>(R.id.imgDelete).setOnClickListener {
            onDeleteClick(producto)
        }
    }

    override fun getItemCount(): Int = usuarios.size

    class UsuarioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(usuario: User) {
            itemView.findViewById<TextView>(R.id.txtProducto).text = usuario.user
        }
    }
}