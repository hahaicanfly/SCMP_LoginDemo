package com.example.scmp_logindemo.ui.fragment

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.scmp_logindemo.MainActivity
import com.example.scmp_logindemo.R
import com.example.scmp_logindemo.databinding.MainFragmentBinding
import com.example.scmp_logindemo.network.State
import com.example.scmp_logindemo.ui.viewmodel.MainViewModel


class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: MainViewModel
    lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            binding.btnLogin.setOnClickListener {
                val acc = edEmail.text.toString()
                val pwd = edPwd.text.toString()

                if(checkInputValid(acc, pwd)){
                    sendLoginRequest(acc, pwd)
                }
            }
        }
    }

    private fun checkInputValid(email: String, pwd: String): Boolean {
        binding.apply {
            if(!isValidEmail(email)){
              edEmail.error = "Please enter a valid Email!!"
                return false
            }
            if(TextUtils.isEmpty(pwd)){
                edPwd.error = "The password can't be empty!!"
                return false
            }
            return true
        }
    }

   private fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    private fun sendLoginRequest(email: String, pwd: String){
        showLoading()
        viewModel.doLogin(email,pwd)
    }

    private fun handle(){

        viewModel.loginResult.observe(viewLifecycleOwner, {
            when (it.state) {
                State.SUCCESS -> {
                    hideLoading()
                    it.data?.let { it ->
                        Log.e("Archer", "SUCCESS token: ${it.token}")
                        (activity as MainActivity).toLoginSuccess(it.token)
                    }
                }
                State.ERROR -> {
                    hideLoading()
                    showErrorDialog(it.message ?: getString(R.string.dialog_unknown_error_message))
                    Log.e("Archer", "ERROR message: ${it.message}")
                }
                State.LOADING -> {
                    showLoading()
                }
            }
        })
    }

    private fun hideLoading() {
        binding.clLoading.visibility = View.GONE
    }

    private fun showLoading() {
        binding.clLoading.visibility = View.VISIBLE
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = MainViewModel()

        handle()
    }

    private fun showErrorDialog(message: String) {
        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder
            .setTitle(getString(R.string.dialog_title_error))
            .setMessage(message)
            .setPositiveButton(
                getString(R.string.dialog_confirm)
            ) { dialog, id ->
                dialog.dismiss()
            }.create().show()
    }

}