package br.com.denisferreira.whitelabelapp.domain.model

import androidx.lifecycle.LiveData

interface ShoppingCartRepository {
    fun getCart(): LiveData<ShoppingCart>
    suspend fun addProductToCart(product: Product): Boolean
    suspend fun removeProductToCart(product: Product): Boolean

}