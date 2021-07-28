package br.com.denisferreira.whitelabelapp.data

import android.net.Uri
import br.com.denisferreira.whitelabelapp.domain.model.Product

interface ProductDataSource {

    suspend fun getProducts(): List<Product>
    suspend fun uploadProductImage(imageUri: Uri): String
    suspend fun createProduct(product: Product): Product
}