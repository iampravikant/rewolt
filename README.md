# ReWolt

ReWolt displays nearby restaurants based on location.

`#mvvm` `#repository` `#usecase` `#retrofit` `#coroutines` `#room` `#dagger` `#tests` `#viewbinding`

https://user-images.githubusercontent.com/31407384/152607096-b7783012-ee4e-4ef6-884e-51499c105fdc.mp4

## Android Studio Project

- The project is built using [**Android Studio Bumblebee | 2021.1.1**](https://developer.android.com/studio/releases#bumblebee)
- [**Kotlin DSL**](https://docs.gradle.org/current/userguide/kotlin_dsl.html) based gradle files instead of Groovy
- Gradle version - **7.1.0**
- Minimum SDK - **23** (Supporting > 94% devices)
- Targetting SDK - **32**
- ViewBinding
- MVVM

## Dependencies

- appcompat, constraint-layout, material, navigation
- **Dependency injection**: dagger2 hilt
- **Image loading**: Glide
- **Network calls**: retrofit2, gson, logging-interceptor
- **Databse**: room
- **Testing**: junit, mockito, espresso, androidx test code, runner, rules, coroutine test

*All the features of the app could have been acheived with lesser dependencies, but some libs were used for demonstration purpose.*

## Project Design Approach

- Although the app is small in terms of size and complexity, the project is designed keeping medium scaling in mind. 
- The idea is to keep a bit of abstraction and therefore overall project can be divided into three parts: domain, data and ui.
- To keep it simple, all these 3 layers reside in the app module in seperate packages instead of having 3 seperate modules to deal with the layers.

### Repository

- `RestaurantRepo` - It deals with 2 functions - `get` (Get the list of restaurants) and `update` (Update a restaurant in local db)

```
interface RestaurantRepo {
    suspend fun get(lat: Double, lon: Double): List<RestaurantEntity>
    suspend fun update(id: String, fav: Boolean)
}
```

A repository is defined as an `interface` in the domain layer and is implemented in the data layer.

### UseCase

UseCases acts as the entry point to the data layer from ui. Ever data driven task that the app has to perfom has a UseCase of its own.

- `GetRestaurants` - This fetches restaurans over the network from the api. Onces fetched, first 15 restaurants are selcted, their `fav` status is fetched from the db, and are returned to the ui. Aprt from this, the ids of these restaurants are also saved in the db so that their `fav` status can be persisted.
- `UpdateRestaurant` - This updates the restaurant's `fav` status in the db.
- `LocationProvided` - This class acts as a mock location provideder. It sends a location to the `ViewModel` from a pre-defined list after every 15 seconds. In reality, the `ViewModel` would recieve the location from the GPS in a smiliar fashion.

The restult of a usecase is encapsulated in a `Result` sealed class which has a `Success`, `GenericError` and `NetworkError` class.

### Remote

The idea is that every repository (which has a network data source), will have a respective remote class. This is also refered as retrofit service interface.
Haveing seperate remote for each repo helps keep the code clean and redable.

- `RestaurantRemote` - This is resposible to perform network operations related to `restaurants` endpoint.

A Remote deals with model classes known as Entity. Eg: `RestaurantEntity`

### Database

The project is built using room database to persisting data

- `RestaurantDao` - This is responsible for performing required table operations for `RestaurantData` table.

A dao deals with model classes known as Data. Eg: `RestaurantData`
If required, conversion between Entity and Data class happens in the repository implementation.

### UI

- The app has only 1 activity called `MainActivity` and it has its own `MainViewModel`
- The activity observes livedatas in viewmodel. 
- There are 2 `LiveData`
  - `viewstate` - This contains the loading and error state to be shown on the screen.
  - `restaurants` - This contains the list of restaurants to be shown as a list on the screen.

## Tests

The app has following tests:

- Unit tests 
  - Testing functionality of `MainViewModel`: `MainViewModelTest`
  - Testing `EntityConverted` extension functions: `EntityConverterTest`

- Android tests
  - Testing room `RestaurantDao`: `RestaurantDaoTest`
  - Testing `ViewExt` extension functions: `ViewExtTest`

## Tasks

#### UI and design
The app is designed to work with both :full_moon:	**standard** and :new_moon: **dark** mode.

The list design is inspired by the Wolt Android app.

#### Location while in background

To achieve this, following options were considered:

- `PeriodicWorkRequest` - This won't work for our us as we want location to be refreshed every 15 seconds. For `PeriodicWorkRequest`, the minimum interval between two periodic work request is 15 min which is defined by MIN_PERIODIC_INTERVAL_MILLIS
- `OneTimeWorkRequest` - We can use this and after 15 seconds shedule another `OneTimeWorkRequest` from inside the first. However, this would not be an ideal solution.
- Foreground `Service` - Although a vaiable option, this could be an overkill as the user would always see a notification and it is not ideal for a restaurant app.
- :white_check_mark: `Coroutines` - This seems to be the best solution amongst all. Run a `while` loop with `delay` inside `CoroutineScope`. This would work even when the app is in background as ViewModel survives app background too. However, this would stop when the user or Android decides to kills the app.

#### Orientation

As required, the app only has portrait mode.

.
.
.
Pramod Ravikant

*Ok bye!*
