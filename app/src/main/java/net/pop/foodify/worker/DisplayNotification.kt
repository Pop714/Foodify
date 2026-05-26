package net.pop.foodify.worker

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.Calendar
import java.util.concurrent.TimeUnit

fun scheduleDailyMealNotification(context: Context) {
    val currentDate = Calendar.getInstance()

    val targetDate = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 12)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
    }

    if (targetDate.before(currentDate)) {
        targetDate.add(Calendar.HOUR_OF_DAY, 24)
    }

    val timeDiff = targetDate.timeInMillis - currentDate.timeInMillis

    val dailyWorkRequest = PeriodicWorkRequestBuilder<DailyMealWorker>(24, TimeUnit.HOURS)
        .setInitialDelay(timeDiff, TimeUnit.MILLISECONDS)
        .build()

    WorkManager.getInstance(context).enqueueUniquePeriodicWork(
        "DailyMealNotification",
        ExistingPeriodicWorkPolicy.KEEP,
        dailyWorkRequest
    )
}