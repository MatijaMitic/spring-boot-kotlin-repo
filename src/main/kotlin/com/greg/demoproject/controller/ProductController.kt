package com.greg.demoproject.controller

import com.greg.demoproject.repository.ProductRepository
import com.greg.demoproject.repository.entities.Product
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import java.time.OffsetDateTime

@Controller
class ProductController(private val repo: ProductRepository) {

    @GetMapping("/")
    fun home(model: Model): String {
        return "index"
    }

    @GetMapping("/search")
    fun searchPage(): String = "search"

    // HTMX results fragment (returns only the table/list)
    @GetMapping("/products/search")
    fun searchProducts(
        @RequestParam("q", required = false, defaultValue = "") q: String,
        model: Model
    ): String {
        val term = q.trim()
        val items: List<Product> =
            if (term.isBlank()) emptyList()
            else repo.findByNameContainingIgnoreCase(term) // or repo.findByNameContainingIgnoreCase(term)

        model.addAttribute("items", items)
        model.addAttribute("q", term)
        return "fragments/search_results :: results(items=${'$'}{items}, q=${'$'}{q})"
    }

    @GetMapping("/products/fragment")
    fun productsFragment(model: Model): String {
        val items = repo.findAll()
        model.addAttribute("items", items)
        return "fragments/products_and_form :: oob(items=${'$'}{items})"
    }

    @PostMapping("/products")
    fun createProduct(
        @ModelAttribute form: ProductForm,
        model: Model
    ): String {
        val entity = Product(
            id = null,
            externalId = form.externalId,
            name = form.name.take(30),
            vendor = form.vendor,
            productType = form.productType,
            createdAt = OffsetDateTime.now(),
            updatedAt = null,
            variants = emptySet()
        )
        repo.save(entity)

        model.addAttribute("items", repo.findAll())
        return "fragments/products :: table(items=${'$'}{items})"
    }

    data class ProductForm(
        val externalId: Long,
        val name: String,
        val vendor: String? = null,
        val productType: String? = null
    )

}
