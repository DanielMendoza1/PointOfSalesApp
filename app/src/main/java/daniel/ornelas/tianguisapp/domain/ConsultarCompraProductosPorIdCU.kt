package daniel.ornelas.tianguisapp.domain

import androidx.lifecycle.LiveData
import daniel.ornelas.tianguisapp.data.model.CompraConProductos
import daniel.ornelas.tianguisapp.data.repository.CompraRepository

class ConsultarCompraProductosPorIdCU(private val compraRepository: CompraRepository) {

    operator fun invoke(id: Long):  LiveData<CompraConProductos>{
        return compraRepository.obtenerCompraConProductosPorId(id)
    }

}