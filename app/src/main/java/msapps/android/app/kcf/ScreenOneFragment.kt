package msapps.android.app.kcf


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * A simple [Fragment] subclass.
 */
class ScreenOneFragment : Fragment() {
    lateinit var view1 : View



    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        view1 = inflater!!.inflate(R.layout.fragment_screen_one, container, false)
        return view1
    }

    fun newInstance(parent: ViewGroup) : ScreenOneFragment{
        return ScreenOneFragment()
    }

}
