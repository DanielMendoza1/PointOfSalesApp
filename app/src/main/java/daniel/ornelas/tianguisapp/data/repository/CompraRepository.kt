package daniel.ornelas.tianguisapp.data.repository

import androidx.lifecycle.LiveData
import daniel.ornelas.tianguisapp.data.dao.CompraDao
import daniel.ornelas.tianguisapp.data.model.CompraProductoCrossRef
import daniel.ornelas.tianguisapp.data.model.CompraModel
import daniel.ornelas.tianguisapp.data.model.CompraProductosPair

class CompraRepository(private val compraDao: CompraDao) {


    suspend fun agregarCompraConProductos(compraProductoCrossRef: CompraProductoCrossRef){
        compraDao.agregarCompraConProductos(compraProductoCrossRef)
    }

    fun obtenerComprasConProductos(): LiveData<List<CompraProductosPair>>{
        return compraDao.obtenerComprasConProductos()
    }

    fun obtenerCompraConProductosPorId(id: Long): LiveData<CompraProductosPair> {
        return compraDao.obtenerCompraConProductosPorId(id)
    }

    fun obtenerComprasPorFechas(fechaDesde: String, fechaHasta: String): LiveData<List<CompraProductosPair>>{
        return compraDao.obtenerComprasConProductosFechas(fechaDesde, fechaHasta)
    }

    suspend fun agregarCompra(compraModel: CompraModel): Long{
        return compraDao.agregarCompra(compraModel)
    }

    fun obtenerCompraPorId(id: Int): LiveData<CompraModel>{
        return compraDao.obtenerCompraPorId(id)
    }

    suspend fun actualizarCompra(compraModel: CompraModel){
        compraDao.actualizarCompra(compraModel)
    }

    suspend fun eliminarCompra(compraModel: CompraModel){
        compraDao.eliminarCompra(compraModel)
    }


}