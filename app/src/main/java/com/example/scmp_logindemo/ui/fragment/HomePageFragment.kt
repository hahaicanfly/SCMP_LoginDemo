package com.example.scmp_logindemo.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.scmp_logindemo.R
import com.example.scmp_logindemo.databinding.HomepageFragmentBinding

/**
 * Created by Archer on 2022/6/13
 */
class HomePageFragment: Fragment() {

     companion object {
         fun newInstance(token: String?) = HomePageFragment().apply {
             arguments = Bundle().apply {
                 Log.e("Archer","HomePageFragment token: $token")
                 putString(HomePageFragment::class.java.name,token)
             }
         }
     }

    lateinit var binding: HomepageFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomepageFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val token = arguments?.getString(HomePageFragment::class.java.name)

        binding.tvToken.text =  String.format(getString(R.string.token),token)
    }

}