package co.edu.udea.compumovil.gr03_20201.lab2version2

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import co.edu.udea.compumovil.gr03_20201.lab2version2.DataBase.AppDataBase
import co.edu.udea.compumovil.gr03_20201.lab2version2.Entity.POI
import co.edu.udea.compumovil.gr03_20201.lab2version2.Entity.User
import kotlinx.android.synthetic.main.activity_create_site.*
import org.jetbrains.anko.doAsync
import java.io.ByteArrayOutputStream

class CreateSiteActivity : AppCompatActivity() {
    private lateinit var db: AppDataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_site)
        db = AppDataBase.getDataBase(this)
    }

    fun createSite(view: View) {
        var userSave: User?
        if (name.text.toString() != "" && description.text.toString() != "" && points.text.toString() != "") {
            doAsync {
                userSave = db.userDao().autoLoguin(true)
                val drawable = imageNewSite.getDrawable() as BitmapDrawable
                val bitmap = drawable.bitmap
                val stream1 = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream1)
                val bitMapData1 = stream1.toByteArray()
                val site1 = POI(
                        bitMapData1,
                        name.text.toString(),
                        description.text.toString(),
                        points.text.toString(),
                        userSave!!.codigo
                )
                db.poiDao().insertPoi(site1)
                runOnUiThread {
                    returnListSites()
                }
            }
        } else {
            Toast.makeText(this, "missingDateToEnter", Toast.LENGTH_SHORT).show()
        }
    }

    fun add(view: View) {
        intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/"
        startActivityForResult(Intent.createChooser(intent, "Seleccione una imagen"), 10)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            imageNewSite.setImageURI(data?.data)
        }
    }

    fun returnListSites() {
        val intent = Intent(this, DataInformationActivity::class.java)
        startActivity(intent)
    }
}