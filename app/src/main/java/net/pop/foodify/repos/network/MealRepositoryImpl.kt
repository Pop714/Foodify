package net.pop.foodify.repos.network

import android.util.Log
import jakarta.inject.Inject
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import net.pop.foodify.model.remote.areas.AreasResponse
import net.pop.foodify.model.remote.categories.CategoriesResponse
import net.pop.foodify.model.remote.ingredients.IngredientsResponse
import net.pop.foodify.model.remote.meal.MealResponse
import net.pop.foodify.model.remote.meals.MealsResponse
import net.pop.foodify.network.MealApi

class MealRepositoryImpl @Inject constructor(
    private val api: MealApi
) : MealRepository {

    override suspend fun getCategories(): CategoriesResponse {
        return coroutineScope {
            api.getCategories()
        }
    }

    override suspend fun getAreas(): AreasResponse {
        return coroutineScope {
            api.getAreas()
        }
    }

    override suspend fun getIngredients(): IngredientsResponse {
        return coroutineScope {
            api.getIngredients()
        }
    }

    override suspend fun getMealsByCategory(): List<MealsResponse> {
        return coroutineScope {
            // 1. Fetch the master list of categories
            val categories = getCategories().meals
            Log.e("FuckFuck categories", categories.toString())

            // 2. Fetch meals for all categories concurrently using async
            val categoryWithMealsList = categories.map { category ->
                async {
                    val mealsResponse = api.getMealsByCategory(category.strCategory.toString())
                    val meals = mealsResponse.meals

                    MealsResponse(
                        meals = meals
                    )
                }
            }.awaitAll() // Wait for all parallel network requests to finish

            // 3. Filter out any categories that have no meals
            categoryWithMealsList.filter { it.meals.isNotEmpty() }
        }
    }

    override suspend fun filterMealsWithType(
        type: String,
        filterText: String
    ): List<MealsResponse> {
        return coroutineScope {
            var mealsResponse: MealsResponse? = null
            when (type) {
                "c" ->  mealsResponse = api.getMealsByCategory(filterText)
                "a" ->  mealsResponse = api.getMealsByArea(filterText)
                "i" ->  mealsResponse = api.getMealsByIngredient(filterText)
            }

            val meals = mealsResponse?.meals

            listOf(MealsResponse(
                meals = meals!!
            ))
        }
    }

    override suspend fun getMealById(id: String): MealResponse {
        return coroutineScope {
           api.getMealById(id)
        }
    }
}