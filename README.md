# Home 🐶

Home is a small application that lets you search for dogs by breed, and click a dog to see 
some details about it.

## Project Details ⚙️

* 100% Kotlin
* Modern architecture (Multi-module setup, MVI, Single-Activity, Compose, Hilt)
* Gradle convention plugins
* The Square buffet

## How Do I Navigate This Project?

Home relies on [Gradle Convention Plugins](https://docs.gradle.org/current/samples/sample_convention_plugins.html) to make it easy to add new modules. It's heavily inspired by [this](https://developer.squareup.com/blog/herding-elephants/) blog post. You can start in the `build-logic` module to see how this works.

The `core-common` module has some shared functionality that can be used across the app, `core-model` is where the domain models live, `core-network` is where all the Remote data plumbing lives. 

`library-data` is where you'll see some work being done. The `BreedRemoteSource` lives there, which you can use to perform network requests.

`library-analytics` is where analytics wrappers live. Currently, only [Timber](https://github.com/JakeWharton/timber) is added to this project.

`feature-list` is a UI module. It has the Search/List feature that you see when you first open the app, and its UI is written in XML.

`feature-detail` is a UI module. It has the detail view that you're taken to when you select a search result, and its UI is written fully in Jetpack Compose.

## Future Plans / What Would I Do Differently? ⏳

This is a live document, so these thoughts might change in the future.

- I'd like to split the `core-common` module into smaller modules. Currently, logic inside it is
separated by package, which is good enough at the moment, but can be worth revisiting in the future.
- I'd like to explore making this app work offline
- Add UI / Snapshot tests
- Explore more complex UIs in Compose
- Add animations for transitions from List to Detail

## Built With 🏗

- [Kotlin](https://kotlinlang.org/)
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture)
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
    - [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
    - [Compose](https://developer.android.com/jetpack/compose)
    - [Navigation](https://developer.android.com/guide/navigation)
- [Retrofit](https://square.github.io/retrofit/)
- [Moshi](https://github.com/square/moshi)

## License

     Copyright 2022 Adam Ahmed

     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.
