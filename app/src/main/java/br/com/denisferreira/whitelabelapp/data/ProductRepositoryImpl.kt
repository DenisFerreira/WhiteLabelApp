package br.com.denisferreira.whitelabelapp.data

import android.net.Uri
import br.com.denisferreira.whitelabelapp.domain.model.Product
import br.com.denisferreira.whitelabelapp.domain.model.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val dataSource: ProductDataSource
) : ProductRepository {
    override suspend fun getProducts(): List<Product> = dataSource.getProducts()
    override suspend fun uploadProductImage(imageUri: Uri): String =
        dataSource.uploadProductImage(imageUri)

    override suspend fun createProduct(product: Product): Product =
        dataSource.createProduct(product)
}