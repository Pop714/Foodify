package net.pop.foodify.model.loacl

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FavoriteMealEntity::class, CartEntity::class],
    version = 2,
    exportSchema = false
)
abstract class FoodifyDatabase : RoomDatabase() {
    abstract val favoriteMealDao: FavoriteMealDao
    abstract val cartDao: CartDao
}