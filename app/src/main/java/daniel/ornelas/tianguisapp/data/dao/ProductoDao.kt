package daniel.ornelas.tianguisapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import daniel.ornelas.tianguisapp.data.model.ProductoModel

@Dao
interface ProductoDao {

    @Query("SELECT  * FROM productos")
    fun obtenerTodosProductos(): LiveData<List<ProductoModel>>

    @Query("SELECT * FROM productos WHERE idProducto = :id")
    fun obtenerProductoPorId(id: Int): LiveData<ProductoModel>

    @Update
    suspend fun actualizarProducto(producto: ProductoModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarProducto(producto: ProductoModel): Long

    @Delete
    suspend fun eliminarProducto(producto: ProductoModel)
}