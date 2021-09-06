package br.com.denisferreira.whitelabelapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.denisferreira.whitelabelapp.domain.model.ShoppingCart
import br.com.denisferreira.whitelabelapp.domain.usecase.GetShoppingCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getShoppingCartUseCase: GetShoppingCartUseCase,
) : ViewModel() {

    val shoppingCart: LiveData<ShoppingCart> = getShoppingCartUseCase(0)

}