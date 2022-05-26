package com.example.challengechapter6.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.challengechapter5.model.User
import com.example.challengechapter5.model.UserDatabase
import com.example.challengechapter6.R
import com.example.challengechapter6.databinding.FragmentProfileBinding
import com.example.challengechapter6.databinding.FragmentWishlistBinding
import com.example.challengechapter6.datastore.UserManager
import com.example.challengechapter6.viewmodel.HomeViewModel
import com.example.challengechapter6.viewmodel.UserViewModel
import com.example.challengechapter6.viewmodel.ViewModelFactory
import kotlinx.coroutines.*

class ProfileFragment : Fragment() {

    lateinit var viewModel: HomeViewModel
    private lateinit var userViewModel: UserViewModel

    private var myDB: UserDatabase?= null

    private var _binding: FragmentProfileBinding?= null
    private val binding get() = _binding!!

    private lateinit var pref: UserManager

//    val sharedPreferences = "sharedPreferences"

    private val args: ProfileFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val profileScreen: SharedPreferences = requireActivity().getSharedPreferences(sharedPreferences, Context.MODE_PRIVATE)

        pref = UserManager(requireContext())
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        userViewModel = ViewModelProvider(requireActivity(), ViewModelFactory(pref))[UserViewModel::class.java]

        binding.ivBack.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
        }

        binding.btnLogout.setOnClickListener {
//            val editor: SharedPreferences.Editor = profileScreen.edit()
//            editor.clear()
//            editor.apply()
            lifecycleScope.launch(Dispatchers.IO) {
                pref.deleteUserFromPref()
            }
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
        }

        myDB = UserDatabase.getInstance(requireContext())

        binding.etUsernameEdit.setText(args.user.username)
        binding.etEmailEdit.setText(args.user.email)
        binding.etPasswordEdit.setText(args.user.password)

        binding.btnEdit.setOnClickListener {
            val objectUser = User(
                args.user.id,
                binding.etUsernameEdit.text.toString(),
                binding.etEmailEdit.text.toString(),
                binding.etPasswordEdit.text.toString()
            )
            GlobalScope.async {
                val result = myDB?.userDao()?.updateProfile(objectUser)
                runBlocking(Dispatchers.Main) {
                    if (result != 0) {
                        userViewModel.setDataUser(objectUser)
//                        val editor: SharedPreferences.Editor = profileScreen.edit()
//                        editor.putString("username", binding.etUsernameEdit.text.toString())
//                        editor.putString("email", binding.etEmailEdit.text.toString())
//                        editor.putString("password", binding.etPasswordEdit.text.toString())
//                        editor.apply()
                        Toast.makeText(requireContext(), "Sukses mengubah profil user", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
                    } else {
                        Toast.makeText(requireContext(), "Gagal mengubah profil user", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding.ibAdd.setOnClickListener {

        }

    }

}