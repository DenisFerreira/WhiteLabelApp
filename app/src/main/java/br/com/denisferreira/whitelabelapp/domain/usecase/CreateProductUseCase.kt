package br.com.denisferreira.whitelabelapp.domain.usecase

import android.net.Uri
import br.com.denisferreira.whitelabelapp.domain.model.Product

interface CreateProductUseCase {
    suspend operator fun invoke(description: String, price: Double, imageUri: Uri): Product
}