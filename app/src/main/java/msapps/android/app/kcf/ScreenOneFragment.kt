package msapps.android.app.kcf


import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_screen_one.*
import msapps.android.app.kcf.firebaseMappingClasses.User


/**
 * A simple [Fragment] subclass.
 */
class ScreenOneFragment : Fragment() {
    lateinit var view1 : View
    var selectedGender : String = ""
    lateinit var genderOptions : Array<String>


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        view1 = inflater!!.inflate(R.layout.fragment_screen_one, container, false)
        genderOptions = arrayOf("Male","Female","Other")

        this.activity.fab.setOnClickListener{
            if(inputIsCorrect()){
                var user = User()
                user.name = etxt_name.text.trim().toString()
                user.mobNo = etxt_mob_no.text.trim().toString().toLong()
                user.gender = selectedGender
                FirebaseDatabase.getInstance().reference.child("USER")
                        .child(etxt_id.text.trim().toString())
                        .setValue(user)
                Snackbar.make(view1, "Data saved successfully", Snackbar.LENGTH_SHORT).show()
            }else
                Snackbar.make(view1, "Please fill all the details",Snackbar.LENGTH_SHORT).show()
        }
        return view1
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        spinner_gender.adapter = ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, genderOptions)
        spinner_gender.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedGender = genderOptions[position]
            }
        }
    }

    fun newInstance(parent: ViewGroup) : ScreenOneFragment{
        return ScreenOneFragment()
    }

    fun inputIsCorrect() : Boolean {
        return etxt_name.text.isNotEmpty() && etxt_id.text.isNotEmpty() && etxt_mob_no.text.trim().length == 10 && selectedGender.isNotEmpty()
    }

}
