package co.edu.udea.compumovil.gr03_20201.lab2version2.Interfaces

import androidx.lifecycle.LiveData
import androidx.room.*
import co.edu.udea.compumovil.gr03_20201.lab2version2.Entity.POI


@Dao
interface POIDao {
    @Insert
    fun insertPoi(poi: POI)

    @Update
    fun updatePoi(poi: POI)

    @Delete
    fun deletePoi(poi: POI)

    @Query("SELECT * FROM POIs")
    fun getAll(): List<POI>

    @Query("SELECT * FROM POIs WHERE FK_user = :codeUser")
    fun getAllForUser(codeUser: Int): LiveData<List<POI>>
}