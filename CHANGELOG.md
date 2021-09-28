# *1.6.0*

Twenty-second release of RainbowCake.

## What's new

- Added a `removeObserver` method to `LiveDataCollection`

### Dependency updates

- Kotlin 1.5.31

---

# *1.5.0*

Twenty-first release of RainbowCake.

## What's new

### Dagger Hilt support

Using [Dagger Hilt](https://dagger.dev/hilt/)? RainbowCake now has you covered! Huge thanks to [stewe93](https://github.com/stewe93) for contributing this.

1. Include the new Hilt artifact:

    ```groovy
    implementation "co.zsmb:rainbow-cake-hilt:$rainbow_cake_version"
    ```  

2. Set up Hilt following the [official Android guide](https://developer.android.com/training/dependency-injection/hilt-android).

3. Call into the `getViewModelFromFactory` function of the `co.zsmb.rainbowcake.hilt` package in
   your Activities or Fragments.

Check out the `hilt-demo` module for an example of a basic setup.

Note: You may also use multiple DI solutions within the same RainbowCake project simultaneously, for example if you're migrating a project piece by piece. Even shared ViewModels will work!

**There is now a [Blank Hilt](https://github.com/rainbowcake/sample-blank-hilt) starter project available showcasing a blank app set up with RainbowCake's Hilt support.**

### Dependency updates

- Android Gradle Plugin 7.0.1
- Kotlin 1.5.30
- Coroutines 1.5.1
- AppCompat 1.3.1
- ConstraintLayout 2.1.0
- Material 1.4.0
- Lifecycle 2.3.1
- Dagger 2.38.1
- Koin 3.1.2
- Timber 5.0.0

---

# *1.4.0*

Twentieth release of RainbowCake.

## What's new

### Integration of requireKTX

Instead of providing its own extensions for requiring values from Bundles, RainbowCake now depends on [requireKTX](https://github.com/zsmb13/requireKTX) to handle these operations. The `require` style methods in RainbowCake are now deprecated and will be removed.

### Updated Koin module to use Koin 3.x

Migrated the Koin integration module from 2.x to 3.x, including the change of dependency coordinates from `org.koin` to `io.insert-koin`.  For more about the 3.x version of Koin, see [its documentation](https://insert-koin.io/docs/setup/v3).

Thanks to [Benjiko99](https://github.com/Benjiko99) for notifying me about this change.

### Minor things

- Expose a `CoroutineScope` as the receiver of the lambda passed to `execute` methods
- Mark `ioContext` as `@InternalRainbowCakeApi` instead of using deprecations on it
- Migrate to an up-to-date Maven publishing setup

### Dependency updates

- Removed jcenter as a dependency
- Android Gradle Plugin 4.2.1
- Kotlin 1.5.10
- Coroutines 1.5.0
- AppCompat 1.3.0
- Material 1.3.0
- Lifecycle 2.3.1
- Dagger 2.36
- Koin 3.0.2
- ConstraintLayout 2.0.4

---

# *1.3.0*

Nineteenth release of RainbowCake.

## What's new

### Support for DialogFragment and BottomSheetDialogFragment

RainbowCake now ships two classes for supporting special kinds of Fragments. Using the new `RainbowCakeDialogFragment` and `RainbowCakeBottomSheetDialogFragment` (now isn't that a mouthful!) classes works very similarly to using `RainbowCakeFragment`.

Thanks to [julienherrero](https://github.com/julienherrero) for their contribution that kicked this off.

### Dependency updates

- Kotlin 1.4.20
- Android Gradle Plugin 4.1.1

---

# *1.2.0*

Eighteenth release of RainbowCake.

## What's new

### Eased requirements for layout inflation in RainbowCakeFragments

Previously, this base Fragment strictly required overriding the `getViewResource` method, and inflated the layout returned from that method in `onCreateView`, which was also mandatory to call if overridden.

This was inconvenient when using View Binding or Date Binding, so now:

- `getViewResource` is no longer abstract, instead it returns `0` by default. If you don't override `getViewResource`, you _must_ override `onCreateView`.
- Overriding `onCreateView` no longer requires a call to the super method.

### Introduced the `@InternalRainbowCakeApi` opt-in annotation

This annotation is only for internal use between RainbowCake's modules. Previous visibility hacks have been replaced with this new annotation. You should generally avoid opting into its usage, as it's not guaranteed public API.

The `coroutineScope` used by `RainbowCakeViewModel` is now exposed as `@InternalRainbowCakeApi` for extensions that need access to this scope.

### Small changes

- Removed the `Application` receiver of the `rainbowCake` configuration function, to make it easier to call

### Dependency updates

- Kotlin 1.4.10
- Android Gradle Plugin 4.1.0
- Dagger 2.29.1
- Gradle wrapper 6.7
- AndroidX libraries to latest versions

---

# *1.1.0*

Seventeenth release of RainbowCake.

## What's new

### Kotlin 1.4 update

The project is now compiled with Kotlin 1.4, the latest stable version of Kotlin 🎉. This cleaned up a bit of the implementation, and most importantly, all library modules now have [explicit API mode](https://kotlinlang.org/docs/reference/whatsnew14.html#explicit-api-mode-for-library-authors) enabled, ensuring that all public API is explicitly marked as public.

### Small things

- Fixed an issue with built-in logging [#16](https://github.com/rainbowcake/rainbowcake/pull/16) (Thanks to Tamás Vágó!)
- Updated target and compile SDK versions to 30
- Version updates for various dependencies

---

# *1.0.0*

Sixteenth release of RainbowCake.

## What's new:

### Removed deprecated constructs

A handful or previously deprecated pieces of code are now removed. If you want to migrate away from them, use `0.7.0` and IDE migration features before upgrading to `1.0.0`.

- `JobViewModel`: replace usages with `RainbowCakeViewModel`
- `RainbowCakeViewModel#postEvent`: update view state from the UI thread instead
  _ Navigation extensions in `co.zsmb.rainbowcake.extensions`: use the methods from the `co.zsmb.rainbowcake.navigation.extensions` package instead
- The `rainbow-cake-channels` artifact: use [Flows](https://rainbowcake.dev/best-practices/flows/) instead

### Small stuff

- **ViewModels are now initialized in `onCreate` instead of `onAttach`**
- Decoupled event dispatches, no synchronous, blocking dispatch anymore for either state or events
- Improved internal logging
- Updated Koin & Dagger to latest version
- Updated visibility of a lots of things
- Optimizations, more inline methods and helpers
- Code documentation updates
- Small bugfixes

---

# *0.7.0*

Fifteenth release of RainbowCake.

## What's new:

### AndroidX migration

RainbowCake has been migrated to AndroidX dependencies instead of the support libraries, and no longer supports projects built on the support library. A year and a half after release of AndroidX, this seemed like a reasonable time to make the jump.

### `JobViewModel` deprecation

The `JobViewModel` class that provides the coroutine integration of the framework (via `execute`) is now deprecated, and will be removed in a couple releases. Its functionality has been pulled up into the `RainbowCakeViewModel` base class, which now handles view state, events, and coroutine support. Having just one ViewModel base class in the framework should make things less confusing.

Please replace any usages of `JobViewModel` with `RainbowCakeViewModel` (you get IDE support for this, so it should be trivial).

### Channel module deprecation

The `rainbow-cake-channels` module has been deprecated, and will be removed in an upcoming release entirely. Coroutine Flows should replace most usages of channels at this point.

### Small stuff

- Some method visibilities have been restricted in `RainbowCakeFragment`. These method should not be called anywhere outside of descendants of the `Fragment`.
- The `observeStateAndEvents` testing function now has a variant that can observe queued events, in addition to view state and events.
- The `requireArguments` method is now deprecated, as the AndroidX `Fragment` class includes the method, making the extension unnecessary.
- Added some new unit tests for `SingleShotLiveData`.
- Small project configuration updates, dependency version bumps, etc.

---

# *0.6.0*

Fourteenth release of RainbowCake.

## What's new:

### Small stuff

- The reified `popUntil` extension used to return `Unit` instead of `Boolean`, which is what the original method returns to indicate if popping happened. Fixed!
- ViewModel instances are now set in `onAttach` instead of `onCreate`. Because there was no real reason to wait until `onCreate` to do this.
- `ViewModelProviders` has been deprecated, so now `ViewModelProvider` is being used directly.
- Version updates (Gradle 6.2.2, AGP 3.6.1, Kotlin 1.3.70).
- Revamped publishing setup for the libraries.

---

# *0.5.0*

Thirteenth release of RainbowCake.

## What's new:

### Internal changes to ViewModel scopes

Instead of implementing `CoroutineScope` in `JobViewModel`, it now contains a `CoroutineScope` instance, which falls in line with many recommendations regarding scopes, as well as the classic advice of favouring composition over inheritance.

This _should_ be an internal only change, but if you've been abusing the scope interface on ViewModels to launch coroutines on them from Fragments, it's a potentially breaking change.

### First release of `rainbow-cake-test`

The architecture now ships with a dedicated testing module, which supports unit testing the architecture (Yay! 🎉). This testing module is in an experimental status, as it itself relies on experimental coroutine testing libraries at this point.

#### Presenter testing

For Presenter tests, you can use the `PresenterTest` base class, which will replace the IO dispatcher used in Presenters with the test dispatcher, to make it execute immediately:

#### ViewModel testing

For ViewModel tests, you can use the `ViewModelTest` base class, which will replace the Main dispatcher used by the `execute` method with the test dispatcher, and also replace the internal `LiveData` executor with a mock executor that executes everything on a single thread, in a blocking manner.

The difficult part of testing ViewModels is having to observe their reactions to inputs through various `LiveData`-based mechanisms - both state changes and events work this way. To make this easy, the `rainbow-cake-test` library provides the `observeStateAndEvents` function that lets you assert changes to state, as well as any emitted events:

```kotlin
vm.observeStateAndEvents { stateObserver, eventsObserver ->
    vm.loadArticle(1L)
    stateObserver.assertObserved(Loading, ArticleLoaded())
    vm.loadArticle(-1L)
    eventsObserver.assertObserved(InvalidIdError)
}
```

See the extensions on the `MockObserver` class for the currently available assertions. Note that you can also add your own assertion extensions on this class, as needed.

#### All other tests

Testing other, lower level components such as Interactors or Data Sources should not require additional support from the architecture. You can use the experimental [coroutines test library](https://github.com/Kotlin/kotlinx.coroutines/tree/master/kotlinx-coroutines-test) to wrap such tests in `runBlockingTest` calls.

---

# *0.4.4*

Twelfth release of RainbowCake.

## What's new:

The `events` property of `RainbowCakeViewModel` (and consequently, its `LiveDataCollection` type) are now exposed for testing purposes only.

---

# *0.4.3*

Eleventh release of RainbowCake.

## What's new:

The `contentFrame` ID that's used in the `activity_main` layout is now declared in a separate XML file to avoid crashes produced by it not being present if the layout is overridden.

---

# *0.4.2*

Tenth release of RainbowCake.

## What's new:

Not much!

### Updates of various dependencies

- Dagger 2.24 (incremental by default!)
- Kotlin 1.3.50
- Coroutines 1.3.0
- Gradle 5.6
- Android Gradle plugin 3.5.0

### Navigator documentation updates

The `pop` and `popUntil` methods now have documented return values.

---

# *0.4.1*

Ninth release of RainbowCake.

## What's new:

### ViewState handling fixes

Version 0.4.0 caused some unexpected behaviour when reading the `viewState` immediately after setting it to a new value, as continuously blocking the thread between these two operations didn't allow the set operation to complete, therefore the read showed an outdated state. This should be fixed now.

The `postState` method is now even more deprecated than before. You should *really* only update state via `viewState` and from the UI thread. Please.

---

# *0.4.0*

Eight release of RainbowCake.

## What's new:

### Dagger artifact

Dagger related code has been moved to a separate artifact, which you can include the following way:

```groovy
implementation "co.zsmb:rainbow-cake-dagger:$rainbow_cake_version"
```

Some imports have also been changed to reflect this. Here's a quick table of what you'll need to migrate (should be just a quick search & replace, so no script this time):

| Original                                           | Replacement                                          |
| -------------------------------------------------- | ---------------------------------------------------- |
| `co.zsmb.rainbowcake.di.RainbowCakeComponent`      | `co.zsmb.rainbowcake.dagger.RainbowCakeComponent`    |
| `co.zsmb.rainbowcake.di.RainbowCakeModule`         | `co.zsmb.rainbowcake.dagger.RainbowCakeModule`       |
| `co.zsmb.rainbowcake.di.ViewModelKey`              | `co.zsmb.rainbowcake.dagger.ViewModelKey`            |
| `co.zsmb.rainbowcake.RainbowCakeApplication`       | `co.zsmb.rainbowcake.dagger.RainbowCakeApplication`  |
| `co.zsmb.rainbowcake.base.getViewModelFromFactory` | `co.zsmb.rainbowcake.dagger.getViewModelFromFactory` |

### Koin support

Why mess around with all that Dagger stuff? Because it's no longer the only game in town. You may now also use Koin 2.0 for your dependency injection needs with RainbowCake.

1. Include the new Koin artifact:

    ```groovy
    implementation "co.zsmb:rainbow-cake-koin:$rainbow_cake_version"
    ```  

2. Replace Dagger with Koin in your dependencies, here are some recommended artifacts:

    ```groovy
    def koin_version = '2.0.1'
    implementation "org.koin:koin-core:$koin_version"
    implementation "org.koin:koin-android:$koin_version"
    implementation "org.koin:koin-android-viewmodel:$koin_version"
    ```

3. Set up Koin. You won't need `@Inject` annotations any more, but you'll have to declare modules and start up your Koin (ideally, in your `Application`'s `onCreate` method). See the [getting started guide](https://insert-koin.io/docs/2.0/getting-started/android/) for more details.

Note: You may also use the two DI solutions within the same RainbowCake project simultaneously, for example if you're migrating a project piece by piece. Even shared ViewModels should work!

### Proper license

The project is now licensed under Apache 2. One more step towards proper open source.

### Android Studio template updates

The screen template now has an option to generate new screens that are powered by Koin, and both the screen and ListAdapter templates now support AndroidX!

---

# *0.3.0*

Seventh release of RainbowCake.

## What's new:

### Channels artifact

Channel related code has now been moved from the core library to the `rainbow-cake-channels` artifact. It also includes a new feature, converting a `LiveData` instance to a `Channel`:

```kotlin
fun getNews(): ReceiveChannel<List<News>> {
    return newsDao.getNews().toChannel()
}
```

This, as with other Channel related API, is experimental (but seems to work alright).

_(Flow support of various forms is also coming soon.)_

## Changes:

### Navigation extension reorganization

Argument handling extensions have been moved from the core library to the navigation artifact. Their package names have also been changed to reflect this change. The old extensions are now deprecated.

### Documentation

The previously used [documentation repo](https://gitlab.autsoft.hu/AutSoft/AndroidChapter/rainbow-cake/rainbow-cake-guide) is now deprecated. See [rainbowcake.dev](https://rainbowcake.dev) instead.

### Version updates:

- Coroutines 1.2.0

---

# *0.2.0 - The Breaking Changes Release*

# Preface

This release contains many structural changes, and some new features. **However, it contains no critical bugfixes.** This means that if you don't want to suffer the cost of updating to this version right now, the previous version should keep working for you just fine.

If you keep using the old version and do find critical issues in it, report the issue, and a bugfix release using the old structure and package names will be provided for you, if necessary.

# Update steps

Updating your project is going to be just a little bit more effort than usual (should still be about 2 minutes using Studio, really).

1. Update your Gradle dependencies (as necessary, see the [Repackaging](#repackaging) section):

    ```groovy
    def rainbow_cake_version = '0.2.0'
    implementation "co.zsmb:rainbow-cake-core:$rainbow_cake_version"
    implementation "co.zsmb:rainbow-cake-navigation:$rainbow_cake_version"
    implementation "co.zsmb:rainbow-cake-timber:$rainbow_cake_version"
    ```

2. Perform the following search and replace actions in your project - <kbd>Ctrl + Shift + R</kbd>:

   | Original                | Replacement              |
       | ------------------------| ------------------------ |
   | `hu.autsoft.rainbowcake`| `co.zsmb.rainbowcake`    |
   | `BaseViewModel`         | `RainbowCakeViewModel`   |      
   | `BaseFragment`          | `RainbowCakeFragment`    |
   | `BaseActivity`          | `RainbowCakeActivity`    |
   | `BaseApplication`       | `RainbowCakeApplication` |
   | `BaseModule`            | `RainbowCakeModule`      |
   | `BaseComponent`         | `RainbowCakeComponent`   |

   There is also a [migration script](./releases/assets/rc-migration.sh) available to perform this search and replace task. This script does its best to safely migrate your project, but be sure to check the changes it makes.

   The script can be invoked in the following way:

    ```sh
    ./rc-migration.sh ~/projects/MyProject
    ```

   Or for example, on Windows, from a Git bash:

    ```sh
    ./rc-migration.sh /c/projects/MyProject
    ```

3. Add configuration to your project as necessary (see the [New configuration options](#new-configuration-options) section for details).

   Note that previous versions of the library logged internal events to Timber by default, while the new configuration feature disables internal logging by default. **This means you won't see stacktraces of uncaught `Exception`s caught by `JobViewModel` anymore.** If you wish to re-enable previous behaviour, set the following configuration options (again, details explained below):

    ```kotlin
    rainbowCake {
        isDebug = BuildConfig.DEBUG
        logger = Loggers.TIMBER
    }
    ```

4. Update your Android Studio templates (should be just a simple `git pull`).

Read on to see the explanation of why all these steps are required.

# Huge changes

## Repackaging

The package names of the framework, as well as the artifact group IDs have been changed from `hu.autsoft` to `co.zsmb`.

The framework is also no longer being published as a `-SNAPSHOT`. These are now regular, stable releases (albeit non-final, because nothing ever is).

As a modularization effort, the framework is being split up into multiple artifacts - only three, for now. This means including three separate Gradle dependencies in your project, _if_ you actually need the features from all of them.

The currently available artifacts are:

#### Core

```groovy
implementation "co.zsmb:rainbow-cake-core:0.2.0"
```

Contains everything from previous versions, except for the navigation features.

#### Navigation addon

```groovy
implementation "co.zsmb:rainbow-cake-navigation:0.2.0"
```

Contains all the navigation features that were part of the base artifact before.

#### Timber addon

```groovy
implementation "co.zsmb:rainbow-cake-timber:0.2.0"
```

You only need this artifact if you want the framework to log about its internal events (this is mostly just the exceptions caught by `JobViewModel`), and you want it to do so using Timber. See details below.

## Base classes renamed

The base classes `BaseViewModel`, `BaseFragment`[,](https://theliteraryman.files.wordpress.com/2012/06/comma-comma.jpg) and `BaseActivity` have been renamed to `RainbowCakeViewModel`, `RainbowCakeFragment`, and `RainbowCakeActivity`, respectively.

This change makes the `Base*` names available for applications using the framework, so that they may create their own `Base*` classes that inherit from the framework classes, and include any app-specific extra behaviour there.

## New configuration options

The framework now has a configuration DSL, which can be invoked in the `onCreate` method of your `Application` class.

Its usage looks like the following:

```kotlin
override fun onCreate() {
    super.onCreate()

    rainbowCake {
        isDebug = false
        logger = Loggers.NONE
        consumeExecuteExceptions = true
    }
}
```

The available settings, and their possible values:

- `isDebug`: Boolean, `false` by default.
    - If set to false, it disables all internal logging of the framework, regardless of the setting of `logger`. May affect other behaviour in the future as well (in debug mode, prod behaviour will definitely not change). Recommended value is `BuildConfig.DEBUG`.
- `consumeExecuteExceptions`: Boolean, `true` by default (to keep existing behaviour).
    - Determines whether the `execute` method in `JobViewModel` should catch and log any uncaught exceptions in coroutines, or let them crash the app. Recommended to be set to `false` at the very least for debug builds, and should be considered even for production.
- `logger`
    - Determines how the framework should log its internal events. Available options by default are `NONE` (as in no logging) and `ANDROID` (logs to Logcat via `Log.d`).
    - If the `rainbowcake-timber` dependency is included, `TIMBER` may also be used to log via Timber. Note that this doesn't `plant` any `Tree`s, you still have to do that yourself.

## Events rework

Event handling has been significantly reworked under the hood, since they were quite broken in some edge cases.

1. When using shared `ViewModel` instances with scopes, only a single one of the attached `Fragment` would receive the events, chosen randomly.
2. If a `Fragment` was inactive (in the background) while its `ViewModel` posted events, only the last event posted would be delivered when it became active again, due to the nature of `LiveData`.

For the first issue: the new events mechanism ensures that _all attached `Fragment`s_ receive each event, so that they may each react to it as appropriate.

As for the second problem, you may now decide whether an event only makes sense for the `Fragment` to receive immediately (most events will fall in this category!), or if they should be remembered if the `Fragment` is not currently active, and delivered later.

Both of these types of events will still be received in the `onEvent` method of your `RainbowCakeFragment` or `RainbowCakeActivity`, but you have to send them in different ways.

#### Active observer only events

Events that should only be delivered immediately should still implement the `OneShotEvent` marker interface, and be sent using `postEvent`, just like before. (One small caveat: this method can now only be called from the UI thread, which you should already have been doing anyway.) If you send one of these events when the `Fragment` is not active, it will never be delivered.

<img src="./releases/images/0_2_0_active_only_event.png" width="750" align="middle" alt="Active only events">

*99% of the time, this is the behaviour you need for your events, and the type of events you should use.*

#### Queued events

Events that matter even if they can't be delivered immediately have to implement the `QueuedOneShotEvent` marker, and be sent using `postQueuedEvent`. If the observing `Fragment` isn't currently active, the event will be queued, and all queued events will be delivered immediately when the `Fragment` becomes active again.

<img src="./releases/images/0_2_0_queued_event.png" width="750" align="middle" alt="Queued events">

Each `Fragment` instance has its own independent queue of events. Note that `Fragment`s in the background can be destroyed and recreated by the framework, and their queues _will be lost_ in this case - this is a best effort mechanism.

<img src="./releases/images/0_2_0_separate_queues.png" width="500" align="middle" alt="Events with shared ViewModels">

#### Events wrapup

If all of this looks confusing at first, the good news is that you _probably_ don't need all this! You can just keep using events like before, and they'll keep working. They're just much more reliable now.

## MultiDex removed

The framework used to include the multidex support dependency and initialize MultiDex in `BaseApplication` by default. Forcing this on applications in this form was a mistake (most notably since apps targeting API 21+ don't need these to use multidex) and has now been removed.

Any apps targeting API levels below 21 should now perform these steps for themselves, if they require multidex.

# Small changes

## Slicker `popUntil`

The `popUntil` navigation method can now be used with a reified type parameter instead of a `KClass` parameter. So instead of `navigator?.popUntil(HomeFragment::class)`, you can now `navigator?.popUntil<HomeFragment>()`!

## ViewModel scoping improvements

A convenience change in `ViewModel` scoping: before, only `Activity` scoped `ViewModel` instances could have keys. Now you can also key `ViewModel`s scoped to a parent `Fragment`.

The syntax for non-keyed `ParentScope` remains the same as before:

```kotlin
override fun provideViewModel() = getViewModelFromFactory(scope = ParentFragment)
```

And the optional key can be provided in the parameter:

```kotlin
override fun provideViewModel() = getViewModelFromFactory(scope = ParentFragment("some_key"))
```

## Deprecations

`withArgs` has been replaced with `applyArgs` roughly four months ago, therefore using `withArgs` is now an outright error, and doesn't just produce a suppressible warning. An intention action to perform this migration via <kbd>Alt + Enter</kbd> is still available.

## `Contexts` removed

The `Contexts` object that actually contained `Dispatcher` instances has now been removed, and the library uses `Dispatchers` directly instead. `RCDispatchers`.

Note that the `withIOContext` method is still available.

Client code shouldn't really use this object directly, so in theory, this shouldn't break anything.

## Version updates

- Dagger `2.17`.
- Android Gradle plugin `3.3.2`

---

# 0.1.2-SNAPSHOT

Fifth snapshot release of RainbowCake.

## What's new:

### New Navigator method to execute pending actions together

Previously, sequences of `Navigator` method calls have always executed individually, e.g. take this call:

```kotlin
navigator?.run {
    popUntil(HomeFragment::class)
    add(SomeFragment())
}
``` 

Here, the current Fragments on top of `HomeFragment` would have first been removed, `HomeFragment` appeared for a split second, and _then_ `SomeFragment` would be added on top.

You can now prevent this "flashing" behaviour by calling `navigator.executePending()` after a series of actions, like so:

```kotlin
navigator?.run {
    popUntil(HomeFragment::class)
    add(SomeFragment())
    executePending()
}
```

#### Version updates:

- Kotlin 1.3.21
- Android Gradle plugin 3.3.1

---

# 0.1.1-SNAPSHOT

Fourth snapshot release of RainbowCake.

## Update steps:

Update your dependency version:

```groovy
implementation 'co.zsmb:rainbow-cake:0.1.1-SNAPSHOT'
```

## What's new:

### Even more new argument handling methods

New methods have been added to support Fragment arguments with `Int` and `Serializable` types. Note that the latter of these still isn't a recommendation to pass around large objects as arguments, it's only meant to serve as a way to pass around some small objects like `java.util.UUID` easier, without having to convert it to a `String` and back.

These, again, conform to the naming convention of existing argument handling methods, and you can see them all [here](https://gitlab.autsoft.hu/AutSoft/AndroidChapter/rainbow-cake/rainbow-cake/blob/6f23bfc345e3939b4b4303f9d42022e1c7c925b5/rainbow-cake/src/main/java/hu/autsoft/rainbowcake/extensions/Bundle.kt).

All of these `Bundle` methods are also now documented and [tested](https://gitlab.autsoft.hu/AutSoft/AndroidChapter/rainbow-cake/rainbow-cake/blob/6f23bfc345e3939b4b4303f9d42022e1c7c925b5/rainbow-cake/src/androidTest/java/hu/autsoft/rainbowcake/extensions/BundleTest.kt) according to their documented behaviour. (Their internal implementation has also been unified to simplify them and make them safer.)

### A new Navigator convenience method

The `Navigator` interface now has a [`setStack(Iterable<Fragment>)`](https://gitlab.autsoft.hu/AutSoft/AndroidChapter/rainbow-cake/rainbow-cake/blob/6f23bfc345e3939b4b4303f9d42022e1c7c925b5/rainbow-cake/src/main/java/hu/autsoft/rainbowcake/navigation/Navigator.kt#L89) method to complement the existing `setStack(vararg Fragment)` method, and avoid having to convert `List`s and other iterables to arrays.

#### Version updates:

- Kotlin 1.3.20

---

# 0.1.0-SNAPSHOT

Third snapshot release of RainbowCake.

## Update steps:

Update your dependency version:

```groovy
implementation 'co.zsmb:rainbow-cake:0.1.0-SNAPSHOT'
```

Optionally, update your screen templates, which can now be done in a more convenient way via [a simple `git clone`](https://gitlab.autsoft.hu/AutSoft/AndroidChapter/rainbow-cake/rainbow-cake-templates#git-cloned-installation).

## What's new:

### Shared ViewModels between Fragments

ViewModels by default are scoped to their Fragment, meaning a new instance is created for every new instance of the Fragment (barring configuration changes), and they are cleared when their Fragment is destroyed (as in their lifecycle completely ends).

There are use cases where it would make sense to share ViewModel instances between Fragments, and the `getViewModelFromFactory` method now provides an opportunity for this in the form of an optional parameter. ViewModels may now be scoped to the current Activity or to a parent Fragment. For details, see the documentation [here](https://gitlab.autsoft.hu/AutSoft/AndroidChapter/rainbow-cake/rainbow-cake/blob/b6d914c3f5949935b5bf5dada5a555f80960af9b/rainbow-cake/src/main/java/hu/autsoft/rainbowcake/base/ViewModelScope.kt).

A demo showcasing a `ViewPager` where the pages share a ViewModel scoped to their parent Fragment is also available [here](https://gitlab.autsoft.hu/AutSoft/AndroidChapter/rainbow-cake/rainbow-cake/tree/dev/demo/src/main/java/hu/autsoft/rainbowcake/demo/ui/sharedvmpager).

### Navigation improvements

The `Navigator` provided by the library contains a fade animation between screen changes by default. It's not possible to override this default behaviour.

You can override it globally, by providing new values for certain properties in your Activity that inherits from `NavActivity`:

```kotlin
class MainActivity : SimpleNavActivity() {

    override val defaultEnterAnim: Int = R.anim.slide_in_right
    override val defaultExitAnim: Int = R.anim.slide_out_left
    override val defaultPopEnterAnim: Int = R.anim.slide_in_left
    override val defaultPopExitAnim: Int = R.anim.slide_out_right
    
}
```

You can also override animations one by one, by using overloads of the `add` and `replace` methods:

```kotlin
navigator?.add(SomeFragment(),
    enterAnim = R.anim.slide_in_right,
    exitAnim = R.anim.slide_out_left,
    popEnterAnim = R.anim.slide_in_left,
    popExitAnim = R.anim.slide_out_right
)
```

Note that a simple `0` may be used for any of these values to disable an animation altogether.

Be sure to check the documentation for all of the properties and methods mentioned above, as they contain much more information.

### New argument handling methods

New methods have been added to support Fragment arguments with `Boolean` and `Parcelable` types. These conform to the naming convention of existing argument handling methods, see them [here](https://gitlab.autsoft.hu/AutSoft/AndroidChapter/rainbow-cake/rainbow-cake/commit/4352e06abf9b8c456100b7c59ffabf6a9d881717) and [here](https://gitlab.autsoft.hu/AutSoft/AndroidChapter/rainbow-cake/rainbow-cake/commit/ba0ebb6cae17bc99dd02b8ee2ef19bfbd0e2bd02).

### New `executeCancellable` method

Previously, any coroutines started by making `execute` calls in the `ViewModel` were only cancelled when the `ViewModel` was cleared. If you need to manage the `Job` representing coroutines manually instead, you can now do so with the `executeCancellable` method:

```kotlin
class MyViewModel : JobViewModel<MyViewState>(Default) {

    private var loadingJob: Job? = null

    fun loadData() {
        loadingJob?.cancel()
        
        loadingJob = executeCancellable {
            // do something
        }
    }

}
```

Note that you shouldn't ever return this `Job` to your Fragment, so ***do not do this***, as the `loadData` method here has an implicit return type of `Job`, instead of `Unit`:

```kotlin
class MyViewModel : JobViewModel<MyViewState>(Default) {

    fun loadData() = executeCancellable {
        // do something
    }

}
```

### Bug fixes and performance improvements (yes, really)

- Fragment backstack management fixes around the `replace` operation of the `Navigator` implementation.
- The `rootJob` in `JobViewModel` is now a `SupervisorJob` so that it's not cancelled altogether if a child coroutine fails (based on [this](https://proandroiddev.com/kotlin-coroutines-patterns-anti-patterns-f9d12984c68e) article's advice).
- The `coroutineContext` used by the `CoroutineScope` in `JobViewModel` is now only created once at instantiation.
- Channel observations are now explicitly cleared when a `ChannelViewModel` is cleared.

#### Version updates:

- Kotlin 1.3.11
- Coroutines 1.1.0
- Android Gradle Plugin 3.3.0
