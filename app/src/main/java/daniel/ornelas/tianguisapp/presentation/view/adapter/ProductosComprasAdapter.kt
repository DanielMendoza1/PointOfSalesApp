package daniel.ornelas.tianguisapp.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import daniel.ornelas.tianguisapp.R
import daniel.ornelas.tianguisapp.data.model.ProductoModel

class ProductosComprasAdapter: RecyclerView.Adapter<ProductosComprasAdapter.MyViewHolder>() {

    private var listaComprasProductos = emptyList<ProductoModel>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.productos_compra_desplegable, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val productoActual = listaComprasProductos[position]

        val codigo = holder.itemView.findViewById(R.id.campo_id_producto_table) as TextView
        val nombre = holder.itemView.findViewById(R.id.campo_nombre_producto_table) as TextView
        val precioUnitario = holder.itemView.findViewById(R.id.campo_preciouni_producto_table) as TextView

        codigo.text = productoActual.idProducto.toString()
        nombre.text = productoActual.nombre
        precioUnitario.text = productoActual.precioUnitario.toString()

    }

    override fun getItemCount(): Int {
        return listaComprasProductos.size
    }

    fun setDatos(productos: List<ProductoModel>){
        this.listaComprasProductos = productos
        notifyDataSetChanged()
    }


}