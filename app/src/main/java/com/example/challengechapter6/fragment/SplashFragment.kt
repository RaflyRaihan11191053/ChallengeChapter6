package com.example.challengechapter6.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.challengechapter6.R
import com.example.challengechapter6.databinding.FragmentSplashBinding
import com.example.challengechapter6.databinding.FragmentWishlistBinding
import com.example.challengechapter6.datastore.UserManager
import com.example.challengechapter6.viewmodel.UserViewModel
import com.example.challengechapter6.viewmodel.ViewModelFactory

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding?= null
    private val binding get() = _binding!!

    private lateinit var userViewModel: UserViewModel

    private lateinit var pref: UserManager

//    val sharedPreferences = "sharedPreferences"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = UserManager(requireContext())
        userViewModel = ViewModelProvider(requireActivity(), ViewModelFactory(pref))[UserViewModel::class.java]

        Handler(Looper.getMainLooper()).postDelayed({
//            val splashScreen: SharedPreferences = requireActivity().getSharedPreferences(sharedPreferences, Context.MODE_PRIVATE)
//            val username = splashScreen.getString("username", "")

            userViewModel.getDataUser()
            userViewModel.userSession.observe(viewLifecycleOwner) {
                if (it.username != "DEF_USERNAME"){
                    findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
                } else {
                    findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                }
            }
        }, 3000)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}