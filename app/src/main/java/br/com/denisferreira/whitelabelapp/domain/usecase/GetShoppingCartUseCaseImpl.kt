package br.com.denisferreira.whitelabelapp.domain.usecase

import br.com.denisferreira.whitelabelapp.domain.model.ShoppingCartRepository
import javax.inject.Inject

class GetShoppingCartUseCaseImpl @Inject constructor(val repository: ShoppingCartRepository) :
    GetShoppingCartUseCase {
    override fun invoke(sessionId: Long) = repository.getCart()

}