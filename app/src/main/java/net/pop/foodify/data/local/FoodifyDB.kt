package net.pop.foodify.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import net.pop.foodify.data.local.dao.CartDao
import net.pop.foodify.data.local.dao.FavoriteRecipeDao
import net.pop.foodify.data.local.entity.CartEntity
import net.pop.foodify.data.local.entity.FavoriteRecipeEntity

@Database(
    entities = [FavoriteRecipeEntity::class, CartEntity::class],
    version = 2,
    exportSchema = false
)
abstract class FoodifyDB : RoomDatabase() {
    abstract val favoriteRecipeDao: FavoriteRecipeDao
    abstract val cartDao: CartDao
}