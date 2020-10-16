package co.edu.udea.compumovil.gr03_20201.lab2version2.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "users")
class User(
        val correo: String,
        val usuario: String,
        val contraseña: String,
        val activo: Boolean,
        @PrimaryKey(autoGenerate = true)
        val codigo: Int = 0
) : Serializable