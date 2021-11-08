package daniel.ornelas.tianguisapp.domain

import daniel.ornelas.tianguisapp.data.model.ProductoModel
import daniel.ornelas.tianguisapp.data.repository.ProductoRepository

class BuscarProductoEnCompra() {

    operator fun invoke(nombreProducto: String, productos: HashMap<Long, ProductoModel>): ProductoModel? {

        for (producto in productos.values){
            if(producto.nombre.equals( nombreProducto, true)){
                return producto
            }
        }
        return null
    }
}