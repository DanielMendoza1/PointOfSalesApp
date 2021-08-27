package daniel.ornelas.tianguisapp.data.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import daniel.ornelas.tianguisapp.data.model.CompraModel
import daniel.ornelas.tianguisapp.data.model.ProductoModel

data class CompraConProductos (
        @Embedded val compra: CompraModel,
        @Embedded val producto: ProductoModel,
        val importe: Float,
        val cantidadProductoCompra: Long
        )
