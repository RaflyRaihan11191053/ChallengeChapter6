package com.example.challengechapter6.ui.wishlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.challengechapter5.model.UserDatabase
import com.example.challengechapter6.R
import com.example.challengechapter6.adapter.AdapterWishlist
import com.example.challengechapter6.databinding.FragmentWishlistBinding
import com.example.challengechapter6.model.Wishlist
import org.koin.androidx.viewmodel.ext.android.viewModel

class WishlistFragment : Fragment() {

    private var _binding: FragmentWishlistBinding?= null
    private val binding get() = _binding!!

    private val viewModel: WishlistFragmentViewModel by viewModel()

//    private lateinit var viewModel: WishlistViewModel
//    private lateinit var pref: UserDatabase

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

//        pref = UserDatabase.getInstance(requireContext())!!
//        viewModel = ViewModelProvider(requireActivity(), WishlistViewModelFactory(pref))[WishlistViewModel::class.java]

        viewModel.getWishlist()

        viewModel.allWishlist.observe(viewLifecycleOwner) {
            showAllWishlist(it)
        }

        binding.ivBack.setOnClickListener {
            findNavController().navigate(R.id.action_wishlistFragment_to_homeFragment)
        }

    }

    private fun showAllWishlist(it: List<Wishlist>?) {
        viewModel.getWishlist()
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