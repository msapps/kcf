package msapps.android.app.kcf

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.layout_hobby_selection_adapter.view.*
import msapps.android.app.kcf.firebaseMappingClasses.Hobbies

/**
 * Created by mayur on 19/11/17.
 */
class HobbiesSelectionViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var imgTick = itemView!!.findViewById<ImageView>(R.id.imgTick)
    fun bind(model: Hobbies?){
        itemView.txtHobbieName.text = model?.HN ?: "Oops!"
    }

}