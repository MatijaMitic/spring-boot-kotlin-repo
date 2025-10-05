package com.greg.demoproject.repository.entities

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.MappedCollection
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.OffsetDateTime

@Table("products")
data class Product(
    @Id
    val id: Long? = null,
    val externalId: Long,
    val name: String,
    val vendor: String? = null,
    @Column("product_type")
    val productType: String? = null,
    @Column("created_at")
    val createdAt: OffsetDateTime? = null,
    @Column("updated_at")
    val updatedAt: OffsetDateTime? = null,

    @MappedCollection(idColumn = "product_id")
    val variants: Set<ProductVariant> = emptySet()
)

@Table("product_variants")
data class ProductVariant(
    @Id
    val id: Long? = null,
    val externalId: Long,
    val title: String,
    val sku: String? = null,
    val available: Boolean = false,
    @Column("requires_shipping")
    val requiresShipping: Boolean = true,
    val taxable: Boolean = true,
    val price: BigDecimal? = null,
    val grams: Int? = null,
    val position: Int? = null,
    @Column("created_at")
    val createdAt: OffsetDateTime? = null,
    @Column("updated_at")
    val updatedAt: OffsetDateTime? = null
)
