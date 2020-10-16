package co.edu.udea.compumovil.gr03_20201.lab2version2

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import co.edu.udea.compumovil.gr03_20201.lab2version2.DataBase.AppDataBase
import co.edu.udea.compumovil.gr03_20201.lab2version2.Entity.POI
import co.edu.udea.compumovil.gr03_20201.lab2version2.Entity.User
import kotlinx.android.synthetic.main.activity_register_activity.*
import kotlinx.android.synthetic.main.items_sites.*
import org.jetbrains.anko.doAsync
import java.io.ByteArrayOutputStream

class RegisterActivity : AppCompatActivity() {

    private lateinit var db: AppDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_activity)
        db = AppDataBase.getDataBase(this)
}
    fun registerUser(view: View) {
        var userData: User?
        doAsync {
            userData = db.userDao().getUserDuplicate(userNameregi.text.toString())
            runOnUiThread {
                if (userData != null) {
                    Toast.makeText(
                            baseContext,
                            "a_user_with_that_name_already_exists",
                            Toast.LENGTH_SHORT
                    ).show()
                } else {
                    save()
                }
            }
        }
    }

    fun save() {
        var userSave: User?
        if (emailregis.text.toString() != "" && userNameregi.text.toString() != "" && userPasswordRegis.text.toString() != "") {
            val user =
                    User(
                            emailregis.text.toString(),
                            userNameregi.text.toString(),
                            userPasswordRegis.text.toString(),
                            true
                    )
            doAsync {
                db.userDao().insertUser(user)
                userSave = db.userDao().autoLoguin(true)

                val drawable1: Drawable = resources.getDrawable(R.drawable.thailandia)
                val bitmap1 = (drawable1 as BitmapDrawable).bitmap
                val stream1 = ByteArrayOutputStream()
                bitmap1.compress(Bitmap.CompressFormat.JPEG, 100, stream1)
                val bitMapData1 = stream1.toByteArray()
                val site1 = POI(
                        bitMapData1,
                        "Thailandia",
                        "Tailandia es un país del Sudeste Asiático. Es famoso por sus playas tropicales, los opulentos palacios reales, las ruinas antiguas y los templos adornados con figuras de Buda. En Bangkok, la capital, un paisaje urbano ultramoderno se alza junto a las tranquilas comunidades a orillas de los canales y a los icónicos templos de Wat Arun.",
                        "5",
                        userSave!!.codigo
                )
                db.poiDao().insertPoi(site1)

                val drawable2: Drawable = resources.getDrawable(R.drawable.japon)
                val bitmap2 = (drawable2 as BitmapDrawable).bitmap
                val stream2 = ByteArrayOutputStream()
                bitmap2.compress(Bitmap.CompressFormat.JPEG, 100, stream2)
                val bitMapData2 = stream2.toByteArray()
                val site2 = POI(
                        bitMapData2,
                        "Japon",
                        "Japón es una nación insular del océano Pacífico con densas ciudades, palacios imperiales, parques nacionales montañosos y miles de santuarios y templos.",
                        "4",
                        userSave!!.codigo
                )
                db.poiDao().insertPoi(site2)

                val drawable3: Drawable = resources.getDrawable(R.drawable.espana)
                val bitmap3 = (drawable3 as BitmapDrawable).bitmap
                val stream3 = ByteArrayOutputStream()
                bitmap3.compress(Bitmap.CompressFormat.JPEG, 100, stream3)
                val bitMapData3 = stream3.toByteArray()
                val site3 = POI(
                        bitMapData3,
                        "españa",
                        "España, país de la península ibérica de Europa, incluye 17 regiones autónomas con diversas características geográficas y culturales. En Madrid, su capital, se encuentra el Palacio Real y el Museo del Prado, que alberga obras de maestros europeos.",
                        "5",
                        userSave!!.codigo
                )
                db.poiDao().insertPoi(site3)

                val drawable4: Drawable = resources.getDrawable(R.drawable.suiza)
                val bitmap4 = (drawable4 as BitmapDrawable).bitmap
                val stream4 = ByteArrayOutputStream()
                bitmap4.compress(Bitmap.CompressFormat.JPEG, 100, stream4)
                val bitMapData4 = stream4.toByteArray()
                val site4 = POI(
                        bitMapData4,
                        "Shinjuku",
                        "Shinjuku es uno de los barrios más intensos que ver en Tokio: karaokes, neones, salas de videojuegos, restaurantes por doquier, cines, centros comerciales",
                        "5",
                        userSave!!.codigo
                )
                db.poiDao().insertPoi(site4)

                val drawable5: Drawable = resources.getDrawable(R.drawable.mexico)
                val bitmap5 = (drawable5 as BitmapDrawable).bitmap
                val stream5 = ByteArrayOutputStream()
                bitmap5.compress(Bitmap.CompressFormat.JPEG, 100, stream5)
                val bitMapData5 = stream5.toByteArray()
                val site5 = POI(
                        bitMapData5,
                        "México",
                        "México es un país entre los Estados Unidos y América Central, conocido por las playas en el Pacífico y el golfo de México, y su diverso paisaje de montañas, desiertos y selvas.",
                        "4",
                        userSave!!.codigo
                )
                db.poiDao().insertPoi(site5)
                runOnUiThread {
                    openFataInfo()
                }
            }
        } else {
            Toast.makeText(this, "missingDateToEnter", Toast.LENGTH_SHORT).show()
        }
    }

    fun openFataInfo() {
        val intent = Intent(this, DataInformationActivity::class.java)
        startActivity(intent)
    }
}