package co.edu.udea.compumovil.gr03_20201.lab2version2.Adapters

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LayoutAnimationController
import android.widget.ArrayAdapter
import co.edu.udea.compumovil.gr03_20201.lab2version2.Entity.POI
import co.edu.udea.compumovil.gr03_20201.lab2version2.R
import kotlinx.android.synthetic.main.items_sites.view.*

class SitesAdapter(private val mContext: Context, private val listSites: List<POI>) :
    ArrayAdapter<POI>(mContext, 0, listSites){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.items_sites, parent, false)
        val sites = listSites[position]
        val image = getImage(sites.image)

        layout.imagen.setImageBitmap(image)
        layout.name.text = sites.name
        layout.punctuation.text = sites.punctuation
        layout.description.text = sites.description
        return layout
    }
    fun getImage(image: ByteArray?): Bitmap?{
        if (image != null){
            return BitmapFactory.decodeByteArray(image, 0, image.size)
        }else{
            return null
        }
    }
}