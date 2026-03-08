package net.pop.foodify.network

import net.pop.foodify.model.remote.areas.AreasResponse
import net.pop.foodify.model.remote.categories.CategoriesResponse
import net.pop.foodify.model.remote.ingredients.IngredientsResponse
import net.pop.foodify.model.remote.meal.MealResponse
import net.pop.foodify.model.remote.meals.MealsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("list.php")
    suspend fun getCategories(@Query("c") list: String = "list"): CategoriesResponse
    @GET("list.php")
    suspend fun getAreas(@Query("a") list: String = "list"): AreasResponse
    @GET("list.php")
    suspend fun getIngredients(@Query("i") list: String = "list"): IngredientsResponse

    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") category: String): MealsResponse

    @GET("filter.php")
    suspend fun getMealsByArea(@Query("a") area: String): MealsResponse
    @GET("filter.php")
    suspend fun getMealsByIngredient(@Query("i") ingredient: String): MealsResponse
    @GET("lookup.php")
    suspend fun getMealById(@Query("i") mealId: String): MealResponse
}