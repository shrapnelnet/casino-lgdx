# casino-gdx

## Docs

Accessible and auto-updating [here](https://casino-doc.shrapnelnet.co.uk)

## Structure

`assets/`

Project assets: textures, skins, audio etc.

`lwjgl3/`

Desktop platform specific implementation details. Mostly boilerplate for compatibility with older systems, or older Java versions.

`core/`

Platform independent logic, rendering code, event loop

`core/src/main/java/com/shr4pnel/casino`

The main package, where the bulk of the code is located.

`com.shr4pnel.casino.audio`

Helpers for playing sounds on the fly

Can also hold a reference to the sound, leaving options open for later muting, cancellation or later playback of the sound.

`com.shr4pnel.casino.base`

Base classes, extended by different card games to make global changes easier later on

`com.shr4pnel.casino.blackjack`

Base class extensions for blackjack, as well as a class for game logic.

`com.shr4pnel.casino.builders`

Builder classes, to abstract away creation of classes with complex constructors.

`com.shr4pnel.casino.console`

Classes relating to the GUI console, which can be used to modify internal game state for debugging, as well as quickly fetching certain important states without needing to debug.

`com.shr4pnel.casino.scene`

Classes which handle state relating to the current active scene

`com.shr4pnel.casino.style`

Classes which handle initialisation and rendering of Scene2D.ui skins

`com.shr4pnel.casino.util`

Classes that don't fit anywhere else, temporary subpackage unless it gets larger

`com.shr4pnel.casino.views`

Classes that return the Scene2d.ui markup for views that don't render directly through openGL i.e. menu, intro

## Usage

The gradle wrapper can be used to build this project without having Java or a build system installed.

`./gradlew run`
