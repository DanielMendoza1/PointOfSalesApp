package daniel.ornelas.tianguisapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "productos")
data class ProductoModel (
      @PrimaryKey(autoGenerate = true)
      val idProducto: Long,
      val nombre: String,
      val precioUnitario: Float,
      val cantidad: Int){
}