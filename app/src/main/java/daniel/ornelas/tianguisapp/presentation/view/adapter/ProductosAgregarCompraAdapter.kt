package daniel.ornelas.tianguisapp.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import daniel.ornelas.tianguisapp.R
import daniel.ornelas.tianguisapp.data.model.ProductoModel

class ProductosAgregarCompraAdapter: RecyclerView.Adapter<ProductosAgregarCompraAdapter.MyViewHolder>() {

    private var productosAgregar = emptyList<ProductoModel>()
    private var cantidades = emptyList<Long>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.producto_agregar_compra_desplegable, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val productoActual = productosAgregar[position]
        val cantidadActual = cantidades[position]

        val nombre = holder.itemView.findViewById(R.id.nombre_producto_agregar) as TextView
        val cantidad = holder.itemView.findViewById(R.id.cantidad_producto_agregar) as TextView
        val total = holder.itemView.findViewById(R.id.total_producto_agregar) as TextView

        nombre.text = productoActual.nombre
        cantidad.text = cantidadActual.toString()
        total.text = (cantidadActual * productoActual.precioUnitario).toString()
    }

    override fun getItemCount(): Int {
        return productosAgregar.size
    }

    fun setDatos(producto: List<ProductoModel>, cantidades: List<Long>){
        this.productosAgregar = producto
        this.cantidades = cantidades
        notifyDataSetChanged()
    }

}