package net.pop.foodify.model.loacl

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val ingredient: String,
    val measure: String
)
