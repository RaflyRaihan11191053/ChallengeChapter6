package com.example.challengechapter6.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.challengechapter6.R
import com.example.challengechapter6.databinding.FragmentSplashBinding
import com.example.challengechapter6.datastore.UserManager
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding?= null
    private val binding get() = _binding!!

//    private lateinit var userViewModel: UserViewModel

    private val viewModel : SplashFragmentViewModel by viewModel()

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

        Handler(Looper.getMainLooper()).postDelayed({
//            val splashScreen: SharedPreferences = requireActivity().getSharedPreferences(sharedPreferences, Context.MODE_PRIVATE)
//            val username = splashScreen.getString("username", "")

            viewModel.getDataUser()
            viewModel.userSession.observe(viewLifecycleOwner) {
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