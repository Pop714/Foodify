package net.pop.foodify.model.loacl

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_meals")
data class FavoriteMealEntity(
    @PrimaryKey val idMeal: String,
    val strMeal: String,
    val strMealThumb: String
)
