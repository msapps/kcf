package msapps.android.app.kcf


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_screen_two.*
import msapps.android.app.kcf.firebaseMappingClasses.Hobbies


/**
 * A simple [Fragment] subclass.
 */
class ScreenTwoFragment : Fragment() {
lateinit var view1 : View

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        view1 = inflater!!.inflate(R.layout.fragment_screen_two, container, false)
        return view1
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var linearLayoutManager = LinearLayoutManager(activity.applicationContext)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rv_hobbies.layoutManager = linearLayoutManager
        var options = FirebaseRecyclerOptions.Builder<Hobbies>()
                .setQuery(FirebaseDatabase.getInstance().reference.child("HOBBIES"), Hobbies::class.java)
                .setLifecycleOwner(this)
                .build()

        //var hobbiesAdapter = FirebaseRecyclerAdapter<Hobbies, HobbiesSelectionViewHolder>


    }

}// Required empty public constructor
