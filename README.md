# RainbowCake

[![Build Status](https://app.bitrise.io/app/63be494637ff98da/status.svg?token=sjWBvcVPa3xZGA_I6Cfolw&branch=dev)](https://app.bitrise.io/app/63be494637ff98da)

<img src="/docs/icon.png" alt="RainbowCake logo" width="200" height="200" />

[**RainbowCake**](https://rainbowcake.dev/) is an Android architecture framework, providing tools and guidance for building modern Android applications. It builds on top of Jetpack, both in terms of code and ideas.

Some of the main goals of this architecture:

- Give guidance on all aspects of the application, covering not just the View architecture,
- Clearly separate concerns between different layers and components,
- Always keep views in a safe and consistent state with ViewModels,
- Handle configuration changes (and even process death) gracefully,
- Make offloading work to background threads trivial.


While RainbowCake is heavily opinionated, it also encourages you to deviate from it as needed. Feel free to pick and choose the ideas and library artifacts provided according to your own application’s needs!

For more information, see the official documentation on [**rainbowcake.dev**](https://rainbowcake.dev/).

### Setup

RainbowCake is available from MavenCentral.

```groovy
repositories {
    mavenCentral()
}
```

It ships in several artifacts - feel free to pick and choose from them (for more info, see [Dependencies](https://rainbowcake.dev/getting-started/dependencies/)):

```groovy
dependencies {
    implementation "co.zsmb:rainbow-cake-core:1.3.0" // Core library (required)
    implementation "co.zsmb:rainbow-cake-dagger:1.3.0" // Dagger 2 support
    implementation "co.zsmb:rainbow-cake-koin:1.3.0" // Koin support
    implementation "co.zsmb:rainbow-cake-navigation:1.3.0" // Navigation features
    implementation "co.zsmb:rainbow-cake-timber:1.3.0" // Internal logging through Timber
    testImplementation "co.zsmb:rainbow-cake-test:1.3.0" // Testing utilities
}
```

# License

    Copyright 2020 Marton Braun

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
