package com.greg.demoproject.repository

import com.greg.demoproject.repository.entities.Product
import com.greg.demoproject.repository.entities.ProductVariant
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface ProductRepository : CrudRepository<Product, Long> {
    fun findByExternalId(externalId: Long): Product
    fun findByNameContainingIgnoreCase(name: String): List<Product>
}

interface ProductVariantRepository : CrudRepository<ProductVariant, Long> {
    @Query("""
        SELECT COALESCE(BOOL_OR(available), FALSE)
        FROM product_variants
        WHERE sku = :sku
    """)
    fun isAvailableBySku(@Param("sku") sku: String): Boolean
}
