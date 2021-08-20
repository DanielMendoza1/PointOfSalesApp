package daniel.ornelas.tianguisapp.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import daniel.ornelas.tianguisapp.data.model.CompraProductoCrossRef
import daniel.ornelas.tianguisapp.databinding.ActivityRealizarCompraBinding
import daniel.ornelas.tianguisapp.data.model.CompraModel
import daniel.ornelas.tianguisapp.data.model.ProductoModel
import daniel.ornelas.tianguisapp.presentation.view.adapter.ProductosAdaptador
import daniel.ornelas.tianguisapp.presentation.viewModel.CompraViewModel
import daniel.ornelas.tianguisapp.presentation.viewModel.ProductoViewModel
import java.util.*
import kotlin.collections.HashMap
import daniel.ornelas.tianguisapp.util.dateAString

class RealizarCompra : AppCompatActivity() {

    private lateinit var binding: ActivityRealizarCompraBinding

    private lateinit var productoViewModel: ProductoViewModel

    private lateinit var compraViewModel: CompraViewModel

    private lateinit var productosAgregar: HashMap<Long, ProductoModel>

    private lateinit var adaptador: ProductosAdaptador



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRealizarCompraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //ViewModels
        productoViewModel = ViewModelProvider(this).get(ProductoViewModel::class.java)
        compraViewModel = ViewModelProvider(this).get(CompraViewModel::class.java)
        productosAgregar = HashMap()


        //Recyclerview
        adaptador = ProductosAdaptador()
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adaptador
        recyclerView.layoutManager = GridLayoutManager(baseContext, 4)
        recyclerView.setHasFixedSize(true)

        //Eventos
        binding.btnAgregarProducto.setOnClickListener {
            agregarProducto()
        }

        binding.btnRealizarCompra.setOnClickListener {
            agregarCompra()
        }

    }

    private fun agregarProducto(){

        if (!binding.campoNombre.text.isNullOrEmpty() && !binding.campoPrecioUnitario.text.isNullOrEmpty() && !binding.campoCantidad.text.isNullOrEmpty()) {

            val nombre = binding.campoNombre.text.toString()
            val precioUnitario = binding.campoPrecioUnitario.text.toString().toFloat()
            val cantidad = binding.campoCantidad.text.toString().toInt()

            val producto = ProductoModel(0, nombre, precioUnitario, cantidad)
            productoViewModel.agregarProducto(producto).observe( this, { id ->
                productosAgregar[id] = producto
                adaptador.setDatos(ArrayList(productosAgregar.values))
            })
            Toast.makeText(this, "Se ha agregado un nuevo producto", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(this, "Debes llener todos los campos de producto", Toast.LENGTH_LONG).show()

        }

    }

    private fun agregarCompra(){

        var totalCompra = 0.0f
        var cantidadProductosCompra = 0

        for (producto in productosAgregar.values){
            totalCompra += (producto.cantidad * producto.precioUnitario)
            cantidadProductosCompra += producto.cantidad
        }

        var compra = CompraModel(0, cantidadProductosCompra, totalCompra, dateAString(Date()))
        compraViewModel.agregarCompra(compra).observe(this, { idCompra ->
            for (idProducto in productosAgregar.keys){
                val detalleCompra = CompraProductoCrossRef(idProducto, idCompra)
                compraViewModel.agregarCompraConProductos(detalleCompra)
            }
        })

        Toast.makeText(this, "Se ha registrado la compra", Toast.LENGTH_SHORT).show()

    }

}