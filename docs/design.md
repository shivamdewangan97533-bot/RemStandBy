# Visual design — Rem StandBy v1.0

## Main screen
- Black/near-black AMOLED canvas, primarily landscape.
- Apple StandBy-inspired composition, but with Rem as the signature companion.
- Large high-contrast clock and date.
- Calendar panel and music player remain visible together.
- Restrained blue and violet accents; soft glows, thin separators and rounded panels.
- Exactly one Rem companion. Do not duplicate her across the same screen.

## Rem behavior
Rem is a tiny pixel-art companion integrated into the layout, not a large character illustration. She may perch on or climb clock digits, walk along panel edges, peek from behind widgets, sit, sleep, and react to taps. Use subtle idle motion (breathing and occasional blinking) so the display remains calm.

## Navigation
Swipe between:
1. Main clock + calendar + music
2. Full clock / night view
3. Rem companion scene
4. Widget dashboard

## Activation and orientation
Support manual launch and charging-aware activation. Use a user-approved, Android-compliant launch path; do not promise unrestricted background activity launches. Adapt to portrait or landscape orientation and exit StandBy when unplugged if the user has enabled that behavior.

## Widget scope
Provide clock, date, battery, music, calendar, weather and Rem companion surfaces where Android APIs permit. Tapping a widget opens the full app in the current orientation. Standard Android widgets and lock-screen surfaces have platform animation and availability limits.

## Confirmed preferences
- Adaptive brightness
- Automatic night mode
- Adaptive animation quality
- Below 20% battery: dim and reduce animation
- Below 10% battery: extra power saving
- Critical thermal state: stop StandBy and its animation/background work
- A single consistent Rem companion throughout