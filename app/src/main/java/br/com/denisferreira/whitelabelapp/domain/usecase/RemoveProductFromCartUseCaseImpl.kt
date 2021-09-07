package br.com.denisferreira.whitelabelapp.domain.usecase

import br.com.denisferreira.whitelabelapp.domain.model.Product
import br.com.denisferreira.whitelabelapp.domain.model.ShoppingCart
import br.com.denisferreira.whitelabelapp.domain.model.ShoppingCartRepository
import javax.inject.Inject

class RemoveProductFromCartUseCaseImpl @Inject constructor(private val repository: ShoppingCartRepository) :
    RemoveProductFromCartUseCase {
    override suspend fun invoke(product: Product): Boolean {
        return repository.removeProductToCart(product)
    }
}