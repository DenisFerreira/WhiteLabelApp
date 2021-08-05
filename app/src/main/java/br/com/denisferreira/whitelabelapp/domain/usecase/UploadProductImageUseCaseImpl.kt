package br.com.denisferreira.whitelabelapp.domain.usecase

import android.net.Uri
import br.com.denisferreira.whitelabelapp.data.ProductRepository
import javax.inject.Inject

class UploadProductImageUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository
    ) : UploadProductImageUseCase {
    override suspend fun invoke(imageUri: Uri): String {
        return productRepository.uploadProductImage(imageUri)
    }

}