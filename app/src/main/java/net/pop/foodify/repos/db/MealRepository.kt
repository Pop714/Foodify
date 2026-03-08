package net.pop.foodify.repos.db

import kotlinx.coroutines.flow.Flow
import net.pop.foodify.model.loacl.CartEntity
import net.pop.foodify.model.loacl.FavoriteMealEntity

interface MealRepository {
    // recipes
    suspend fun insertFavoriteMeal(meal: FavoriteMealEntity)
    suspend fun removeFavoriteMeal(id: String)
    fun getAllFavorites(): Flow<List<FavoriteMealEntity>>
    fun isFav(id: String): Flow<Boolean>

    // cart
    suspend fun insertCartItem(cartItem: CartEntity)
    suspend fun deleteCartItem(cartItem: CartEntity)
    fun getAllCartItems(): Flow<List<CartEntity>>
    suspend fun clearCart()
}