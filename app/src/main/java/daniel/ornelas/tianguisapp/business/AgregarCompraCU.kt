package daniel.ornelas.tianguisapp.domain

import androidx.lifecycle.LiveData
import daniel.ornelas.tianguisapp.data.model.CompraModel
import daniel.ornelas.tianguisapp.data.model.CompraProductoCrossRef
import daniel.ornelas.tianguisapp.data.model.ProductoModel
import daniel.ornelas.tianguisapp.data.repository.CompraRepository
import daniel.ornelas.tianguisapp.util.dateAString
import java.util.*
import kotlin.collections.HashMap

class AgregarCompraCU(private val compraRepository: CompraRepository) {

    suspend operator fun invoke(productosAgregar: HashMap<Long, ProductoModel>){

        var totalCompra = 0.0f
        var cantidadProductosCompra = 0L

        for (producto in productosAgregar){
            totalCompra += (producto.key * producto.value.precioUnitario)
            cantidadProductosCompra += producto.key
        }

        var compra = CompraModel(0, cantidadProductosCompra, totalCompra, dateAString(Date()))
        val idCompra = compraRepository.agregarCompra(compra)
        for (producto in productosAgregar){
            val detalleCompra = CompraProductoCrossRef(producto.value.idProducto, idCompra, (producto.key * producto.value.precioUnitario), producto.key)
            compraRepository.agregarCompraConProductos(detalleCompra)
        }
    }
}