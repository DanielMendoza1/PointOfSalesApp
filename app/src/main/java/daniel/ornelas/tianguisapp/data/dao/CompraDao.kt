package daniel.ornelas.tianguisapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import daniel.ornelas.tianguisapp.data.model.CompraModel
import daniel.ornelas.tianguisapp.data.model.CompraProductoCrossRef
import daniel.ornelas.tianguisapp.data.model.CompraConProductos

@Dao
interface CompraDao {

    @Query(" SELECT compras.idCompra, compras.cantProductos, compras.montoTotal, compras.fecha, productos.idProducto, productos.cantidad, productos.precioUnitario, productos.nombre, CompraProductoCrossRef.importe, CompraProductoCrossRef.cantidadProductoCompra FROM compras INNER JOIN CompraProductoCrossRef ON compras.idCompra = CompraProductoCrossRef.idCompra INNER JOIN productos ON productos.idProducto = CompraProductoCrossRef.idProducto")
    fun obtenerComprasConProductos(): LiveData<List<CompraConProductos>>

    //Test
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarCompraConProductos(compraProductoCrossRef: CompraProductoCrossRef)

    @Query("SELECT compras.idCompra, compras.cantProductos, compras.montoTotal, compras.fecha, productos.idProducto, productos.cantidad, productos.precioUnitario, productos.nombre, CompraProductoCrossRef.importe, CompraProductoCrossRef.cantidadProductoCompra FROM compras INNER JOIN CompraProductoCrossRef ON compras.idCompra = CompraProductoCrossRef.idCompra INNER JOIN productos ON productos.idProducto = CompraProductoCrossRef.idProducto WHERE compras.idCompra = :id")
    fun obtenerCompraConProductosPorId(id: Long): LiveData<CompraConProductos>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarCompra(compra: CompraModel): Long

    @Update
    suspend fun actualizarCompra(compra: CompraModel)

    @Delete
    suspend fun eliminarCompra(compra: CompraModel)

    @Query("SELECT * FROM compras WHERE idCompra = :id")
    fun obtenerCompraPorId(id: Int): LiveData<CompraModel>


    @Query("SELECT compras.idCompra, compras.cantProductos, compras.montoTotal, compras.fecha, productos.idProducto, productos.cantidad, productos.precioUnitario, productos.nombre, CompraProductoCrossRef.importe, CompraProductoCrossRef.cantidadProductoCompra FROM compras INNER JOIN CompraProductoCrossRef ON compras.idCompra = CompraProductoCrossRef.idCompra INNER JOIN productos ON productos.idProducto = CompraProductoCrossRef.idProducto WHERE fecha BETWEEN :fechaDesde AND :fechaHasta ")
    fun obtenerComprasConProductosFechas(fechaDesde: String, fechaHasta: String): LiveData<List<CompraConProductos>>


}