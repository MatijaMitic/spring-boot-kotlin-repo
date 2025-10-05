package com.greg.demoproject.service

import com.greg.demoproject.repository.ProductRepository
import com.greg.demoproject.service.FammeClient.ExternalProductDto
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class ProductSyncer(private val client: FammeClient,
                    private val repo: ProductRepository
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @Scheduled(initialDelay = 0)
    fun fetchAndStore() {
        runCatching {
            val products: List<ExternalProductDto> = client.fetchProducts()
            if (products.isEmpty()) return


            products.map { dto ->
                val current = repo.findByExternalId(dto.id)
                if (current.id == null) {
                    val entity = dto.toEntity()
                    repo.save(entity)
                }
            }
        }.onFailure { ex ->
            log.warn("Failed to fetch/store products {}", ex.message)
        }
    }

}
