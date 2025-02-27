# PreferenceState
[![](https://jitpack.io/v/mohitsoni48/DataStoreState.svg)](https://jitpack.io/#mohitsoni48/PreferenceState)

## How to Install

Add it in your root build.gradle at the end of repositories:

```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

Add the dependency:

```
dependencies {
    implementation 'com.github.mohitsoni48:PreferenceState:<Version>'
}
```

## How to Use

Initialize DataStoreState in your application class or launching activity like this:

```
PreferenceState.initialize(context, "preferences_name")
```

Use the provided `remember` functions to store and retrieve values from DataStore directly as state in Jetpack Compose:

```
var username by rememberStringPreferenceState("username", "Guest")
```

Example Usage:

```
@Composable
fun Greeting() {
    var randomString by rememberStringPreferenceState("randomString", "Hello World")

    Text(
        text = randomString,
        modifier = Modifier.clickable {
            randomString = UUID.randomUUID().toString()
        }
    )
}
```

### Supported Data Types

You can store and observe the following types using `remember<Type>PreferenceState`:
- `rememberStringPreferenceState(key, defaultValue)`
- `rememberBooleanPreferenceState(key, defaultValue)`
- `rememberIntPreferenceState(key, defaultValue)`
- `rememberLongPreferenceState(key, defaultValue)`
- `rememberFloatPreferenceState(key, defaultValue)`
- `rememberDoublePreferenceState(key, defaultValue)`
- `rememberStringSetPreferenceState(key, defaultValue)`