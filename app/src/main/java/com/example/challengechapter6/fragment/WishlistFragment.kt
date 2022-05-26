package com.example.challengechapter6.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.challengechapter5.model.UserDatabase
import com.example.challengechapter6.R
import com.example.challengechapter6.adapter.AdapterItem
import com.example.challengechapter6.adapter.AdapterWishlist
import com.example.challengechapter6.databinding.FragmentWishlistBinding
import com.example.challengechapter6.model.Wishlist
import com.example.challengechapter6.viewmodel.HomeViewModel
import com.example.challengechapter6.viewmodel.ViewModelFactory
import com.example.challengechapter6.viewmodel.WishlistViewModel
import com.example.challengechapter6.viewmodel.WishlistViewModelFactory

class WishlistFragment : Fragment() {

    private var _binding: FragmentWishlistBinding?= null
    private val binding get() = _binding!!

    private lateinit var viewModel: WishlistViewModel
    private lateinit var pref: UserDatabase

//    private val wishlistViewModel by viewModels<WishlistViewModel> {
//        WishlistViewModelFactory(
//            UserDatabase.getInstance(requireContext())!!
//        )
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = UserDatabase.getInstance(requireContext())!!
        viewModel = ViewModelProvider(requireActivity(), WishlistViewModelFactory(pref))[WishlistViewModel::class.java]

        viewModel.getAllWishlist()

        viewModel.allWishlist.observe(viewLifecycleOwner) {
            showAllWishlist(it)
        }

        binding.ivBack.setOnClickListener {
            findNavController().navigate(R.id.action_wishlistFragment_to_homeFragment)
        }

    }

    private fun showAllWishlist(it: List<Wishlist>?) {
        viewModel.getAllWishlist()
        viewModel.allWishlist.observe(viewLifecycleOwner, Observer {
                data -> (binding.rvWishlist.adapter as AdapterWishlist).itemsData(data)
        })
        binding.rvWishlist.adapter = AdapterWishlist {
            val id = it
            val sent = WishlistFragmentDirections.actionWishlistFragmentToDetailFragment(id)
            findNavController().navigate(sent)
        }
    }

}