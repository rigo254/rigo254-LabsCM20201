package co.edu.udea.compumovil.gr03_20201.lab2version2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import co.edu.udea.compumovil.gr03_20201.lab2version2.DataBase.AppDataBase
import co.edu.udea.compumovil.gr03_20201.lab2version2.Entity.User
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.items_sites.*
import org.jetbrains.anko.doAsync

class LoginActivity : AppCompatActivity() {
    private lateinit var db: AppDataBase
    private var data: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = AppDataBase.getDataBase(this)
    }
    fun ingreso(view: View) {
        doAsync {
            data = db.userDao().validateUser(userName.text.toString())
            runOnUiThread {
                validateData(data, view)
            }
        }
    }

    fun validateData(data: User?, view: View) {
        if (data != null) {
            val user =
                    User(data.correo, data.usuario, data.contraseña, true, data.codigo)
            doAsync {
                db.userDao().updateUser(user)
                runOnUiThread {
                    val intent = Intent(baseContext, DataInformationActivity::class.java)
                    startActivity(intent)
                }
            }
        } else {
            Toast.makeText(this, "Username_does_not_exist", Toast.LENGTH_SHORT).show()
        }
    }

    fun registro (view: View){
        val intent: Intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }


}