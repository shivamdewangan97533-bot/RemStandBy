# Technical plan

## Target
Samsung Galaxy S23 first, with a native Android application. Planned stack: Kotlin, Jetpack Compose and Android platform APIs.

## Modules
- `standby-ui`: clock, date, calendar, music and swipe navigation
- `rem-sprite`: sprite-sheet renderer, animation state machine and touch reactions
- `activation`: manual launch, charging state and user-configurable exit behavior
- `orientation`: portrait/landscape responsive layouts
- `widgets`: supported Android home-screen widgets and app deep links
- `power-thermal`: battery thresholds, thermal callbacks and adaptive rendering
- `settings`: brightness, night mode, activation and animation preferences

## Power policy
- Below 20% battery: dim and lower animation frequency/quality.
- Below 10% battery: enable extra power-saving behavior.
- Read Android thermal status/headroom where available; do not invent universal Celsius thresholds.
- Warm/hot states progressively reduce frame rate, effects and animation work.
- Critical thermal state: close/stop the StandBy experience and release its work/resources.
- Respect Android and Samsung thermal/charging protections. Never bypass system safeguards.
- Avoid continuous sensor polling and unnecessary wake locks. Pause rendering when not visible.

## Platform limitations
Android restricts background activity launches. Charging detection does not guarantee an app can force itself into the foreground. Implement a compliant user-enabled path such as a notification/action or supported system integration. Lock-screen widgets and animated widget behavior vary by Android and Samsung software version.

## Build milestones
1. Establish Gradle Android project and compile a blank app.
2. Implement main StandBy layout and swipe navigation.
3. Add pixel sprite renderer and animation state machine.
4. Add charging/orientation/battery/thermal handling.
5. Add supported home-screen widgets and deep links.
6. Test long charging sessions, burn-in mitigation, thermal behavior and battery use on Galaxy S23.

## Current status
Documentation scaffold only. No APK, production sprite sheets or hardware tests have been completed.