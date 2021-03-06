package br.com.denisferreira.whitelabelapp.ui.addproducts

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.denisferreira.whitelabelapp.R
import br.com.denisferreira.whitelabelapp.domain.model.Product
import br.com.denisferreira.whitelabelapp.domain.usecase.CreateProductUseCase
import br.com.denisferreira.whitelabelapp.util.fromCurrency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// HiltViewModel instrui o hilt a atender as construções de viewModel através do lazy constructor
//  by viewModels()
// Inject instrui o hilt como criar o viewModel
@HiltViewModel
class AddProductViewModel @Inject constructor(
    private val createProductUseCase: CreateProductUseCase
) : ViewModel() {

    private var isFormValid = false

    private val _imageUriErrorResId = MutableLiveData<Int>()
    val imageUriErrorResId: LiveData<Int> = _imageUriErrorResId

    private val _descriptionFieldErrorErrorResId = MutableLiveData<Int?>()
    val descriptionFieldErrorErrorResId: LiveData<Int?> = _descriptionFieldErrorErrorResId

    private val _priceFieldErrorResId = MutableLiveData<Int?>()
    val priceFieldErrorResId: LiveData<Int?> = _priceFieldErrorResId

    val showErrorMessage = MutableLiveData<String>()

    private val _productCreated = MutableLiveData<Product?>()
    val productCreated: LiveData<Product?> = _productCreated

    fun createProduct(description: String, price: String, imageUri: Uri?) = viewModelScope.launch {
        isFormValid = true

        _imageUriErrorResId.value = getDrawableResIdIfNull(imageUri)
        _descriptionFieldErrorErrorResId.value = getErrorStringResIdIfEmpty(description)
        _priceFieldErrorResId.value = getErrorStringResIdIfEmpty(price)

        if (isFormValid) {
            try {
                val product = createProductUseCase(description, price.fromCurrency(), imageUri!!)
                _productCreated.postValue(product)
            } catch (e: Exception) {
                Log.d("CreateProduct", e.toString())
                showErrorMessage.postValue(e.toString())
            }
        }
    }

    private fun getErrorStringResIdIfEmpty(value: String): Int? {
        return if (value.isEmpty()) {
            isFormValid = false
            R.string.add_product_field_error
        } else null

    }

    private fun getDrawableResIdIfNull(value: Uri?): Int {
        return if (value == null) {
            isFormValid = false
            R.drawable.background_product_image_error
        } else R.drawable.background_product_image

    }

}