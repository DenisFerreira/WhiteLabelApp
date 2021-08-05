package br.com.denisferreira.whitelabelapp.domain.model

import android.net.Uri

interface ProductRepository {
    suspend fun getProducts(): List<Product>

    suspend fun uploadProductImage(imageUri: Uri): String

    suspend fun createProduct(product: Product): Product
}