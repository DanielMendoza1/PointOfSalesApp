package daniel.ornelas.tianguisapp.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import daniel.ornelas.tianguisapp.R
import daniel.ornelas.tianguisapp.data.model.ProductoModel

class ProductosAdaptador: RecyclerView.Adapter<ProductosAdaptador.MyViewHolder>() {

    private var listaProductos = emptyList<ProductoModel>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.producto_desplegable, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val productoActual = listaProductos[position]


        val nombre = holder.itemView.findViewById(R.id.nombre_producto_view) as TextView
        var imagen = holder.itemView.findViewById(R.id.imagen_producto_view) as ImageView

        nombre.text = productoActual.nombre
    }

    override fun getItemCount(): Int {
        return listaProductos.size
    }

    fun setDatos(producto: List<ProductoModel>){
        this.listaProductos = producto
        notifyDataSetChanged()
    }
}