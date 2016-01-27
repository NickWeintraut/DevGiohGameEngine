# DevGiohGameEngine
A game rules engine for card-based games written in Java

README/DOCUMENTATION A WORK IN PROGRESS

Table of Contents:
  1. Summary
  2. Engine Features/Design choices

Tasks:
  1. Modify Engine class to support for running games in tandem (the rest of the design supports it!!!)
  2. Parallelize operations, use multi-threading to improve performance of simultaneous processes
  3. Restructure input pipeline (currently Engine-driven, asks view for input and waits) to Controller-driven (use engine for rules calculation by supplying input, Engine works until more input is needed)
  4. Fill out utility methods in base-classes to allow full access to info.
  5. Restructure Effects system as full-on component-based GameObject definition system (in the same vein as Unity 3d)
  6. Create alternate Engine assembly to support RealTime game rules definition.
  7. Add external components for Game rendering/representation creation (wayyyyyyy in the future goal).

Summary:	The DevGioh game engine is a rules engine designed for implementation of card-based games in Java. The rules engine abstracts games to units of GameObjects, GameValues, and Phases. A game in the engine consists is comprised of a number of components. Classes have access to critical game-level info through a static Engine class. Some default components include game state recording, turn management, player management, and input management. The rules engine is almost entirely separated from any view or view control classes according to MVC.

ENGINE FEATURES/DESIGN CHOICES:

State/Strategy Pattern - Each Turn iterates through a polymorphic collection of Phase-subclass instances, which define sequence of gameplay and can be added/removed from turns at runtime.

Support for N number of players.

Support for multiple games running in tandem (bug-fixing needed currently)

MVC - Designed for game rules implementation to be separate from game input devices and game representation (visual, audio, etc.)

Chained observer pattern for updating GameObjects/Components

Strategy Pattern - GameObject behavior abstracted to Effect components

Polymorphic component system used for Effect and Condition implementation

Regulated queueing and iteration of activatables (Ex. Effects trigger in sequence with enum-based priority)

(Pseudo) Singleton Pattern - Static methods in Engine class allow for global access of game-level references.

Decorator Pattern - Decorated Effects allow for activation/deactivation of nested effects (E.g. turn off effect triggering)

Observer Pattern - GameObjects(including Game) update all observers upon being updated. GameObjects, by default, add children to list of observers.

Game state is generated recursively through the GameObject parent-child tree, and comprised of GameValues and CardZone state instances.

Game progression is handled by a TurnManager, which uses a PhaseSequence to iterate through the Game’s Turns and joining Phases (Turn is a subclass of Phase).

Changes in Game State are calculated from recorded Game States, both enabling recording of StateChanges over varied time lengths (e.g. immediate changes, changes over the course of phases, turns, etc.) AND allowing changes in state to be recorded without registry in the system by the causes of the changes (“care-free” automated change recording)

Modular components could be re-used to create different, specialized versions of the engine (e.g. RealTime rules engine).
Abstract methods used for instantiating game components allow implementations of the engine to define most, if not all implementation-specific rules behavior using inheritance.

Supports using generic object-based input storage to send and receive input requests and responses.

