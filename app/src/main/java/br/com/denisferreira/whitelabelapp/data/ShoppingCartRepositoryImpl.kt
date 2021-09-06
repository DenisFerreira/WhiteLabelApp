package br.com.denisferreira.whitelabelapp.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.denisferreira.whitelabelapp.domain.model.Product
import br.com.denisferreira.whitelabelapp.domain.model.ShoppingCart
import br.com.denisferreira.whitelabelapp.domain.model.ShoppingCartRepository
import javax.inject.Inject

class ShoppingCartRepositoryImpl @Inject constructor() : ShoppingCartRepository {
    private val products = mutableListOf<Product>()
    private val _shoppingCart = MutableLiveData<ShoppingCart>()

    override fun getCart(): LiveData<ShoppingCart> {
        return _shoppingCart
    }

    override suspend fun addProductToCart(product: Product): ShoppingCart {
        products.add(product)
        Log.i("CARTREPO", "add product $product to cart")
        val shoppingCart = ShoppingCart(0, products)
        _shoppingCart.postValue(shoppingCart)
        return shoppingCart
    }

    override suspend fun removeProductToCart(product: Product): ShoppingCart {
        products.remove(product)
        Log.i("CARTREPO", "remove Product $product to cart")
        val shoppingCart = ShoppingCart(0, products)
        _shoppingCart.postValue(shoppingCart)
        return shoppingCart
    }
}