package com.example.personal_barter_refac.main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.personal_barter_refac.R
import com.example.personal_barter_refac.databinding.FragmentHomeBinding
import com.example.personal_barter_refac.main.EventViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private var param: String? = null
    private lateinit var binding: FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param = it.getString(ARG_PARAM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.vp2Event.adapter = EventViewPagerAdapter(requireActivity())
        binding.vp2Event.isUserInputEnabled = true

        TabLayoutMediator(binding.tabLayEvent, binding.vp2Event){ tab, position ->
        }.attach()

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM, param)
                }
            }
    }
}