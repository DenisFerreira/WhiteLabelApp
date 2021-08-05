package br.com.denisferreira.whitelabelapp.domain.usecase

import br.com.denisferreira.whitelabelapp.data.ProductRepository
import br.com.denisferreira.whitelabelapp.domain.model.Product
import javax.inject.Inject

class GetProductsUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository
) : GetProductsUseCase {

    override suspend operator fun invoke(): List<Product> {
        return productRepository.getProducts()
    }
}