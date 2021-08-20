package daniel.ornelas.tianguisapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import daniel.ornelas.tianguisapp.data.dao.CompraDao
import daniel.ornelas.tianguisapp.data.model.CompraProductoCrossRef
import daniel.ornelas.tianguisapp.data.dao.ProductoDao
import daniel.ornelas.tianguisapp.data.model.CompraModel
import daniel.ornelas.tianguisapp.data.model.ProductoModel

@Database(
    entities = [ProductoModel::class, CompraModel::class, CompraProductoCrossRef::class],
    version = 2
)
abstract class TianguisDB: RoomDatabase() {

    abstract fun productoDao(): ProductoDao
    abstract fun compraDao(): CompraDao

    companion object{

        @Volatile
        private var INSTANCE: TianguisDB? = null

        fun obtenerBD(context: Context): TianguisDB {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        TianguisDB::class.java,
                        "tianguis_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}