package br.com.denisferreira.whitelabelapp.domain.usecase

import android.net.Uri
import br.com.denisferreira.whitelabelapp.data.ProductRepository
import br.com.denisferreira.whitelabelapp.domain.model.Product
import java.util.*

class CreateProductUseCaseImpl(
    private val uploadProductImageUseCase: UploadProductImageUseCase,
    private val productRepository: ProductRepository
) : CreateProductUseCase {
    override suspend fun invoke(description: String, price: Double, imageUri: Uri): Product {
        return try {
            val imageUrl = uploadProductImageUseCase(imageUri)
            val product = Product(
                UUID.randomUUID().toString(),
                description,
                price,
                imageUrl
            )
            productRepository.createProduct(product)
        } catch (e: Exception) {
            throw e
        }
    }

}