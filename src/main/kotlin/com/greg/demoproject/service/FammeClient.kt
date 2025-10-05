package com.greg.demoproject.service

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import java.time.OffsetDateTime

@Component
class FammeClient(
    private val rest: RestClient,
    @Value("\${external.api.base-url}") private val productsPath: String) {

    fun fetchProducts(): List<ExternalProductDto> {
        val dto = rest.get()
            .uri(productsPath)
            .retrieve()
            .body(ExternalProductsResponse::class.java)
            ?: error("Empty body from external service")

        return dto.products;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    data class ExternalProductsResponse(
        val products: List<ExternalProductDto> = emptyList()
    )

    @JsonIgnoreProperties(ignoreUnknown = true)
    data class ExternalProductDto(
        val id: Long,
        val title: String,
        val vendor: String,
        @JsonProperty("product_type") val productType: String,
        @JsonProperty("created_at") val createdAt: OffsetDateTime?,
        @JsonProperty("updated_at") val updatedAt: OffsetDateTime?,
        @JsonProperty("published_at") val publishedAt: OffsetDateTime?,
        val tags: List<String>? = null,
        val variants: List<ExternalVariantDto> = emptyList()
    )

    @JsonIgnoreProperties(ignoreUnknown = true)
    data class ExternalVariantDto(
        val id: Long,
        val title: String,
        val sku: String,
        val available: Boolean,
        @JsonProperty("requires_shipping") val requires_shipping: Boolean,
        @JsonProperty("created_at") val createdAt: OffsetDateTime?,
        @JsonProperty("updated_at") val updatedAt: OffsetDateTime?,
        val price: String?
    )
}
