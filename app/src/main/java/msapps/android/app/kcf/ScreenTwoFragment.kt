package msapps.android.app.kcf


import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import msapps.android.app.kcf.firebaseMappingClasses.Hobbies


/**
 * A simple [Fragment] subclass.
 */
class ScreenTwoFragment : Fragment() {
    lateinit var view1 : View
    lateinit var rv_hobbies : RecyclerView
    var selectedHobbyList : MutableList<String>?  = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        view1 = inflater!!.inflate(R.layout.fragment_screen_two, container, false)
        rv_hobbies = view1.findViewById(R.id.rv_hobbies)
        var linearLayoutManager = LinearLayoutManager(activity.applicationContext)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rv_hobbies.layoutManager = linearLayoutManager
        var options = FirebaseRecyclerOptions.Builder<Hobbies>()
                .setQuery(FirebaseDatabase.getInstance().reference.child("HOBBIES"), Hobbies::class.java)
                .setLifecycleOwner(this)
                .build()

        var hobbiesAdapter = object : FirebaseRecyclerAdapter<Hobbies, HobbiesSelectionViewHolder>(options){
            override fun onBindViewHolder(holder: HobbiesSelectionViewHolder?, position: Int, model: Hobbies?) {
                holder?.bind(model)
                holder?.itemView?.setOnClickListener{
                    var isVisible = if(holder.imgTick.visibility == View.GONE){
                        holder.imgTick.visibility = View.VISIBLE
                        false
                    }else{
                        holder.imgTick.visibility = View.GONE
                        true
                    }
                    if(isVisible){
                        if(selectedHobbyList != null && selectedHobbyList!!.size > 0)
                            selectedHobbyList?.remove(getRef(position).key)
                        Log.e("TAG","Removed: "+ (model?.HN ?: "null"))
                    }else{
                        if(selectedHobbyList == null)
                            selectedHobbyList = mutableListOf()
                        model?.let {
                            selectedHobbyList?.add(getRef(position).key)
                            Log.e("TAG","count: "+selectedHobbyList?.count()+" "+model.HN)
                        }

                    }
                }
            }

            override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HobbiesSelectionViewHolder {
                return HobbiesSelectionViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.layout_hobby_selection_adapter,parent, false))
            }

        }

        rv_hobbies.adapter = hobbiesAdapter
        activity.fab.setOnClickListener{
            if(selectedHobbyList?.size == 0){
                Snackbar.make(view1,"Select at least 1 hobby!", Snackbar.LENGTH_SHORT).show()
            }else{
                var result = HashMap<String, Any>()
                for(hobby in selectedHobbyList!!.iterator()){
                    var hobbyMap = HashMap<String, Long>()
                    hobbyMap.put("TS", System.currentTimeMillis())
                    result.put(hobby, hobbyMap)
                }
                FirebaseDatabase.getInstance().reference
                        .child("USER")
                        .child(PreferenceManager.getDefaultSharedPreferences(activity).getString("ID", "0"))
                        .child("hobbies")
                        .setValue(result)
                Snackbar.make(view1, "Data saved successfully!",Snackbar.LENGTH_SHORT).show()
                (activity as MainActivity).switchFragment(ScreenThreeFragment(),"ScreenThreeFragment")
            }

        }
        return view1
    }

}// Required empty public constructor
