package br.com.denisferreira.whitelabelapp.domain.usecase

import br.com.denisferreira.whitelabelapp.data.ProductRepository
import br.com.denisferreira.whitelabelapp.domain.model.Product

class GetProductsUseCaseImpl(
    private val productRepository: ProductRepository
) : GetProductsUseCase {

    override suspend operator fun invoke(): List<Product> {
        return productRepository.getProducts()
    }
}