package ec.uti.edu.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ec.uti.edu.myapplication.R
import ec.uti.edu.utifact.entity.Factura

class ListaFactAdapter(
    private val reportes:List<Factura>,
    private val onEditClick: (Factura) -> Unit
): RecyclerView.Adapter<ListaFactAdapter.ReporteViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReporteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_listadofact, parent, false)
        return ReporteViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReporteViewHolder, position: Int) {
        val reporte = reportes[position]
        holder.bind(reporte)
        holder.itemView.findViewById<ImageButton>(R.id.imgEdit).setOnClickListener {
            onEditClick(reporte)
        }
    }

    override fun getItemCount(): Int = reportes.size

    class ReporteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(reporte: Factura){
            itemView.findViewById<TextView>(R.id.txtFactura).text = "#00${reporte.id}"
            itemView.findViewById<TextView>(R.id.txtFactura).text = "${reporte.cliente}"
            itemView.findViewById<TextView>(R.id.txtFactura).text = "#00${reporte.fecha}"
            itemView.findViewById<TextView>(R.id.txtFactura).text = "#00${reporte.total}"
        }
    }
}