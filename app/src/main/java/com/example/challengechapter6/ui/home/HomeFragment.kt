package com.example.challengechapter6.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.challengechapter5.model.User
import com.example.challengechapter5.model.UserDatabase
import com.example.challengechapter6.R
import com.example.challengechapter6.adapter.AdapterItem
import com.example.challengechapter6.databinding.FragmentHomeBinding
import com.example.challengechapter6.datastore.UserManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

//    private var myDB: UserDatabase?= null
//
//    private lateinit var fragmentViewModel: HomeFragmentViewModel
//    private lateinit var userViewModel: UserViewModel

    private val viewModel: HomeFragmentViewModel by viewModel()

    private var _binding: FragmentHomeBinding?= null
    private val binding get() = _binding!!

    private lateinit var pref: UserManager

//    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        pref = UserManager(requireContext())
//        fragmentViewModel = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
//        userViewModel = ViewModelProvider(requireActivity(), ViewModelFactory(pref))[UserViewModel::class.java]

//        sharedPreferences = requireActivity().getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)

        showList()
        viewModel.getDataUser()

        binding.ivProfile.setOnClickListener {
            viewModel.userSession.observe(viewLifecycleOwner) { user ->
//            val user = myDB?.userDao()?.getUser()
//            val data = User(user.id, user.username, user.email, user.password, "")
                val direct = HomeFragmentDirections.actionHomeFragmentToProfileFragment(user)
                findNavController().navigate(direct)
            }
        }

        viewModel.userSession.observe(viewLifecycleOwner, Observer {
            binding.tvUsername.text = it.username
        })

        binding.ivWishlist.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_wishlistFragment)
        }

    }

    private fun showList() {
        viewModel.getAllItem()
        viewModel.home.observe(viewLifecycleOwner, Observer {
                data -> (binding.rvBukapalak.adapter as AdapterItem).itemsData(data)
        })
        binding.rvBukapalak.adapter = AdapterItem {
            val id = it
            val sent = HomeFragmentDirections.actionHomeFragmentToDetailFragment(id)
            findNavController().navigate(sent)
        }
    }

//    private fun getUser() {
////        myDB = UserDatabase.getInstance(requireContext())
////        sharedPreferences = requireContext().getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)
////        val username = sharedPreferences.getString("username", null)
////        val password = sharedPreferences.getString("password", null)
//    }

}