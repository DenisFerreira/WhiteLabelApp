package br.com.denisferreira.whitelabelapp.domain.usecase

import br.com.denisferreira.whitelabelapp.domain.model.Product

interface GetProductsUseCase {
    suspend operator fun invoke(): List<Product>
}