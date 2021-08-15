package br.com.denisferreira.whitelabelapp.ui.addproducts

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.com.denisferreira.whitelabelapp.databinding.AddProductFragmentBinding
import br.com.denisferreira.whitelabelapp.util.CurrencyTextWatcher
import br.com.denisferreira.whitelabelapp.util.PRODUCT_KEY
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddProductFragment : BottomSheetDialogFragment() {

    private val viewModel: AddProductViewModel by viewModels()
    private lateinit var binding: AddProductFragmentBinding

    private var imageUri: Uri? = null

    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            imageUri = uri
            binding.imageProduct.setImageURI(imageUri)

        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AddProductFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeVMEvents()
        setListener()
    }

    private fun observeVMEvents() {
        viewModel.imageUriErrorResId.observe(viewLifecycleOwner) {
            binding.imageProduct.setBackgroundResource(it)
        }
        viewModel.descriptionFieldErrorErrorResId.observe(viewLifecycleOwner) {
            binding.inputLayoutDescription.setError(it)
        }
        viewModel.priceFieldErrorResId.observe(viewLifecycleOwner) {
            binding.inputLayoutPrice.setError(it)
        }
        viewModel.showErrorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
        viewModel.productCreated.observe(viewLifecycleOwner) { product ->
            if (product != null) {
                Toast.makeText(
                    context,
                    "${product.description} added with success",
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().run {
                    previousBackStackEntry?.savedStateHandle?.set(PRODUCT_KEY, product)
                    popBackStack()
                }
            }

        }
    }

    private fun TextInputLayout.setError(stringResId: Int?) {
        error = if (stringResId != null) {
            getString(stringResId)
        } else null
    }

    private fun setListener() {
        binding.imageProduct.setOnClickListener {
            chooseImage()
        }
        binding.buttonAddProduct.setOnClickListener {
            val description = binding.inputDescription.text.toString()
            val price = binding.inputPrice.text.toString()
            viewModel.createProduct(description, price, imageUri)
        }

        binding.inputPrice.run {
            addTextChangedListener(CurrencyTextWatcher(this))
        }
    }

    private fun chooseImage() {
        getContent.launch("image/*")
    }

}