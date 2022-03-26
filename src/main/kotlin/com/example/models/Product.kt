package com.example.models

data class Product (
    val name: String,
    val price: Float,
    val avaible: Boolean
) {
    override fun toString(): String {
        return "$name - $price, Dostępność: $avaible\n"
    }
}