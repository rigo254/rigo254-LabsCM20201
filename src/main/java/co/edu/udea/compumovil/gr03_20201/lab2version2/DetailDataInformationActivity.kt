package co.edu.udea.compumovil.gr03_20201.lab2version2

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.edu.udea.compumovil.gr03_20201.lab2version2.Entity.POI
import kotlinx.android.synthetic.main.items_sites.*

class DetailDataInformationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_data_information)
        val POI = intent.getSerializableExtra("POI") as POI

        val imageNew = getImage(POI.image)

        imagen.setImageBitmap(imageNew)
        name.text = POI.name
        description.text = POI.description
        punctuation.text = POI.punctuation
    }

    fun getImage(image: ByteArray?): Bitmap? {
        if (image != null) {
            return BitmapFactory.decodeByteArray(image, 0, image.size)
        } else {
            return null
        }
    }
}