# GIFSearcher
Simple android app that is able to show trending GIFs and search for GIFs, using Giphy API.

## Some technial details
Below is a list of the libraries used in project:
* HTTP client using [Retrofit](https://github.com/square/retrofit)
* Yalantis [JellyToolbar](https://github.com/Yalantis/JellyToolbar)
* Media management and image loading framework for Android [Glide](https://github.com/bumptech/glide)

## Building and running
Run `./gradlew check` to compile and run the unit tests. If everything passes, you can run the app on any emulator or device with API > 16 with `./gradlew assembleDebug installDebug`.
