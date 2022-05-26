package com.example.challengechapter6.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.challengechapter5.model.UserDatabase
import com.example.challengechapter6.R
import com.example.challengechapter6.databinding.FragmentDetailBinding
import com.example.challengechapter6.model.Wishlist
import com.example.challengechapter6.ui.home.HomeFragmentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

//    private val viewModel by viewModels<DetailItemViewModel> {
//        DetailViewModelFactory(UserDatabase.getInstance(requireContext())!!)
//    }

    private val viewModel: DetailFragmentViewModel by viewModel()

    private var _binding: FragmentDetailBinding?= null
    private val binding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivBack.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
        }

        detailItem()
    }

    private fun detailItem() {
        val id = args.id
        viewModel.getDetail(id)
        viewModel.detailItem.observe(viewLifecycleOwner, Observer {
                detail ->
            Glide.with(binding.root).load(detail.image).into(binding.ivImage)
            binding.tvTitle.text = detail.title
            binding.tvCategory.text = detail.category
            binding.tvDescription.text = detail.description
            binding.tvPrice.text = detail.price.toString()
            binding.fabAdd.setOnClickListener {
                viewModel.getWishlist(id)
                viewModel.isWishlist.observe(viewLifecycleOwner) {
                    val addWishlist = Wishlist(
                        id = detail.id,
                        image = detail.image,
                        title = detail.title,
                        category = detail.category,
                        price = detail.price
                    )
                    if (it == null) {
                        viewModel.addWishlist(addWishlist)
                    } else {
                        viewModel.deleteWishlist(addWishlist)
                    }
                }
            }
        })
    }

}