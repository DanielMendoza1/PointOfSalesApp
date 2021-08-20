package daniel.ornelas.tianguisapp.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import daniel.ornelas.tianguisapp.R
import daniel.ornelas.tianguisapp.data.model.CompraModel
import daniel.ornelas.tianguisapp.data.model.CompraProductosPair

class ComprasAdaptador: RecyclerView.Adapter<ComprasAdaptador.MyViewHolderCompras>() {

    private var listaCompras = emptyList<CompraProductosPair>()

    class MyViewHolderCompras(itemView: View): RecyclerView.ViewHolder(itemView){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderCompras {
        return MyViewHolderCompras(LayoutInflater.from(parent.context).inflate(R.layout.compra_desplegable, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolderCompras, position: Int) {
        val compraActual = listaCompras[position]

        val codigo = holder.itemView.findViewById(R.id.campo_id_table) as TextView
        val fecha = holder.itemView.findViewById(R.id.campo_fecha_table) as TextView
        val cantProducto = holder.itemView.findViewById(R.id.campo_cantidad_table) as TextView
        val total = holder.itemView.findViewById(R.id.campo_total_table) as TextView

        codigo.text = compraActual.compra.idCompra.toString()
        fecha.text = compraActual.compra.fecha
        cantProducto.text = compraActual.compra.cantProductos.toString()
        total.text = compraActual.compra.montoTotal.toString()

    }

    override fun getItemCount(): Int {
        return listaCompras.size
    }

    fun setDatos(compra: List<CompraProductosPair>){
        this.listaCompras = compra
        notifyDataSetChanged()
    }

}