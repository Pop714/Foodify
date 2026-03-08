package net.pop.foodify.model.loacl

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(meal: FavoriteMealEntity)

    @Query("DELETE FROM favorite_meals WHERE idMeal = :id")
    suspend fun deleteFavoriteById(id: String)

    @Query("SELECT * FROM favorite_meals")
    fun getAllFavorites(): Flow<List<FavoriteMealEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_meals WHERE idMeal = :id)")
    fun isFavorite(id: String): Flow<Boolean>
}