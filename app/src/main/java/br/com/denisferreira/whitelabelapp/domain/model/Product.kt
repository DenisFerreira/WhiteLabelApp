package br.com.denisferreira.whitelabelapp.domain.model

data class Product(
    var id: String = "",
    var description: String = "",
    var price: Double = 0.0,
    var imageUrl: String = ""
)
