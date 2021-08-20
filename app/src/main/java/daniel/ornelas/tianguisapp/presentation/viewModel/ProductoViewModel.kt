package daniel.ornelas.tianguisapp.presentation.viewModel

import android.app.Application
import androidx.lifecycle.*
import daniel.ornelas.tianguisapp.data.model.ProductoModel
import daniel.ornelas.tianguisapp.data.repository.ProductoRepository
import daniel.ornelas.tianguisapp.data.database.TianguisDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductoViewModel(application: Application): AndroidViewModel(application){

    private val productoRepository: ProductoRepository
    val obtenerTodosProductos: LiveData<List<ProductoModel>>

    init {
        val productosDao = TianguisDB.obtenerBD(application).productoDao()
        productoRepository = ProductoRepository(productosDao)
        obtenerTodosProductos = productoRepository.obtenerTodosProductos()

    }

    fun agregarProducto(productoModel: ProductoModel): LiveData<Long> {
        val resultado = MutableLiveData<Long>()

        viewModelScope.launch(Dispatchers.IO) {
            val resultadoRepo = productoRepository.agregarProducto(productoModel)
            resultado.postValue(resultadoRepo)
        }

        return resultado
    }

    fun actualizarProducto(productoModel: ProductoModel) {
        viewModelScope.launch(Dispatchers.IO) {
        productoRepository.actualizarProducto(productoModel)
       }
    }

    fun eliminarProducto(productoModel: ProductoModel){
        viewModelScope.launch(Dispatchers.IO) {
            productoRepository.eliminarProducto(productoModel)
        }
    }

    fun obtenerTodosProductos(): LiveData<List<ProductoModel>>{
        return productoRepository.obtenerTodosProductos()
    }

    fun obtenerProductoPorId(id: Int): LiveData<ProductoModel>{
        return productoRepository.obtenerProductoPorId(id)
    }

    }
