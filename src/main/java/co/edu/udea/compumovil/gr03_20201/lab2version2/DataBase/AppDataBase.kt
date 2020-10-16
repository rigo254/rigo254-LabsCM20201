package co.edu.udea.compumovil.gr03_20201.lab2version2.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import co.edu.udea.compumovil.gr03_20201.lab2version2.Entity.POI
import co.edu.udea.compumovil.gr03_20201.lab2version2.Entity.User
import co.edu.udea.compumovil.gr03_20201.lab2version2.Interfaces.POIDao
import co.edu.udea.compumovil.gr03_20201.lab2version2.Interfaces.UserDao
import java.security.AccessControlContext

@Database(entities = [User::class, POI::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun poiDao(): POIDao

    companion object{
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDataBase(context: Context): AppDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext, AppDataBase::class.java,"app_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}