package ec.uti.edu.utifact.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ec.uti.edu.utifact.R
import ec.uti.edu.utifact.entity.Producto


class ProductoAdapter(
    private val productos: List<Producto>,
    private val onEditClick: (Producto) -> Unit,
    private val onDeleteClick: (Producto) -> Unit
) : RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_productos, parent, false)
        return ProductoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = productos[position]
        holder.bind(producto)
        holder.itemView.findViewById<ImageButton>(R.id.imgEditP).setOnClickListener {
            onEditClick(producto)
        }
        holder.itemView.findViewById<ImageButton>(R.id.imgDeleteP).setOnClickListener {
            onDeleteClick(producto)
        }
    }

    override fun getItemCount(): Int = productos.size

    class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(producto: Producto) {
            itemView.findViewById<TextView>(R.id.txtProducto).text = producto.nombre
            itemView.findViewById<TextView>(R.id.txtStock).text = producto.stock.toString()
        }
    }
}