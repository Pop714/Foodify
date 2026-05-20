package net.pop.foodify.data.remote.meals

import net.pop.foodify.model.remote.meals.Meal

data class MealsResponse(
    val meals: List<Meal> = listOf()
)