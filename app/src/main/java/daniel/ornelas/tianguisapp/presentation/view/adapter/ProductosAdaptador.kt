package daniel.ornelas.tianguisapp.presentation.view.adapter

import android.app.Dialog
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import daniel.ornelas.tianguisapp.R
import daniel.ornelas.tianguisapp.data.model.ProductoModel
import daniel.ornelas.tianguisapp.presentation.view.interfaces.CallBackInterface
import daniel.ornelas.tianguisapp.util.Operacion
import java.util.*
import kotlin.collections.HashMap

class ProductosAdaptador(private val callBackInterface: CallBackInterface): RecyclerView.Adapter<ProductosAdaptador.MyViewHolder>() {

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

        holder.itemView.setOnClickListener {
            detallesProductoAgregar(it, productoActual)
        }
    }

    override fun getItemCount(): Int {
        return listaProductos.size
    }

    fun setDatos(producto: List<ProductoModel>){
        this.listaProductos = producto
        notifyDataSetChanged()
    }

    fun detallesProductoAgregar(view: View, producto: ProductoModel){

        var dialog =  Dialog(view.context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.detalle_producto_dialog)
        val window = dialog.window
        window?.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)

        val campoCantidad = dialog.findViewById(R.id.campo_cantidad_dialog) as EditText
        val campoDescuento = dialog.findViewById(R.id.campo_descuento_dialog) as EditText
        val btnCancelar = dialog.findViewById(R.id.btn_cancelar_producto_dialog) as Button
        val btnAgregar = dialog.findViewById(R.id.btn_agregar_producto_dialog) as Button


        //Eventos elementos dialog
        btnCancelar.setOnClickListener {
            dialog.dismiss()
        }

        btnAgregar.setOnClickListener {
            val resultado = HashMap<String, Any>()

            if(!campoCantidad.text.isNullOrEmpty() && campoDescuento.text.isNullOrEmpty()){
                resultado["operacion"] = Operacion.COMPRA_SENCILLA
                resultado["cantidad"] = campoCantidad.text.toString().toLong()
                resultado["producto"] = producto
                dialog.dismiss()
                callBackInterface.obtenerCallBack(resultado)

            } else if (!campoCantidad.text.isNullOrEmpty() && !campoDescuento.text.isNullOrEmpty()){
                resultado["operacion"] = Operacion.COMPRA_DESCUENTO
                resultado["cantidad"] = campoCantidad.text.toString().toLong()
                resultado["descuento"] = campoDescuento.text.toString().toLong()
                resultado["producto"] = producto
                dialog.dismiss()
                callBackInterface.obtenerCallBack(resultado)
            } else {
                Toast.makeText(view.context, "Debe llenar el campo de Cantidad", Toast.LENGTH_SHORT).show()
            }
        }

        dialog.show()

    }
}