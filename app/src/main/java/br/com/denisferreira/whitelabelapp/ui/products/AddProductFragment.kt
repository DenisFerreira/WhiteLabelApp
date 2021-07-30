package br.com.denisferreira.whitelabelapp.ui.products

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.denisferreira.whitelabelapp.R
import br.com.denisferreira.whitelabelapp.databinding.AddProductFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddProductFragment : BottomSheetDialogFragment () {

    private lateinit var viewModel: AddProductViewModel
    private lateinit var binding: AddProductFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddProductFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddProductViewModel::class.java)
        // TODO: Use the ViewModel
    }

}