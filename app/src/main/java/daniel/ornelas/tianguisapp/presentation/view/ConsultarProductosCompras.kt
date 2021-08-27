package daniel.ornelas.tianguisapp.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import daniel.ornelas.tianguisapp.databinding.ActivityConsultarProductosComprasBinding
import daniel.ornelas.tianguisapp.presentation.view.adapter.ProductosComprasAdapter
import daniel.ornelas.tianguisapp.presentation.viewModel.ConsultarProductosComprasViewModel

class ConsultarProductosCompras : AppCompatActivity() {

    private lateinit var binding: ActivityConsultarProductosComprasBinding

    private lateinit var consultarProductosComprasViewModel: ConsultarProductosComprasViewModel

    private lateinit var adaptador: ProductosComprasAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConsultarProductosComprasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //ViewModels
        consultarProductosComprasViewModel = ViewModelProvider(this).get(ConsultarProductosComprasViewModel::class.java)

        //RecyclerView
        adaptador = ProductosComprasAdapter()
        val recyclerView = binding.recyclerViewConsultarProductosCompras
        recyclerView.adapter = adaptador
        recyclerView.layoutManager = LinearLayoutManager(baseContext)

       // desplegarProductos()
    }
/*
    fun desplegarProductos(){
        val id = intent.getLongExtra("idCompra",-1)
        consultarProductosComprasViewModel.obtenerProductosCompraPorId(id).observe(this, { compras ->
        adaptador.setDatos(compras.productos)})

    }
*/
}