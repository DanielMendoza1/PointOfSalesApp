package daniel.ornelas.tianguisapp.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import daniel.ornelas.tianguisapp.databinding.ActivityConsultarComprasBinding
import daniel.ornelas.tianguisapp.presentation.view.adapter.ComprasAdaptador
import daniel.ornelas.tianguisapp.presentation.viewModel.CompraViewModel
import daniel.ornelas.tianguisapp.util.Operacion

class ConsultarCompras : AppCompatActivity() {

    private lateinit var binding: ActivityConsultarComprasBinding

    private lateinit var comprasViewModel: CompraViewModel

    private lateinit var adaptador: ComprasAdaptador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConsultarComprasBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //ViewModels
        comprasViewModel = ViewModelProvider(this).get(CompraViewModel::class.java)

        //RecyclerView
        adaptador = ComprasAdaptador()
        val recyclerView = binding.recyclerViewConsultarCompras
        recyclerView.adapter = adaptador
        recyclerView.layoutManager = LinearLayoutManager(baseContext)
        recyclerView.setHasFixedSize(true)

        desplegarProductos()

    }

    fun desplegarProductos(){

        val operacion = intent.getSerializableExtra("operacion")
        if(operacion == Operacion.TODAS){
            comprasViewModel.obtenerTodasComprasConProductos.observe(this, { compras ->
                val comprasUnicas = compras.distinctBy { it.compra.idCompra }
                adaptador.setDatos(comprasUnicas)
            })
        } else if( operacion == Operacion.FECHAS){
            val fechaDesde = intent.getStringExtra("fechaDesde")
            val fechaHasta = intent.getStringExtra("fechaHasta")

            comprasViewModel.obtenerProductosCompraPorFecha(fechaDesde!!, fechaHasta!!).observe(this, { compras ->
                val comprasUnicas = compras.distinctBy { it.compra.idCompra }
                adaptador.setDatos(comprasUnicas)
            })

        }

    }
}