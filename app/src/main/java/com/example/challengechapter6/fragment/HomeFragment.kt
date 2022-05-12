package com.example.challengechapter6.fragment

import android.content.Context
import android.content.SharedPreferences
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
import com.example.challengechapter6.databinding.FragmentWishlistBinding
import com.example.challengechapter6.viewmodel.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class HomeFragment : Fragment() {

    private var myDB: UserDatabase?= null

    private lateinit var viewModel: HomeViewModel

    private var _binding: FragmentHomeBinding?= null
    private val binding get() = _binding!!

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        sharedPreferences = requireActivity().getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)

        showList()
        getUser()

        binding.ivProfile.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }

        viewModel.user.observe(viewLifecycleOwner, Observer {
            binding.tvUsername.text = it.username
        })

    }

    private fun showList() {
        viewModel.getAllItem()
        viewModel.item.observe(viewLifecycleOwner, Observer {
                data -> (binding.rvBukapalak.adapter as AdapterItem).itemsData(data)
        })
        binding.rvBukapalak.adapter = AdapterItem {
            val id = it
            val sent = HomeFragmentDirections.actionHomeFragmentToDetailFragment(id)
            findNavController().navigate(sent)
        }
    }

    private fun getUser() {
        myDB = UserDatabase.getInstance(requireContext())
        sharedPreferences = requireContext().getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", null)
        val password = sharedPreferences.getString("password", null)

        lifecycleScope.launch(Dispatchers.IO) {
            val user = myDB?.userDao()?.getUser(username.toString(), password.toString())
            runBlocking(Dispatchers.Main) {
                if (user != null) {
                    val data = User(user.id, user.username, user.email, user.password)
                    viewModel.getDataUser(data)
                    binding.ivProfile.setOnClickListener {
                        val direct = HomeFragmentDirections.actionHomeFragmentToProfileFragment(data)
                        findNavController().navigate(direct)
                    }
                }
            }
        }
    }

}