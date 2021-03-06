package br.com.denisferreira.whitelabelapp.ui.products

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.denisferreira.whitelabelapp.config.Config
import br.com.denisferreira.whitelabelapp.domain.model.Product
import br.com.denisferreira.whitelabelapp.domain.usecase.AddProductToCartUseCase
import br.com.denisferreira.whitelabelapp.domain.usecase.GetProductsUseCase
import br.com.denisferreira.whitelabelapp.domain.usecase.RemoveProductFromCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val addProductToCartUseCase: AddProductToCartUseCase,
    private val removeProductFromCartUseCase: RemoveProductFromCartUseCase,
    config: Config
) :
    ViewModel() {

    val showErrorMessage = MutableLiveData<String>()
    private val _productsData = MutableLiveData<List<Product>>()
    val productData: LiveData<List<Product>> = _productsData

    private val _addProductVisibility = MutableLiveData(config.addButtonVisibility)
    val addProductVisibility: LiveData<Int> = _addProductVisibility

    fun getProducts() = viewModelScope.launch {
        try {
            val products = getProductsUseCase()
            _productsData.postValue(products)
        } catch (e: Exception) {
            Log.d("ProductViewModel", e.toString())
            showErrorMessage.postValue(e.toString())
        }
    }

    fun addProductToCart(product: Product) {
        viewModelScope.launch {
            addProductToCartUseCase(product)
        }
    }

    fun removeProductFromCart(product: Product) {
        viewModelScope.launch {
            removeProductFromCartUseCase(product)
        }
    }
}