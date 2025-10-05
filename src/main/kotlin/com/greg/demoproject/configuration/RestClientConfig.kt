package com.greg.demoproject.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.client.RestClient

@Configuration
class RestClientConfig {

    @Bean
    fun restClient(
        @Value("\${external.api.base-url}") baseUrl: String
    ): RestClient = RestClient.builder()
        .baseUrl(baseUrl)
        .defaultHeader("Accept", MediaType.APPLICATION_JSON_VALUE)
        .build()
}
