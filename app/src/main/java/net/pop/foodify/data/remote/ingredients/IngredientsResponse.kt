package net.pop.foodify.model.remote.ingredients

data class IngredientsResponse(
    val meals: List<Ingredient> = listOf()
)