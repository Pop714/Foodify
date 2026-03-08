package net.pop.foodify.repos.db

import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import net.pop.foodify.model.loacl.CartDao
import net.pop.foodify.model.loacl.CartEntity
import net.pop.foodify.model.loacl.FavoriteMealDao
import net.pop.foodify.model.loacl.FavoriteMealEntity

class MealRepositoryImpl @Inject constructor(
    private val dao: FavoriteMealDao,
    private val cartDao: CartDao
) : MealRepository {


    override suspend fun insertFavoriteMeal(meal: FavoriteMealEntity) {
        dao.insertFavorite(meal)
    }

    override suspend fun removeFavoriteMeal(id: String) {
        dao.deleteFavoriteById(id)
    }

    override fun getAllFavorites(): Flow<List<FavoriteMealEntity>> {
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