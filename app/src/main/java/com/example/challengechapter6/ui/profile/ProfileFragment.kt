package com.example.challengechapter6.ui.profile

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.challengechapter5.model.User
import com.example.challengechapter5.model.UserDatabase
import com.example.challengechapter6.R
import com.example.challengechapter6.databinding.FragmentProfileBinding
import com.example.challengechapter6.datastore.UserManager
import com.example.challengechapter6.datastore.UserManager.Companion.DEFAULT_IMAGE
import com.example.challengechapter6.ui.detail.DetailFragmentViewModel
import com.example.challengechapter6.utils.PermissionUtils
import com.example.challengechapter6.ui.home.HomeFragmentViewModel
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

//    lateinit var fragmentViewModel: HomeFragmentViewModel
//    private lateinit var userViewModel: UserViewModel
//
//    private var myDB: UserDatabase?= null

    private var _binding: FragmentProfileBinding?= null
    private val binding get() = _binding!!

    private val viewModel: ProfileFragmentViewModel by viewModel()

//    private lateinit var pref: UserManager

    private var uri = DEFAULT_IMAGE

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

//        pref = UserManager(requireContext())
//        fragmentViewModel = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
//        userViewModel = ViewModelProvider(requireActivity(), ViewModelFactory(pref))[UserViewModel::class.java]

        binding.ivBack.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
        }

        binding.btnLogout.setOnClickListener {
//            val editor: SharedPreferences.Editor = profileScreen.edit()
//            editor.clear()
//            editor.apply()
            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.deleteUserFromPref()
            }
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
        }

//        myDB = UserDatabase.getInstance(requireContext())

        binding.etUsernameEdit.setText(args.user.username)
        binding.etEmailEdit.setText(args.user.email)
        binding.etPasswordEdit.setText(args.user.password)
        binding.ivProfile.setImageURI(Uri.parse(args.user.image))

        binding.btnEdit.setOnClickListener {
            val objectUser = User(
                args.user.id,
                binding.etUsernameEdit.text.toString(),
                binding.etEmailEdit.text.toString(),
                binding.etPasswordEdit.text.toString(),
                uri
            )
            viewModel.editProfile.observe(viewLifecycleOwner) {
                if (it != 0) {
                    viewModel.setUser(objectUser)
////                        val editor: SharedPreferences.Editor = profileScreen.edit()
////                        editor.putString("username", binding.etUsernameEdit.text.toString())
////                        editor.putString("email", binding.etEmailEdit.text.toString())
////                        editor.putString("password", binding.etPasswordEdit.text.toString())
////                        editor.apply()
                    Toast.makeText(requireContext(), "Sukses mengubah profil user", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
                } else {
                    Toast.makeText(requireContext(), "Gagal mengubah profil user", Toast.LENGTH_SHORT).show()
                }
            }
           viewModel.editUser(objectUser)
        }

        binding.ibAdd.setOnClickListener {
            if(PermissionUtils.isPermissionsGranted(requireActivity(), getRequiredPermission())){
                openGallery()
            }
        }

    }

    private fun openGallery() {
        activity?.intent?.type = "image/*"
        galleryResult.launch(arrayOf("image/*"))
    }

    private val galleryResult =
        registerForActivityResult(ActivityResultContracts.OpenDocument()) { result ->
            result?.let {
                requireActivity().contentResolver.takePersistableUriPermission(
                    it, Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
                uri = it.toString()
            }
            if (result != null) {
                loadImage(result)
            }
        }

    private fun loadImage(uri: Uri) {
        Log.d("Cek URI", uri.toString())
        binding.ivProfile.setImageURI(uri)
//        val s: String = mUri.toString()
//        val mUri = Uri.parse(s)
    }

    private fun getRequiredPermission(): Array<String> {
        return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
        } else {
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            )
        }
    }

}