package daniel.ornelas.tianguisapp.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import daniel.ornelas.tianguisapp.R
import daniel.ornelas.tianguisapp.data.model.ProductoModel

class ProductosAgregarCompra: RecyclerView.Adapter<ProductosAgregarCompra.MyViewHolder>() {

    private var listaProductos = emptyList<ProductoModel>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.producto_agregar_compra_desplegable, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val productoActual = listaProductos[position]

        val nombre = holder.itemView.findViewById(R.id.nombre_producto_agregar) as TextView
        val cantidad = holder.itemView.findViewById(R.id.cantidad_producto_agregar) as TextView
        val total = holder.itemView.findViewById(R.id.total_producto_agregar) as TextView

        nombre.text = productoActual.nombre
        cantidad
    }

    override fun getItemCount(): Int {
        return listaProductos.size
    }

    fun setDatos(producto: List<ProductoModel>){
        this.listaProductos = producto
        notifyDataSetChanged()
    }

}