package br.com.denisferreira.whitelabelapp.domain.usecase

import br.com.denisferreira.whitelabelapp.domain.model.Product
import br.com.denisferreira.whitelabelapp.domain.model.ShoppingCart

interface AddProductToCartUseCase {
    suspend operator fun invoke(product: Product): Boolean
}