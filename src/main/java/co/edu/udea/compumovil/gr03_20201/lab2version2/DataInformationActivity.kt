package co.edu.udea.compumovil.gr03_20201.lab2version2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import co.edu.udea.compumovil.gr03_20201.lab2version2.Adapters.SitesAdapter
import co.edu.udea.compumovil.gr03_20201.lab2version2.DataBase.AppDataBase
import co.edu.udea.compumovil.gr03_20201.lab2version2.Entity.POI
import kotlinx.android.synthetic.main.activity_data_information.*
import org.jetbrains.anko.doAsync

class DataInformationActivity : AppCompatActivity() {

    private lateinit var db: AppDataBase
    private  var lisPOI = emptyList<POI>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_information)
        db = AppDataBase.getDataBase(this)
        createList()
    }

    fun createList() {
        doAsync {
            var data = db.userDao().autoLoguin(true)
            runOnUiThread {
                addList(data.codigo)
            }
        }
    }


    // convert from bitmap to byte array
    fun addList(code: Int) {
        db.poiDao().getAllForUser(code).observe(this, {
            lisPOI = it
            val adapter = SitesAdapter(this, lisPOI)
            MyList.adapter = adapter
            MyList.setOnItemClickListener { parent, view, position, id ->
                val intent = Intent(baseContext, DetailDataInformationActivity::class.java)
                intent.putExtra("POI", lisPOI[position])
                startActivity(intent)
            }
        })
    }

    fun logOutApp(view: View) {
        doAsync {
            db.userDao().logOut(false)
        }
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun createNewSite(view: View) {
        val intent = Intent(baseContext, CreateSiteActivity::class.java)
        startActivity(intent)
    }
}