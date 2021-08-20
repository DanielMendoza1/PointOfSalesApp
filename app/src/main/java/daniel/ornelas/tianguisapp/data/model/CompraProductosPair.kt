package daniel.ornelas.tianguisapp.data.model

import androidx.room.*

data class CompraProductosPair(
        @Embedded val compra: CompraModel,
        @Relation(
             parentColumn = "idCompra",
             entityColumn = "idProducto",
             associateBy = Junction(CompraProductoCrossRef::class,
                     parentColumn = "idCompra",
                     entityColumn = "idProducto"
             )
     )
    val productos: List<ProductoModel>
)

