package daniel.ornelas.tianguisapp.domain

import androidx.lifecycle.LiveData
import daniel.ornelas.tianguisapp.data.model.ProductoModel
import daniel.ornelas.tianguisapp.data.repository.ProductoRepository

class ObtenerTodosProductosCU(private val productoRepository: ProductoRepository) {

     operator fun invoke(): LiveData<List<ProductoModel>>{
        return productoRepository.obtenerTodosProductos()
    }

}