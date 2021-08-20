package daniel.ornelas.tianguisapp.data.repository

import androidx.lifecycle.LiveData
import daniel.ornelas.tianguisapp.data.dao.ProductoDao
import daniel.ornelas.tianguisapp.data.model.ProductoModel

class ProductoRepository(private val productoDao: ProductoDao) {

    suspend fun agregarProducto(productoModel: ProductoModel): Long{
       return productoDao.agregarProducto(productoModel)
    }

    suspend fun actualizarProducto(productoModel: ProductoModel){
        productoDao.actualizarProducto(productoModel)
    }

    suspend fun eliminarProducto(productoModel: ProductoModel){
        productoDao.eliminarProducto(productoModel)
    }

    fun obtenerTodosProductos(): LiveData<List<ProductoModel>> {
        return productoDao.obtenerTodosProductos()
    }

    fun obtenerProductoPorId(id: Int): LiveData<ProductoModel>{
        return productoDao.obtenerProductoPorId(id)
    }




}