# Part I

## What is Android?

### Android is an open-source operating system for mobile devices developed by Google. It is based on the Linux kernel and provides a rich application framework for building mobile applications. It is used on a variety of devices including smartphones, tablets, smartwatches, and TVs.

## What is the mobile application development approach we learnt through Android development?

### The approach we learned involves using the Android SDK (Software Development Kit) to create applications in Java or Kotlin. It involves using Android Studio for development, implementing Android components such as Activities, Services, and Broadcast Receivers, and handling user interactions through UI elements.

## What is Android SDK? What are the components in SDK?

### The Android SDK is a set of tools provided by Google for developing Android applications. It includes:

- Android Platform Tools: Used to manage and build Android applications.
- Android Build Tools: Used for compiling and packaging the application.
- Android Emulator: For running Android apps on your computer.
- SDK Tools: Utilities like ADB (Android Debug Bridge) for debugging, managing devices, and more.
- System Images: For running Android versions on the Emulator.

## What is API level of Android?

### The API level refers to the version of Android’s Application Programming Interface (API) and corresponds to a specific Android version. Each version of Android has a unique API level number, such as API Level 30 for Android 11. This helps developers ensure their apps are compatible with certain versions of Android.


## What are minSdkVersion and targetSdkVersion? And where would you specify these?

- minSdkVersion: Specifies the minimum API level required to run the app. If the device's API level is lower than this version, the app won’t run.
- targetSdkVersion: Specifies the API level that the app is optimized for. The app will run on higher versions, but with behavior expected from the target SDK.
- These are specified in the build.gradle file (Module level) under the defaultConfig block.


## Demonstrate with Android Studio

### This would typically involve opening Android Studio, creating a new project, setting minSdkVersion and targetSdkVersion in the build.gradle file, and running the project on the emulator or a real device to demonstrate how they work.

# Part II

## Explain the Android activity lifecycle?

### The Android Activity lifecycle refers to the various stages that an Activity goes through during its existence, from creation to destruction. Key lifecycle methods include:

- onCreate(): Called when the activity is first created.
- onStart(): Called when the activity becomes visible to the user.
- onResume(): Called when the activity starts interacting with the user.
- onPause(): Called when the system is about to start resuming another activity.
- onStop(): Called when the activity is no longer visible.
- onDestroy(): Called before the activity is destroyed.


## What is the purpose of the AndroidManifest.xml file?

### The AndroidManifest.xml file is a vital component of every Android application. It provides essential information about the app to the Android system, including:

- Declaring activities, services, and other components.
- Specifying app permissions.
- Defining the app’s entry point (main activity).
- Defining features like themes and UI properties.


## How does Jetpack Compose simplify UI development in Android?

### Jetpack Compose is a modern, declarative UI toolkit for Android development. It simplifies UI development by:

- Using Kotlin-based composable functions instead of XML layout files.
- Reducing boilerplate code.
- Supporting real-time updates to the UI based on state changes.
- Lifecycle-awareness, automatically handling updates when the app’s state changes.


## What are the available layouts in Compose libraries?

### Common layout components in Jetpack Compose include:

- Column: Displays items vertically.
- Row: Displays items horizontally.
- Box: Allows stacking of elements on top of each other.
- ConstraintLayout: Offers a flexible and responsive layout mechanism.
- LazyColumn and LazyRow: For efficient, scrollable lists.


## How can you manage state in Jetpack Compose?

### State management in Jetpack Compose can be done using:

- remember: To store values across recompositions.
- mutableStateOf: To create state variables that trigger recomposition when changed.
- State: Represents the current value of a state.
- ViewModel: To handle business logic and state persistence across activity/fragment lifecycles.

# Part III

## What are data persistence techniques in Android?

### Android provides several methods for data persistence:

- SharedPreferences: Stores key-value pairs for simple data like user settings.
- SQLite: A lightweight, relational database for structured data.
- Room: An abstraction layer over SQLite that simplifies database operations.
- File Storage: Saving data to files, either internal or external.
- Content Providers: Used to share data between apps.

## What is the best method to handle user preference data specific to an application?

### The best method to store user preferences is using SharedPreferences. It allows for storing small pieces of data, such as settings, and is key-value based.

## Explain the difference between Room Database and SQLite in Android development.

-  SQLite is a low-level relational database. You need to write raw SQL queries for operations.
- Room is a higher-level abstraction that simplifies SQLite interactions. It uses annotations for entities and data access objects (DAOs), offering compile-time verification and reducing boilerplate code.

# Part IV

## What is a coroutine in Kotlin?

### A coroutine is a lightweight thread used to perform asynchronous tasks in Kotlin. It helps with handling long-running operations like network requests or database access without blocking the main thread.

## Briefly explain MVVM architecture.

### MVVM (Model-View-ViewModel) is a software architectural pattern used to separate concerns:

- Model: Represents the data and business logic.
- View: Represents the UI elements.
- ViewModel: Acts as a mediator between the View and the Model, handling the UI logic and managing state.

### What are two built-in components in Kotlin, commonly used to implement the MVVM architecture in Android apps?

- LiveData: A lifecycle-aware data holder used to manage UI-related data in the ViewModel.
- ViewModel: A lifecycle-aware component designed to store and manage UI-related data, ensuring it survives          configuration changes like screen rotations.
