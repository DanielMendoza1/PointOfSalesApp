package daniel.ornelas.tianguisapp.presentation.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import daniel.ornelas.tianguisapp.data.model.CompraProductoCrossRef
import daniel.ornelas.tianguisapp.data.database.TianguisDB
import daniel.ornelas.tianguisapp.data.model.CompraModel
import daniel.ornelas.tianguisapp.data.model.CompraProductosPair
import daniel.ornelas.tianguisapp.data.repository.CompraRepository
import daniel.ornelas.tianguisapp.domain.ConsultarComprasProductosCU
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CompraViewModel(application: Application): AndroidViewModel(application) {

    private val compraRepository: CompraRepository
    private val consultarComprasProductosCU: ConsultarComprasProductosCU
    val obtenerTodasComprasConProductos: LiveData<List<CompraProductosPair>>

    init {
        val comprasDao = TianguisDB.obtenerBD(application).compraDao()
        compraRepository = CompraRepository(comprasDao)
        consultarComprasProductosCU =  ConsultarComprasProductosCU(compraRepository)
        obtenerTodasComprasConProductos = consultarComprasProductosCU.invoke()
    }

    fun agregarCompra(compraModel: CompraModel): LiveData<Long>{
        val resultado = MutableLiveData<Long>()

        viewModelScope.launch(Dispatchers.IO){
            val resultadoRepo = compraRepository.agregarCompra(compraModel)
            resultado.postValue(resultadoRepo)
        }
        return resultado
    }

    fun actualizarCompra(compraModel: CompraModel){
        viewModelScope.launch(Dispatchers.IO){
            compraRepository.actualizarCompra(compraModel)
        }
    }

    fun eliminarCompra(compraModel: CompraModel){
        viewModelScope.launch(Dispatchers.IO){
            compraRepository.eliminarCompra(compraModel)
        }
    }

    fun obtenerCompraPorId(id: Int): LiveData<CompraModel>{
        return compraRepository.obtenerCompraPorId(id)
    }

    fun agregarCompraConProductos(compraProductoCrossRef: CompraProductoCrossRef){
        viewModelScope.launch(Dispatchers.IO){
            compraRepository.agregarCompraConProductos(compraProductoCrossRef)
        }
    }

    fun obtenerTodasComprasConProductos(): LiveData<List<CompraProductosPair>>{
        return compraRepository.obtenerComprasConProductos()
    }

    fun obtenerCompraConProductosPorId(id: Long): LiveData<CompraProductosPair>{
        return compraRepository.obtenerCompraConProductosPorId(id)
    }

}