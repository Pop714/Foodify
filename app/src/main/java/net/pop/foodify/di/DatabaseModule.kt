package net.pop.foodify.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.pop.foodify.data.local.FoodifyDB
import net.pop.foodify.data.local.dao.CartDao
import net.pop.foodify.data.local.dao.FavoriteRecipeDao
import net.pop.foodify.data.repos.RecipeRepository
import net.pop.foodify.domain.repos.RecipeRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("ALTER TABLE cart_items ADD COLUMN mealThumb TEXT NOT NULL DEFAULT ''")
            db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_cart_items_mealName_ingredient` ON `cart_items` (`mealName`, `ingredient`)")
        }
    }

    @Provides
    @Singleton
    fun provideFoodifyDatabase(@ApplicationContext context: Context): FoodifyDB {
        return Room.databaseBuilder(
            context, FoodifyDB::class.java, "foodify_database"
        )
            .addMigrations(MIGRATION_1_2)
            .fallbackToDestructiveMigration(false)
            .build()
    }

    @Provides
    @Singleton
    fun provideFavoriteRecipeDao(database: FoodifyDB): FavoriteRecipeDao {
        return database.favoriteRecipeDao
    }

    @Provides
    @Singleton
    fun provideCartDao(database: FoodifyDB): CartDao {
        return database.cartDao
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DBRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindMealRepository(
        mealRepositoryImpl: RecipeRepositoryImpl
    ): RecipeRepository
}