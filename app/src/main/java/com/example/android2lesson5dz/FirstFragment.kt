package com.example.android2lesson5dz

import android.Manifest
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.android2lesson5dz.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var uri: Uri? = null
    private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPermission()
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { isGranted ->
        for (permission in isGranted) {
            when {
                permission.value -> fileChooserContract.launch("image/*")
                !shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                    findNavController().navigate(R.id.blankFragment)
                }
            }
        }
    }

    private fun setPermission() {
        binding.btnGallery.setOnClickListener {
            if (hasPermissionCheckAndRequest(
                    requestPermissionLauncher,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                )
            ) {
                fileChooserContract.launch("image/*")
            }
        }
    }

    private val fileChooserContract =
        registerForActivityResult(ActivityResultContracts.GetContent()) { imageUri ->
            if (imageUri != null) {
                binding.imgGallery.setImage(imageUri.toString())
                uri = imageUri
            }
        }
}