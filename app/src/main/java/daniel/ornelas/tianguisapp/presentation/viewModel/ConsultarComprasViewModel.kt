package daniel.ornelas.tianguisapp.presentation.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import daniel.ornelas.tianguisapp.data.database.TianguisDB
import daniel.ornelas.tianguisapp.data.model.CompraProductosPair
import daniel.ornelas.tianguisapp.data.repository.CompraRepository
import daniel.ornelas.tianguisapp.domain.ConsultarComprasProductosCU

class ConsultarComprasViewModel(application: Application): AndroidViewModel(application) {

    private val compraRepository: CompraRepository
    private val consultarComprasCU: ConsultarComprasProductosCU
    val obtenerTodasComprasConProductos: LiveData<List<CompraProductosPair>>


    init {
        val comprasDao = TianguisDB.obtenerBD(application).compraDao()
        compraRepository = CompraRepository(comprasDao)
        consultarComprasCU = ConsultarComprasProductosCU(compraRepository)
        obtenerTodasComprasConProductos = consultarComprasCU.invoke()
    }

    fun desplegarProductos(){

    }


}