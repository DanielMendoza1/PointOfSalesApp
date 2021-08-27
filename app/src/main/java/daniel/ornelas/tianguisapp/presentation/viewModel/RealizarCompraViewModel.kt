package daniel.ornelas.tianguisapp.presentation.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import daniel.ornelas.tianguisapp.data.database.TianguisDB
import daniel.ornelas.tianguisapp.data.model.ProductoModel
import daniel.ornelas.tianguisapp.data.repository.CompraRepository
import daniel.ornelas.tianguisapp.data.repository.ProductoRepository
import daniel.ornelas.tianguisapp.domain.AgregarCompraCU
import daniel.ornelas.tianguisapp.domain.ObtenerTodosProductosCU
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RealizarCompraViewModel(application: Application): AndroidViewModel(application) {

    private val productoRepository: ProductoRepository
    private val compraRepository: CompraRepository
    private val agregarCompraCU: AgregarCompraCU
    private val obtenerTodosProductosCU: ObtenerTodosProductosCU

    init {
        val productoDao = TianguisDB.obtenerBD(application).productoDao()
        val compraDao = TianguisDB.obtenerBD(application).compraDao()
        compraRepository = CompraRepository(compraDao)
        productoRepository = ProductoRepository(productoDao)
        obtenerTodosProductosCU = ObtenerTodosProductosCU(productoRepository)
        agregarCompraCU = AgregarCompraCU(compraRepository)
    }

    fun obtenerTodosProductos(): LiveData<List<ProductoModel>>{
        return obtenerTodosProductosCU.invoke()
    }

    fun agregarCompra(productosAgregar: HashMap<Long, ProductoModel>){
        viewModelScope.launch(Dispatchers.IO){
            agregarCompraCU.invoke(productosAgregar)
        }
    }
}