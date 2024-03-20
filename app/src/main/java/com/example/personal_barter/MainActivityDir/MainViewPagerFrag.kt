package com.example.personal_barter.MainActivityDir

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.personal_barter.R

class MainViewPagerFrag: Fragment() {
    private  var page: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            page = it.getInt(ARG_TYPE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.frag_main1, container, false)

        val imageView = view.findViewById<ImageView>(R.id.imageView_main)
        val imageResource = view.resources.getIdentifier("event$page","drawable", view.context.packageName)
        imageView.setImageResource(imageResource)
        return view
    }

    companion object{
        const val ARG_TYPE = "mainViewPage"
        fun newInstance(position: Int): Fragment{
            val frag = MainViewPagerFrag()
            val args = Bundle()

            args.putInt(ARG_TYPE, position)
            frag.arguments = args

            return frag
        }
    }
}