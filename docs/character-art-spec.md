# Rem pixel-art character specification

## Character direction
Create one consistent, original pixel-art rendition of Rem, recognizable by her short blue hair, maid-inspired outfit and small white hair ornament. Keep the design cute, readable at tiny on-screen sizes, and suitable for an AMOLED interface. Do not extract sprites from anime footage, game files or copyrighted model assets.

## Canvas and export
- Transparent PNG sprite sheets.
- Design on a logical 32×48 pixel character canvas; test at 2×, 3× and 4× nearest-neighbor scaling.
- Use a restrained palette with clear outlines and limited highlights.
- Keep feet baseline and character pivot consistent across animation frames.
- Export a palette/reference sheet alongside animation sheets.
- No background baked into the sprite.

## Required animation sets
- `idle`: breathing loop, 4–6 frames
- `blink`: brief eye-close overlay or 2–3 frame sequence
- `walk`: 6–8 frames
- `run`: 6–8 frames
- `climb`: 6–8 frames, compatible with clock-digit edges
- `sit`: 2–4 subtle frames
- `sleep`: 4–6 frames with gentle breathing
- `peek`: 3–5 frames
- `expressions`: happy, sleepy, surprised and wink

## Runtime rules
Use sprite-sheet frame regions and nearest-neighbor sampling to preserve crisp pixels. Keep a single shared character identity and palette across all sets. Animation rate and frequency must be reduced under battery-saving and thermal-throttling states. Pause animation when the app is not visible.

## Acceptance checks
- Transparent edges are clean.
- No frame changes hair, outfit, proportions or palette unexpectedly.
- No jitter when walking, climbing or sitting.
- Character remains legible at the intended small display size.
- Idle motion is subtle and does not distract from time, calendar or music.