# Rem StandBy v1.0

An Android StandBy experience inspired by the clean, glanceable layout of iPhone StandBy, featuring one tiny animated pixel-art Rem companion.

## Project status

Early project scaffold. Confirmed design and behavior are documented in `docs/`. The Android application and production-ready character sprite animations are not implemented yet.

## Confirmed experience

- AMOLED-friendly dark landscape StandBy screen
- Large clock, date, calendar and music controls
- One consistent tiny Rem companion who can idle, blink, walk, climb, sit, peek and sleep
- Swipe navigation between StandBy layouts
- Manual launch and charging-aware activation, subject to Android/Samsung background-launch rules
- Smart portrait/landscape adaptation
- Home-screen widgets where Android supports them; lock-screen behavior depends on device/OS support
- Adaptive brightness and automatic night mode
- Below 20% battery: dim and reduce animation
- Below 10% battery: extra power saving
- Adaptive animation quality based on Android thermal status
- At critical thermal status: stop StandBy and its animation work; never override system thermal protections

## Planned stack

Native Android, Kotlin and Jetpack Compose. Use Android battery, charging, orientation and thermal APIs with lifecycle-aware work.

## Character assets

The Rem sprite will be an original, small pixel-art rendition with transparent backgrounds and reusable animation frames. See `docs/character-art-spec.md`.

## Development

- [Design specification](docs/design.md)
- [Character art specification](docs/character-art-spec.md)
- [Technical plan](docs/technical-plan.md)

No APK has been built or device-tested yet.

## Android app foundation

The first native Android implementation is now in the repository:

- Kotlin + Jetpack Compose app module (app/)
- Four swipe pages: main clock/calendar/music layout, full clock, Rem placeholder scene, and widget dashboard
- Immersive dark StandBy presentation and screen-on behavior while the app is open
- GitHub Actions workflow to build a debug APK on pushes and pull requests

### Build locally

Open this repository in Android Studio, allow Gradle sync, then run the app configuration on an Android device or emulator. The project uses JDK 17 and Android SDK 36.

### Current limitations

This is an early UI foundation, not a finished StandBy app. Music controls are visual placeholders and do not control a media session yet. Battery/weather data, charging-aware activation, thermal throttling, settings, home-screen widgets, and the final animated Rem sprite sheet are not implemented. The GitHub Actions workflow is configured, but a successful build has not yet been verified.
