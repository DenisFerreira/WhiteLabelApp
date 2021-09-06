package br.com.denisferreira.whitelabelapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShoppingCart(var sessionId: Long = 0, var products: List<Product>) : Parcelable
