package net.pop.foodify.model.remote.categories

data class CategoriesResponse(
    val meals: List<Category> = listOf()
)