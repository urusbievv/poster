package com.example.poster.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.poster.R
import com.example.poster.databinding.FragmentRegisterBinding
import com.example.poster.presentation.viewModel.RegisterViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : Fragment() {

    private val vm by viewModel<RegisterViewModel>()

    private lateinit var root: FrameLayout
    private lateinit var nameEditTextReg: EditText
    private lateinit var emailEditTextReg: EditText
    private lateinit var passwordEditTextReg: EditText
    private lateinit var phoneEditTextReg: EditText
    private lateinit var btnRegister: Button
    private lateinit var singInText: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRegisterBinding.inflate(inflater)
        val root = binding.root
        nameEditTextReg = binding.nameEditTextReg
        emailEditTextReg = binding.emailEditTextReg
        passwordEditTextReg = binding.passwordEditTextReg
        phoneEditTextReg = binding.phoneEditTextReg
        btnRegister = binding.btnRegister
        singInText = binding.signInText
        return root
    }

    override fun onResume() {
        super.onResume()
        vm.errorLiveData.observe(this, Observer { errorMessage ->
            showSnackBar(errorMessage)
        })
        vm.registrationLiveData.observe(this, Observer { success ->
            if (success) {
                showSnackBar("Регистрация успешна")
            } else {
                showSnackBar("Ошибка при регистрации")
            }
        })
        btnRegister.setOnClickListener {
            saveUserRegistration()
        }
        singInText.setOnClickListener{
            navigateToEntranceFragment()
        }
        setButtonClickListeners()
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(root, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun  saveUserRegistration(){
        val name = nameEditTextReg.text.toString()
        val email = emailEditTextReg.text.toString()
        val password = passwordEditTextReg.text.toString()
        val phone = phoneEditTextReg.text.toString()
        vm.saveUserRegistration(name, email, password, phone)
    }

    companion object {
        @JvmStatic
        fun newInstance() = RegisterFragment()
    }

    private fun setButtonClickListeners() {

    }

    private fun navigateToEntranceFragment() {
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.entrance_place_holder, EntranceFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }
}