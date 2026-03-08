package net.pop.foodify.di

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.pop.foodify.model.loacl.CartDao
import net.pop.foodify.model.loacl.FavoriteMealDao
import net.pop.foodify.model.loacl.FoodifyDatabase
import net.pop.foodify.repos.db.MealRepository
import net.pop.foodify.repos.db.MealRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideFoodifyDatabase(@ApplicationContext context: Context): FoodifyDatabase {
        return Room.databaseBuilder(
            context, FoodifyDatabase::class.java, "foodify_db"
        ).fallbackToDestructiveMigration(false).build()
    }

    @Provides
    @Singleton
    fun provideFavoriteMealDao(database: FoodifyDatabase): FavoriteMealDao {
        return database.favoriteMealDao
    }

    @Provides
    @Singleton
    fun provideCartDao(database: FoodifyDatabase): CartDao {
        return database.cartDao
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DBRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMealRepository(
        mealRepositoryImpl: MealRepositoryImpl
    ): MealRepository
}