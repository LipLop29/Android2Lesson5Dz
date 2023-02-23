package com.example.android2lesson5dz

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.example.android2lesson5dz.databinding.FragmentBlankBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BlankFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBlankBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBlankBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setList()
    }

    private fun setList() {
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
        val resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {}
        binding.btnSettings.setOnClickListener {
            resultLauncher.launch(
                Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).setData(
                    Uri.parse("package:${requireActivity().packageName}")
                )
            )
            dismiss()
        }
    }
}