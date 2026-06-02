package net.pop.foodify.domain.updater

sealed class UpdateStatus {
    object UpToDate : UpdateStatus()
    object RecommendedUpdate : UpdateStatus()
    object ForceUpdate : UpdateStatus()
}