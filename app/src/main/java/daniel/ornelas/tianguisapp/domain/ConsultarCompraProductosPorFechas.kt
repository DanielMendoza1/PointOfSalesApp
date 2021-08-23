package daniel.ornelas.tianguisapp.domain

import androidx.lifecycle.LiveData
import daniel.ornelas.tianguisapp.data.model.CompraProductosPair
import daniel.ornelas.tianguisapp.data.repository.CompraRepository

class ConsultarCompraProductosPorFechas(private val compraRepository: CompraRepository) {

    operator fun invoke(fechaDesde: String, fechaHasta: String): LiveData<List<CompraProductosPair>>{
        return compraRepository.obtenerComprasPorFechas(fechaDesde,  fechaHasta)
    }

}