package com.example.challengechapter6.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.challengechapter5.model.User
import com.example.challengechapter5.model.UserDatabase
import com.example.challengechapter6.R
import com.example.challengechapter6.databinding.FragmentRegisterBinding
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : Fragment() {

//    private var myDB: UserDatabase?= null

    private val viewModel: RegisterFragmentViewModel by viewModel()

    private var _binding: FragmentRegisterBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {
            if (binding.etUsernameRegister.text.isNullOrEmpty() &&
                binding.etEmailRegister.text.isNullOrEmpty() &&
                binding.etPasswordRegister.text.isNullOrEmpty() &&
                binding.etConfirmPassword.text.isNullOrEmpty()) {
                Toast.makeText(context, "Form regitrasi belum terisi, harap isi terlebih dahulu", Toast.LENGTH_LONG).show()
            } else if (binding.etUsernameRegister.text.isNullOrEmpty()) {
                binding.etUsernameRegister.error = "Tentukan Username kamu terlebih dahulu"
            } else if (binding.etEmailRegister.text.isNullOrEmpty()) {
                binding.etEmailRegister.error = "Masukkan Email yang akan kamu pakai"
            } else if (binding.etPasswordRegister.text.isNullOrEmpty()) {
                binding.etPasswordRegister.error = "Tentukan Password kamu terlebih dahulu"
            } else if (binding.etConfirmPassword.text.isNullOrEmpty()) {
                binding.etConfirmPassword.error = "Konfirmasi Password kamu"
            } else {
                viewModel.register(
                    User(null,
                        binding.etUsernameRegister.text.toString(),
                        binding.etEmailRegister.text.toString(),
                        binding.etPasswordRegister.text.toString(),
                        ""
                    )
                )
//                  val result = myDB?.userDao()?.getUser(binding.etUsernameLogin.text.toString(), binding.etPasswordLogin.text.toString())
                viewModel.register.observe(viewLifecycleOwner){
                    if (it != 0.toLong()){
                        Toast.makeText(activity, "Berhasil Sign Up", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                    } else {
                        Toast.makeText(activity, "Gagal Sign Up", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        binding.ivBack.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        binding.tvGoLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}