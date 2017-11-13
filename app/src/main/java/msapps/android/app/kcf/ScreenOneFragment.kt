package msapps.android.app.kcf


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_screen_one.*
import kotlinx.android.synthetic.main.fragment_screen_one.view.*
import msapps.android.app.kcf.firebaseMappingClasses.User


/**
 * A simple [Fragment] subclass.
 */
class ScreenOneFragment : Fragment() {
    lateinit var view1 : View



    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        view1 = inflater!!.inflate(R.layout.fragment_screen_one, container, false)


        this.activity.fab.setOnClickListener{
            /*var map = if(inputIsCorrect()){
                //User has entered all details.. create map to save to database
                FirebaseDatabase.getInstance().reference.child("USER")
                        .child(etxt_id.text.trim().toString())
                        .setValue(User(etxt_name.text.trim().toString(), etxt_mob_no.text.trim()))
            }*/
        }
        return view1
    }

    fun newInstance(parent: ViewGroup) : ScreenOneFragment{
        return ScreenOneFragment()
    }

    fun inputIsCorrect() : Boolean {
        return etxt_name.text.trim().length != 0 && etxt_id.text.trim().length !=  0 && etxt_mob_no.text.trim().length == 10 && spinner_gender.selectedItem != null
    }

}
