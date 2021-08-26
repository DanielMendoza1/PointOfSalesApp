package daniel.ornelas.tianguisapp.presentation.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import daniel.ornelas.tianguisapp.data.database.TianguisDB
import daniel.ornelas.tianguisapp.data.model.ProductoModel
import daniel.ornelas.tianguisapp.data.repository.ProductoRepository
import daniel.ornelas.tianguisapp.domain.ObtenerTodosProductosCU

class RealizarCompraViewModel(application: Application): AndroidViewModel(application) {

    private val productoRepository: ProductoRepository
    private val obtenerTodosProductosCU: ObtenerTodosProductosCU

    init {
        val productoDao = TianguisDB.obtenerBD(application).productoDao()
        productoRepository = ProductoRepository(productoDao)
        obtenerTodosProductosCU = ObtenerTodosProductosCU(productoRepository)
    }

    fun obtenerTodosProductos(): LiveData<List<ProductoModel>>{
        return obtenerTodosProductosCU.invoke()
    }

}