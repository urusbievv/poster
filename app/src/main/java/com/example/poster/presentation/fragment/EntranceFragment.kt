package com.example.poster.presentation.fragment

import android.content.Intent
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
import app.ij.mlwithtensorflowlite.DetectActivity
import com.example.poster.R
import com.example.poster.databinding.FragmentEntraceBinding
import com.example.poster.presentation.viewModel.EntranceViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel


class EntranceFragment : Fragment() {

    private val vm by viewModel<EntranceViewModel>()

    private lateinit var btnEntrance: Button
    private lateinit var root: FrameLayout
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var registerText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEntraceBinding.inflate(inflater)
        btnEntrance = binding.btnEntrance
        root = binding.root
        emailEditText = binding.emailEditText
        passwordEditText = binding.passwordEditText
        registerText = binding.registerText

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        vm.errorLiveData.observe(this, Observer { errorMessage ->
            showSnackBar(errorMessage)
        })
        vm.successLiveData.observe(this, Observer { success ->
            showSnackBar("Привет")
        })

        btnEntrance.setOnClickListener { onEntranceButtonClicked() }
        registerText.setOnClickListener {
            navigateToRegisterFragment()
        }
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(root, message, Snackbar.LENGTH_SHORT).show()
    }

    companion object {
        @JvmStatic
        fun newInstance() = EntranceFragment()
    }

    private fun onEntranceButtonClicked() {
        val email: String = emailEditText.text.toString()
        val password: String = passwordEditText.text.toString()
        vm.onEntranceClicked(email, password)
        navigateToDetectActivity()
    }

    private fun navigateToRegisterFragment() {
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.entrance_place_holder, RegisterFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    private fun navigateToDetectActivity() {
        val intent = Intent(activity, DetectActivity::class.java)
        startActivity(intent)
    }

}