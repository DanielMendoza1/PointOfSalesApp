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
        var cantidadProductosCompra = 0

        for (producto in productosAgregar.values){
            totalCompra += (producto.cantidad * producto.precioUnitario)
            cantidadProductosCompra += producto.cantidad
        }

        var compra = CompraModel(0, cantidadProductosCompra, totalCompra, dateAString(Date()))
        val idCompra = compraRepository.agregarCompra(compra)
        for (idProducto in productosAgregar.keys){
            val detalleCompra = CompraProductoCrossRef(idProducto, idCompra)
            compraRepository.agregarCompraConProductos(detalleCompra)
        }
    }
}