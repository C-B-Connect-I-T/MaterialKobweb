# MaterialKobweb

MaterialKobweb is a [Kobweb](https://github.com/varabyte/kobweb) library that brings a **Material Design 3** look
and feel — colors, components, and theming — to Kobweb/Compose HTML projects. It also bundles a few lightweight
**core utilities** (`Logger`, `ViewModel`, `ViewModelStore`) that don't belong to Material Design specifically, but
are small enough that it wasn't worth splitting them into their own library yet.

Think of this repo as a starting point/basis for building Kobweb apps: pull in the theme, use the `Ds*` components,
and get the core utilities for free.

## What's in here

```
MaterialKobweb/
├── materialKobweb/   # The library itself (published artifact)
└── sample/           # A runnable Kobweb app that demonstrates every component/feature
```

- **`materialKobweb`** is a Kobweb *library* module (uses the `com.varabyte.kobweb.library` Gradle plugin). This is
  the module that gets published and consumed by other projects.
- **`sample`** is a Kobweb *application* module used as a living component gallery/playground. It is the fastest way
  to see how a component or theme setting is meant to be used — check it before asking "how do I use X?".

### Package layout inside `materialKobweb`

All source lives under `com.materialkobweb`:

| Package | Purpose |
|---|---|
| `theme/` | Material Design 3 color scheme tokens (`ColorScheme`, light/dark palette tokens). |
| `styles/` | CSS variables and Silk style definitions that expose the color scheme to the DOM. |
| `components/widgets/` | Material-styled widgets: buttons, button links, cards, inputs, icon buttons, FABs, spinners, etc. Prefixed with `Ds` (Design System). |
| `components/sections/` | Larger composite sections (e.g. `NavHeader`). |
| `components/toast/` | Toast/snackbar notification system (`ToastManager`, `ToastContainer`, `ToastData`). |
| `components/svg/` | Inline SVG icon composables. |
| `constants/` | Shared constants, attribute names, class names. |
| `extensions/` | Kotlin extension functions on Kobweb/Compose/CSS types (`Modifier`, `Color`, sizes, `Document`). |
| `utils/` | The "core" pieces that aren't Material-specific: `Logger`, `ViewModel`, `ViewModelStore`. |
| `MaterialTheme.kt` | Entry point that wires a light/dark `ColorScheme` into Silk's theme + CSS variables. |

## Using the library in a Kobweb project

MaterialKobweb is published to [JitPack](https://jitpack.io/#C-B-Connect-I-T/MaterialKobweb). To consume it:

```kotlin
// settings.gradle.kts or build.gradle.kts repositories block
repositories {
    maven(url = "https://jitpack.io")
}

// build.gradle.kts
dependencies {
    implementation("com.github.C-B-Connect-I-T:MaterialKobweb:<version>")
}
```

### Wiring up the theme

Call `MaterialTheme.setSchemes(...)` inside an `@InitSilk` block (see `sample/.../MyApp.kt` for a full example),
passing your own `lightColorScheme()` / `darkColorScheme()` if you want to override the default Material tokens:

```kotlin
@InitSilk
fun initSilk(ctx: InitSilkContext) {
    MaterialTheme.setSchemes(lightScheme = MyLightColorScheme, darkScheme = MyDarkColorScheme)
}
```

Once wired up, use `ColorMode.current.toColorScheme` to read the active `ColorScheme` (primary, surface, error, etc.)
from any composable, and use the `Ds*`-prefixed widgets (`DsButton`, `DsCard`, `DsSelect`, `DsFloatingActionButton`,
`DsSpinner`, toasts via `ToastManager`, ...) instead of raw Silk/Compose HTML equivalents to automatically get the
Material look and feel.

### Two ways to read colors — which one to use

Every Material color is available through two complementary mechanisms, both driven by the same `ColorScheme` you
pass to `setSchemes(...)`:

- **`MaterialColorVars` (CSS variables) — use this in your own app code.** These are plain `CSSColorValue`s you can
  pass straight into any Kobweb modifier, no `ColorMode`/`toColorScheme` knowledge required, and they update via the
  CSS cascade (no recomposition, no flash of the wrong color):
  ```kotlin
  Modifier.backgroundColor(MaterialColorVars.SurfaceContainer.value())
  Modifier.color(MaterialColorVars.Primary.value())
  ```
- **`ColorMode.current.toColorScheme` (Kotlin object) — use this only when you need to compute a color**, e.g.
  `.lightened()`, `.darkened()`, `.toRgb().copyf(alpha = ...)` for hover/focus glows or shadows. A CSS `var(...)`
  reference is opaque in Kotlin and can't be manipulated this way, so the library's own widgets fall back to this
  object internally whenever they need that kind of color math.

Both stay in sync automatically since they're derived from the same `ColorScheme`, so pick whichever fits — but
default to `MaterialColorVars` for plain styling.


## The "core" utilities

These live in `com.materialkobweb.utils` and are intentionally UI/Material-agnostic — useful in any Kobweb app even
if you don't want the Material theming:

- **`Logger`** — environment-aware logger (`Logger.init("development")`) that only prints in development/staging,
  silent in production.
- **`ViewModel`** — base class providing a lazily-created `CoroutineScope` that is cancelled on `dispose()`, similar
  in spirit to Android's `ViewModel`.
- **`ViewModelStore`** / **`rememberViewModel(...)`** — a global, LRU-bounded store that keeps `ViewModel` instances
  alive across Kobweb's client-side navigation (which normally recomposes the whole page), with an opt-out
  (`cached = false`) for screens like create/edit forms where you don't want caching.

If these utilities grow in scope or get reused across enough unrelated projects, they are candidates to be split out
into their own standalone "core" library later. For now they stay here for simplicity.

## Running the sample app

```bash
./gradlew :sample:kobwebStart
```

Then open the URL printed in the console. Stop the dev server with `./gradlew :sample:kobwebStop`.

## Publishing

The library is published via JitPack (see `jitpack.yml`), which runs:

```bash
./gradlew :materialKobweb:clean :materialKobweb:publishToMavenLocal
```

To test a local publish yourself, run the same command and check `~/.m2` for the resulting artifact.

## Contributing / working in this repo

- `materialKobweb` is the library — most feature work happens here.
- Add or update a page in `sample` when you add/change a component, so it stays a living, up-to-date example.
- Run `./gradlew :materialKobweb:detekt` for static analysis, and `./gradlew :materialKobweb:jsTest` for tests.
- Issues are tracked in GitHub Issues for this repo; see `docs/agents/issue-tracker.md` and `AGENTS.md` for the
  conventions agents should follow when working in this repository.
