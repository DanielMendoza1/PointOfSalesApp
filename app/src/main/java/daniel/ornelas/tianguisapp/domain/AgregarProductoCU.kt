package daniel.ornelas.tianguisapp.domain

import daniel.ornelas.tianguisapp.data.model.ProductoModel
import daniel.ornelas.tianguisapp.data.repository.ProductoRepository

class AgregarProductoCU(private val productoRepository: ProductoRepository) {

    suspend operator fun invoke(productoModel: ProductoModel): Long {
        return productoRepository.agregarProducto(productoModel)
    }

}