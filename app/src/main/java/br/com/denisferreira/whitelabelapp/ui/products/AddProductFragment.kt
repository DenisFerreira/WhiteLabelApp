package br.com.denisferreira.whitelabelapp.ui.products

import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import br.com.denisferreira.whitelabelapp.R
import br.com.denisferreira.whitelabelapp.databinding.AddProductFragmentBinding
import br.com.denisferreira.whitelabelapp.util.CurrencyTextWatcher
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddProductFragment : BottomSheetDialogFragment () {

    private lateinit var viewModel: AddProductViewModel
    private lateinit var binding: AddProductFragmentBinding

    private var imageUri : Uri? = null

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) {  uri ->
        imageUri = uri
        binding.imageProduct.setImageURI(imageUri)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddProductFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddProductViewModel::class.java)
        setListener()
    }

    private fun setListener() {
        binding.imageProduct.setOnClickListener {
            chooseImage()
        }
        binding.buttonAddProduct.setOnClickListener {
            val description = binding.inputDescription.text.toString()
            val price = binding.inputPrice.text.toString()
        }

        binding.inputPrice.run {
            addTextChangedListener(CurrencyTextWatcher(this))
        }
    }

    private fun chooseImage() {
        getContent.launch("image/*")
    }

}