package br.com.denisferreira.whitelabelapp.domain.usecase

import androidx.lifecycle.LiveData
import br.com.denisferreira.whitelabelapp.domain.model.ShoppingCart

interface GetShoppingCartUseCase {
    operator fun invoke(sessionId: Long): LiveData<ShoppingCart>

}