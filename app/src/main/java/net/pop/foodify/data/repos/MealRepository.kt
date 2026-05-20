package net.pop.foodify.data.repos

import net.pop.foodify.model.remote.areas.AreasResponse
import net.pop.foodify.model.remote.categories.CategoriesResponse
import net.pop.foodify.model.remote.ingredients.IngredientsResponse
import net.pop.foodify.model.remote.meal.MealResponse
import net.pop.foodify.data.remote.meals.MealsResponse

interface MealRepository {
    suspend fun getCategories(): CategoriesResponse
    suspend fun getAreas(): AreasResponse
    suspend fun getIngredients(): IngredientsResponse
    suspend fun getMealsByCategory(): List<MealsResponse>
    suspend fun filterMealsWithType(type: String, filterText: String): List<MealsResponse>
    suspend fun getMealById(id: String): MealResponse
}