package net.pop.foodify.domain.repos

import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import net.pop.foodify.data.local.dao.CartDao
import net.pop.foodify.data.local.dao.FavoriteRecipeDao
import net.pop.foodify.data.local.entity.CartEntity
import net.pop.foodify.data.local.entity.FavoriteRecipeEntity
import net.pop.foodify.data.repos.RecipeRepository

class RecipeRepositoryImpl @Inject constructor(
    private val dao: FavoriteRecipeDao,
    private val cartDao: CartDao
) : RecipeRepository {


    override suspend fun insertFavoriteRecipe(recipe: FavoriteRecipeEntity) {
        dao.insertFavorite(recipe)
    }

    override suspend fun removeFavoriteRecipe(id: String) {
        dao.deleteFavoriteById(id)
    }

    override fun getAllFavorites(): Flow<List<FavoriteRecipeEntity>> {
        return dao.getAllFavorites()
    }

    override fun isFav(id: String): Flow<Boolean> {
        return dao.isFavorite(id)
    }

    override suspend fun insertCartItem(cartItem: CartEntity) {
        cartDao.insertCartItem(cartItem)
    }

    override suspend fun deleteCartItem(cartItem: CartEntity) {
        cartDao.deleteCartItem(cartItem)
    }

    override fun getAllCartItems(): Flow<List<CartEntity>> {
        return cartDao.getAllCartItems()
    }

    override suspend fun clearCart() {
        cartDao.clearCart()
    }
}