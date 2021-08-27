package daniel.ornelas.tianguisapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "compras")
data class CompraModel(
        @PrimaryKey(autoGenerate = true)
        val idCompra: Long,
        val cantProductos: Long,
        val montoTotal: Float,
        val fecha: String
) {
}