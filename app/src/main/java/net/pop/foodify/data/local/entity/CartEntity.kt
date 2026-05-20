package net.pop.foodify.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "cart_items",
    indices = [
        Index(value = ["mealName", "ingredient"], unique = true)
    ]
)
data class CartEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val mealName: String,
    val mealThumb: String,
    val ingredient: String,
    val measure: String
)
