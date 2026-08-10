# MonoPolySelect

A tiny client-side helper mod for [Mindustry](https://github.com/Anuken/Mindustry): select all of your mono or poly units with a single hotkey.

## Features

- Two configurable hotkeys, registered in the in-game Controls menu:
  - **Select All Mono**
  - **Select All Poly**
- Pressing a hotkey automatically enters Command Mode, selects every commandable mono/poly on your team, and refreshes the command HUD — issue orders immediately with right-click.
- Keys are unbound by default; assign your own in `Settings → Controls → Unit Command`.
- Safe on dedicated servers (no effect there).
- Works on vanilla Mindustry and MindustryX, desktop and Android.

## Installation

1. Download `MonoPolySelect.jar` from the [latest release](https://github.com/DeterMination-Wind/MonoPolySelect/releases/latest).
2. Put it into your game's `mods` folder.
3. Launch the game, open `Settings → Controls`, find **Select All Mono** / **Select All Poly** under *Unit Command*, and bind your keys.

## Building

```bash
./gradlew deploy
```

Merged desktop + Android artifacts are written to `dist/`.

Note: for dev builds (project version `0.0.0`), the merged jar is written to `构建/MonoPolySelect/` (as `MonoPolySelect-dev.jar`) instead of `dist/`.
