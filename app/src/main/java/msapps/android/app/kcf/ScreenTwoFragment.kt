package msapps.android.app.kcf


import android.os.Bundle
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
                            selectedHobbyList?.remove(model?.HN)
                        Log.e("TAG","Removed: "+ (model?.HN ?: "null"))
                    }else{
                        if(selectedHobbyList == null)
                            selectedHobbyList = mutableListOf()
                        model?.let {
                            selectedHobbyList?.add(model.HN)
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
        return view1
    }

}// Required empty public constructor
