package daniel.ornelas.tianguisapp.presentation.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import daniel.ornelas.tianguisapp.data.database.TianguisDB
import daniel.ornelas.tianguisapp.data.model.CompraProductosPair
import daniel.ornelas.tianguisapp.data.repository.CompraRepository
import daniel.ornelas.tianguisapp.domain.ConsultarCompraProductosPorIdCU

class ConsultarProductosComprasViewModel(application: Application): AndroidViewModel(application) {

    private val compraRepository: CompraRepository
    private val consultarCompraProductosPorIdCU: ConsultarCompraProductosPorIdCU


    init {
        val comprasDao = TianguisDB.obtenerBD(application).compraDao()
        compraRepository = CompraRepository(comprasDao)
        consultarCompraProductosPorIdCU = ConsultarCompraProductosPorIdCU(compraRepository)
    }


    fun obtenerProductosCompraPorId(id: Long): LiveData<CompraProductosPair>{
        return consultarCompraProductosPorIdCU.invoke(id)
    }

}