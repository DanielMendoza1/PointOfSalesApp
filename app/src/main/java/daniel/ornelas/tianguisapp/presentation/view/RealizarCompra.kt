package daniel.ornelas.tianguisapp.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import daniel.ornelas.tianguisapp.data.model.CompraProductoCrossRef
import daniel.ornelas.tianguisapp.databinding.ActivityRealizarCompraBinding
import daniel.ornelas.tianguisapp.data.model.CompraModel
import daniel.ornelas.tianguisapp.data.model.ProductoModel
import daniel.ornelas.tianguisapp.presentation.view.adapter.ProductosAdaptador
import daniel.ornelas.tianguisapp.presentation.view.adapter.ProductosAgregarCompraAdapter
import daniel.ornelas.tianguisapp.presentation.view.interfaces.CallBackInterface
import daniel.ornelas.tianguisapp.presentation.viewModel.CompraViewModel
import daniel.ornelas.tianguisapp.presentation.viewModel.ProductoViewModel
import daniel.ornelas.tianguisapp.presentation.viewModel.RealizarCompraViewModel
import daniel.ornelas.tianguisapp.util.Operacion
import java.util.*
import kotlin.collections.HashMap
import daniel.ornelas.tianguisapp.util.dateAString

class RealizarCompra : AppCompatActivity(), CallBackInterface {

    private lateinit var binding: ActivityRealizarCompraBinding

    private lateinit var productoViewModel: ProductoViewModel

    private lateinit var compraViewModel: CompraViewModel

    private lateinit var realizarCompraViewModel: RealizarCompraViewModel

    private lateinit var productosAgregar: HashMap<Long, ProductoModel>

    private lateinit var adaptador: ProductosAdaptador

    private lateinit var adaptadorListado: ProductosAgregarCompraAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRealizarCompraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //ViewModels
        realizarCompraViewModel = ViewModelProvider(this).get(RealizarCompraViewModel::class.java)
        productoViewModel = ViewModelProvider(this).get(ProductoViewModel::class.java)
        compraViewModel = ViewModelProvider(this).get(CompraViewModel::class.java)
        productosAgregar = HashMap()


        //Recyclerview
        adaptador = ProductosAdaptador(this)
        val recyclerView = binding.recyclerViewProductosCompraStock
        recyclerView.adapter = adaptador
        recyclerView.layoutManager = GridLayoutManager(baseContext, 4)
        recyclerView.setHasFixedSize(true)

        adaptadorListado = ProductosAgregarCompraAdapter()
        val recyclerViewListado =  binding.recyclerViewProductosPreCompra
        recyclerViewListado.adapter = adaptadorListado
        recyclerViewListado.layoutManager = LinearLayoutManager(baseContext)
        recyclerViewListado.setHasFixedSize(true)

        //Eventos
        binding.btnAgregarProducto.setOnClickListener {
        }

        binding.btnRealizarCompra.setOnClickListener {
            agregarCompra()
        }
        /*
        val producto = ProductoModel(1,"Papitas", 15f, 10)
        val producto2 = ProductoModel(2,"Banana", 4f, 5)

        productoViewModel.agregarProducto(producto)
        productoViewModel.agregarProducto(producto2)*/

         cargarProductos()

    }

    private fun cargarProductos() {
        realizarCompraViewModel.obtenerTodosProductos().observe(this, { productos ->
            adaptador.setDatos(productos)
        })
    }

    private fun agregarCompra(){
        realizarCompraViewModel.agregarCompra(productosAgregar)
        Toast.makeText(this, "Se ha registrado la compra", Toast.LENGTH_SHORT).show()
    }

    override fun obtenerCallBack(resultado: HashMap<String, Any>) {
        val operacion = resultado["operacion"]

        if (operacion == Operacion.COMPRA_SENCILLA){
            val cantidad = resultado["cantidad"] as Long
            val producto = resultado["producto"] as ProductoModel

            productosAgregar[cantidad] = producto
            agregarProductoListadoCompra()

        } else if (operacion == Operacion.COMPRA_DESCUENTO){
            val cantidad = resultado["cantidad"]
            val descuento = resultado["descuento"]
            val producto = resultado["producto"]

        }

    }

    private fun agregarProductoListadoCompra(){
        adaptadorListado.setDatos(productosAgregar.values.toList(), productosAgregar.keys.toList())
    }
}