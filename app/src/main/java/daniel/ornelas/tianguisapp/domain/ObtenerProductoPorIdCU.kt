package daniel.ornelas.tianguisapp.domain

import androidx.lifecycle.LiveData
import daniel.ornelas.tianguisapp.data.model.ProductoModel
import daniel.ornelas.tianguisapp.data.repository.ProductoRepository

class ObtenerProductoPorIdCU(private val productoRepository: ProductoRepository) {

    operator fun invoke(id: Int): LiveData<ProductoModel>{
        return productoRepository.obtenerProductoPorId(id)
    }

}