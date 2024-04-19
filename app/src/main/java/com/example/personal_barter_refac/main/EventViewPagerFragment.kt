package com.example.personal_barter_refac.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.personal_barter_refac.databinding.FragmentMainEventBinding

private const val ARG_PARAM = "position"
class EventViewPagerFragment: Fragment() {
    private var page: Int = 0
    private lateinit var binding: FragmentMainEventBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            page = it.getInt(ARG_PARAM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainEventBinding.inflate(inflater, container, false)

        binding.ivEvent.setImageResource(
            binding.root.context.resources.getIdentifier(
                "event$page", "drawable", binding.root.context.packageName)
        )

        return binding.root
    }

    companion object{
        fun newInstance(position: Int) =
            EventViewPagerFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM, position)
                }
            }
    }
}