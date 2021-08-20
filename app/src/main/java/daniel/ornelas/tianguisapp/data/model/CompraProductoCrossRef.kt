package daniel.ornelas.tianguisapp.data.model

import androidx.room.Entity

@Entity(primaryKeys = ["idProducto", "idCompra"])
data class CompraProductoCrossRef(
        val idProducto: Long,
        val idCompra: Long) {
}