package com.example.repositories

import com.example.models.Product

class GlobalRepository {

    val products = mapOf(
        "Komputery" to listOf(
            Product("Lenovo IdeaPad 5-15", 2499f, true),
            Product("Dell Inspiron 5515", 3499f, true),
            Product("Lenovo IdeaCentre AIO 5-24", 3699f, false),
            Product("Apple MacBook Pro 13", 4488.28f, false),
            Product("Acer Nitro 50", 5149f, true)
        ),
        "Smartfony" to listOf(
            Product("XIAOMI Redmi 9C", 499f, true),
            Product("XIAOMI POCO X3 PRO", 1499f, false),
            Product("Apple iPhone 13", 4099f, true),
            Product("Samsung Galaxy S20 FE 5G Fan Edition", 2499f, true),
            Product("OnePlus 8 Pro", 2799f, true)
        ),
        "Podzespoły" to listOf(
            Product("Samsung 1TB M.2 PCIe NVMe 980", 529f, true),
            Product("Gigabyte GeForce RTX 3050 GAMING OC 8GB GDDR6", 2199f, false),
            Product("Intel Core i5-11400F", 779f, true),
            Product("MSI PRO Z690-A-DDR4", 959f, true),
            Product("Crucial 16GB (2x8GB) 3600MHzCL16 Ballistix Black RGB", 479f, true)
        )
    )
}