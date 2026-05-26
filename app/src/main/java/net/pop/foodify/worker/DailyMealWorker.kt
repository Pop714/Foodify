package net.pop.foodify.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import androidx.core.net.toUri
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.pop.foodify.R
import net.pop.foodify.data.repos.MealRepository
import java.net.URL

@HiltWorker
class DailyMealWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted workerParams: WorkerParameters,
    private val repository: MealRepository
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            val response = repository.getRandomMeal()
            val meal = response.meals.firstOrNull()

            if (meal != null) {
                val bitmap = downloadImageAsBitmap(meal.strMealThumb.toString())
                showNotification(meal.strMeal.toString(), meal.idMeal.toString(), bitmap)
            }
            Result.success()
        } catch (_: Exception) {
            Result.retry()
        }
    }

    private suspend fun downloadImageAsBitmap(imageUrl: String): Bitmap? {
        return withContext(Dispatchers.IO) {
            try {
                val url = URL(imageUrl)
                BitmapFactory.decodeStream(url.openConnection().getInputStream())
            } catch (_: Exception) {
                null
            }
        }
    }

    private fun showNotification(mealName: String, mealId: String, mealImage: Bitmap?) {
        val channelId = "daily_meal_channel"
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(
            channelId,
            "Daily Meal Notifications",
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = "Notifies you of a new random recipe every day."
        }
        notificationManager.createNotificationChannel(channel)

        val deepLinkUri = "foodify://details/$mealId".toUri()
        val intent = Intent(Intent.ACTION_VIEW, deepLinkUri)
        val pendingIntent = TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        }

        val builder = NotificationCompat.Builder(applicationContext, channelId)
            .setContentTitle("\uD83C\uDF72 Recipe of the Day!")
            .setContentText("How about making $mealName today?")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        if (mealImage != null) {
            builder.setLargeIcon(mealImage)
            builder.setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(mealImage)
                    .bigLargeIcon(null as Bitmap?)
            )
        }

        notificationManager.notify(1, builder.build())
    }
}