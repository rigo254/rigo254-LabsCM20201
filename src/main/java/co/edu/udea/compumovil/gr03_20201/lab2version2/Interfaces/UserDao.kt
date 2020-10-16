package co.edu.udea.compumovil.gr03_20201.lab2version2.Interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import co.edu.udea.compumovil.gr03_20201.lab2version2.Entity.User

@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User)

    @Query("SELECT * FROM users WHERE :user = usuario LIMIT 1")
    fun validateUser(user: String): User

    @Query("SELECT * FROM users WHERE activo = :valor LIMIT 1")
    fun autoLoguin(valor: Boolean): User

    @Query("UPDATE users SET activo = :valor")
    fun logOut(valor: Boolean)

    @Update
    fun updateUser(user: User)

    @Query("SELECT * FROM users")
    fun getUsers(): List<User>

    @Query("SELECT * FROM users WHERE usuario = :user LIMIT 1")
    fun getUserDuplicate(user: String): User
}