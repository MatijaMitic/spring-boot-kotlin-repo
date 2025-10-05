package com.greg.demoproject.service

import com.greg.demoproject.repository.entities.Product
import com.greg.demoproject.repository.entities.ProductVariant
import java.time.OffsetDateTime

fun FammeClient.ExternalProductDto.toEntity(): Product =
    Product(
        externalId = id,
        name = title.take(30),
        vendor = vendor,
        productType = productType,
        createdAt = createdAt ?: OffsetDateTime.now(),
        updatedAt = updatedAt,
        variants = variants.orEmpty().map { it.toEntity() }.toSet()
    )

fun FammeClient.ExternalVariantDto.toEntity(): ProductVariant =
    ProductVariant(
        externalId = id,
        title = title,
        sku = sku,
        available = available,
        requiresShipping = requires_shipping,
        price = price?.toBigDecimalOrNull(),
        createdAt = createdAt,
        updatedAt = updatedAt
    )
