package co.edu.udea.compumovil.gr03_20201.lab2version2.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "POIs")
class POI(
        val image: ByteArray? = null,
        val name: String,
        val description: String,
        val punctuation: String,
        val FK_user: Int,
        @PrimaryKey(autoGenerate = true)
        val codigo: Int = 0
) : Serializable