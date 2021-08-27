package daniel.ornelas.tianguisapp.data.model

import androidx.room.Entity

@Entity(primaryKeys = ["idProducto", "idCompra"] )
class CompraProductoCrossRef(
        val idProducto: Long,
        val idCompra: Long,
        val importe: Float,
        val cantidadProductoCompra: Long)
 {
}