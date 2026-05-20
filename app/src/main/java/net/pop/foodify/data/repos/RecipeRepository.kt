package net.pop.foodify.data.repos

import kotlinx.coroutines.flow.Flow
import net.pop.foodify.data.local.entity.CartEntity
import net.pop.foodify.data.local.entity.FavoriteRecipeEntity

interface RecipeRepository {
    // recipes
    suspend fun insertFavoriteRecipe(recipe: FavoriteRecipeEntity)
    suspend fun removeFavoriteRecipe(id: String)
    fun getAllFavorites(): Flow<List<FavoriteRecipeEntity>>
    fun isFav(id: String): Flow<Boolean>

    // cart
    suspend fun insertCartItem(cartItem: CartEntity)
    suspend fun deleteCartItem(cartItem: CartEntity)
    fun getAllCartItems(): Flow<List<CartEntity>>
    suspend fun clearCart()
}