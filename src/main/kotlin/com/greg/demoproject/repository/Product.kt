package com.greg.demoproject.repository

import java.time.OffsetDateTime

data class Product(
    val id: Long,
    val name: String,
    val createdAt: OffsetDateTime
)
