# Foodify - Modern Android Recipe App

Foodify is a feature-rich, beautifully animated Android application built entirely with **Jetpack Compose** and modern Android development practices. It consumes data from [TheMealDB API](https://www.themealdb.com/api.php) to provide users with thousands of recipes, categorized by area, ingredient, and type. 

Beyond discovering recipes, Foodify allows users to curate a personal list of favorite meals and intelligently extract recipe ingredients into a local Shopping Cart.

## Key Features

* **Smart Discoverability:** Filter meals seamlessly by Area, Category, or Ingredient using animated, interactive filter chips.
* **Detailed Recipe Pages:** View high-quality meal imagery, exact ingredient measurements, step-by-step instructions, and embedded YouTube tutorials.
* **Favorites System:** Save your go-to meals locally. The UI instantly reacts to database changes using Kotlin Flows.
* **Smart Shopping Cart:** Extract specific ingredients and measurements from a recipe via a beautiful popup dialog and add them to a dedicated, interactive shopping list.
* **Fluid Animations:** Features premium UI touches including crossfade screen transitions, animated bottom navigation bars, and reactive button states.
* **Native Sharing:** Easily share recipe links with friends and family using Android's native share sheet.

## Tech Stack & Architecture

Foodify is built utilizing **Clean Architecture** principles and the **MVVM (Model-View-ViewModel)** design pattern to ensure separation of concerns, testability, and scalable code.

* **UI:** [Jetpack Compose](https://developer.android.com/jetpack/compose) - Modern, declarative native UI toolkit.
* **Language:** [Kotlin](https://kotlinlang.org/)
* **Asynchronous Programming:** [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & [Flows](https://kotlinlang.org/docs/flow.html) - For handling background threads and reactive UI states.
* **Dependency Injection:** [Dagger-Hilt](https://dagger.dev/hilt/) - For robust, compile-time dependency injection.
* **Local Database:** [Room](https://developer.android.com/training/data-storage/room) - SQLite object mapping library for the Favorites and Shopping Cart systems.
* **Network:** [Retrofit](https://square.github.io/retrofit/) & Gson - For making type-safe REST API calls.
* **Image Loading:** [Coil](https://coil-kt.github.io/coil/) - For fast, lightweight image loading with Compose support.
* **Navigation:** Jetpack Compose Navigation - For routing and passing arguments between screens.

## Project Setup & Installation

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/Pop714/Foodify.git](https://github.com/Pop714/Foodify.git)