package daniel.ornelas.tianguisapp.domain

import androidx.lifecycle.LiveData
import daniel.ornelas.tianguisapp.data.model.CompraConProductos
import daniel.ornelas.tianguisapp.data.repository.CompraRepository

class ConsultarComprasProductosCU(private val compraRepository: CompraRepository) {


    operator fun invoke(): LiveData<List<CompraConProductos>>{

        return compraRepository.obtenerComprasConProductos()

    }

}