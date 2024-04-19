package com.example.personal_barter_refac.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.personal_barter_refac.databinding.FragmentPurchaseBinding

private const val ARG_PARAM = "param"

class PurchaseFragment: Fragment() {
    private var param: String? = null
    private lateinit var binding: FragmentPurchaseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param = it.getString(ARG_PARAM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPurchaseBinding.inflate(inflater, container, false)



        return binding.root
    }

    companion object{

        @JvmStatic
        fun newInstance(param: String) =
            PurchaseFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM, param)
                }
            }
    }
}