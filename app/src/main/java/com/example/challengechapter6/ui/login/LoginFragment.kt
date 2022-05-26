package com.example.challengechapter6.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.challengechapter6.R
import com.example.challengechapter6.databinding.FragmentLoginBinding
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

//    private var myDB: UserDatabase?= null

    private var _binding: FragmentLoginBinding?= null
    private val binding get() = _binding!!

    private val viewModel: LoginFragmentViewModel by viewModel()

//    private lateinit var userViewModel: UserViewModel
//
//    private lateinit var pref: UserManager

//    val sharedPreferences = "sharedPreferences"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val loginScreen: SharedPreferences = requireActivity().getSharedPreferences(sharedPreferences, Context.MODE_PRIVATE)
//        myDB = UserDatabase.getInstance(requireContext())
//        pref = UserManager(requireContext())
//        userViewModel = ViewModelProvider(requireActivity(), ViewModelFactory(pref))[UserViewModel::class.java]

        binding.btnLogin.setOnClickListener {
            if (binding.etUsernameLogin.text.isNullOrEmpty() && binding.etPasswordLogin.text.isNullOrEmpty()){
                Toast.makeText(context, "Masukkan Username dan Password kamu terlebih dahulu", Toast.LENGTH_LONG).show()
            } else if (binding.etUsernameLogin.text.isNullOrEmpty()){
                binding.etUsernameLogin.error = "Masukkan Username kamu yang telah terdaftar"
            } else if (binding.etPasswordLogin.text.isNullOrEmpty()){
                binding.etPasswordLogin.error = "Masukkan Password kamu"
            } else {
                viewModel.login(binding.etUsernameLogin.text.toString(), binding.etPasswordLogin.text.toString())
//                  val result = myDB?.userDao()?.getUser(binding.etUsernameLogin.text.toString(), binding.etPasswordLogin.text.toString())
                viewModel.userSession.observe(viewLifecycleOwner){
                    if (it == null){
                        Toast.makeText(context, "Sign In gagal", Toast.LENGTH_SHORT).show()
                    } else {
                        viewModel.setDataUser(it)
//                            val editor: SharedPreferences.Editor = loginScreen.edit()
//                            editor.putString("username", binding.etUsernameLogin.text.toString())
//                            editor.putString("password", binding.etPasswordLogin.text.toString())
//                            editor.apply()
                        Toast.makeText(context, "Sign In Berhasil", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    }
                }
            }
        }

        binding.tvGoRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}