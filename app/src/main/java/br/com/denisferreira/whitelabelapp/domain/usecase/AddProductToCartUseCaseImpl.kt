package br.com.denisferreira.whitelabelapp.domain.usecase

import br.com.denisferreira.whitelabelapp.domain.model.Product
import br.com.denisferreira.whitelabelapp.domain.model.ShoppingCart
import br.com.denisferreira.whitelabelapp.domain.model.ShoppingCartRepository
import javax.inject.Inject

class AddProductToCartUseCaseImpl @Inject constructor(private val repository: ShoppingCartRepository) :
    AddProductToCartUseCase {
    override suspend fun invoke(product: Product): ShoppingCart {
        return repository.addProductToCart(product)
    }
}