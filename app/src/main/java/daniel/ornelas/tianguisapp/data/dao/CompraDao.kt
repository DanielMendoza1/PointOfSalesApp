package daniel.ornelas.tianguisapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import daniel.ornelas.tianguisapp.data.model.CompraModel
import daniel.ornelas.tianguisapp.data.model.CompraProductoCrossRef
import daniel.ornelas.tianguisapp.data.model.CompraProductosPair

@Dao
interface CompraDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarCompraConProductos(compraProductoCrossRef: CompraProductoCrossRef)


    @Transaction
    @Query("SELECT * FROM compras")
    fun obtenerComprasConProductos(): LiveData<List<CompraProductosPair>>

    @Transaction
    @Query("SELECT * FROM compras WHERE idCompra = :id")
    fun obtenerCompraConProductosPorId(id: Long): LiveData<CompraProductosPair>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarCompra(compra: CompraModel): Long

    @Update
    suspend fun actualizarCompra(compra: CompraModel)

    @Delete
    suspend fun eliminarCompra(compra: CompraModel)

    @Query("SELECT * FROM compras WHERE idCompra = :id")
    fun obtenerCompraPorId(id: Int): LiveData<CompraModel>

    


}