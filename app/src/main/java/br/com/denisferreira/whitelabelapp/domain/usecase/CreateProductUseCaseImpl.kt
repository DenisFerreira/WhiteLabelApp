package br.com.denisferreira.whitelabelapp.domain.usecase

import android.net.Uri
import br.com.denisferreira.whitelabelapp.domain.model.Product
import br.com.denisferreira.whitelabelapp.domain.model.ProductRepository
import java.util.*
import javax.inject.Inject

class CreateProductUseCaseImpl @Inject constructor(
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