package com.greg.demoproject.repository

import com.greg.demoproject.repository.entities.Product
import com.greg.demoproject.repository.entities.ProductVariant
import org.springframework.data.repository.CrudRepository

interface ProductRepository : CrudRepository<Product, Long> {
    fun findByExternalId(externalId: Long): Product
}

interface ProductVariantRepository : CrudRepository<ProductVariant, Long>
