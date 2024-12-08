#set page("a4")

#align(center, text(17pt)[
	*TDA367 Requirements Analysis Document*
])

#align(center, text(15pt)[
    *Group 9*
])

Smurfs vs. Gargamel is a strategic tower defense game inspired by Plants vs. Zombies, where players must strategically defend their smurf villages against waves of Gargamel's forces. Players place different smurf units, each with its own unique ability, to attack the advancing gargamels. The game combines resource management, strategic planning, and good action to create an engaging and challenging gameplay experience.

Here follows some of our epic user stories: 

#align(center)[
    _As a Player, I want to be able to choose where I place Smurfs on the battlefield, because I need to strategically defend my base._
]

#align(center)[
    _As a Player, I want an intuitive resource system used to deploy Smurfs, so I can manage resources effectively and prioritize which smurfs to buy._
]

#align(center)[
    _As a Player, I want to enjoy an interactive UI with engaging graphics and sounds, so I feel immersed in the Smurf universe._
]

== Requirements Overview

To define our requirements, we first start by defining the scope. We want to highlight these four key features of our game. The game should include:
- A grid-based board divided into multiple lanes.
- Defenders with ranged and melee attacks.
- Waves of attackers with varying properties and behaviours.
- Resource management and rewards for defeating attackers and completing waves.

The game only interacts with a user as its business context: The table below showcases this relation:

#figure(
    caption: [Business Context],
    table(
        columns: (auto, auto, auto),
        [*Communication Partner*],
        [*Inputs*],
        [*Outputs*],

        [*Player (User)*],
        [
            - Mouse input for placing defenders.
            - Commands to start and reset the game.
            - Selection of defender types
        ],
        [
            - Visual feedback for the game state.
            - Resource updates.
            - Notifications for winning and losing.
        ]
    )
)

There are also some technical interfaces, as the game is designed as a desktop-based application. Here follows the technical context:

#figure(
    caption: [Technical Context],
    table(
        columns: (auto, auto, auto),
        [*Channel*],
        [*Inputs*],
        [*Outputs*],

        [*Input Devices*],
        [
            - Mouse and keyboard events for interaction.
        ],
        [
            -
        ],

        [*Display*],
        [
            -
        ],
        [
            Graphical updates for game visuals.
        ]
    )
)

#pagebreak()

Based on our current user feedback, we represent the following stakeholders:

#table(
  columns: 3,
  [*Role*],
  [*Contact*],
  [*Expectations*],

  [*Product Owner*],
  [#link("mailto:stefan@gargasmurf.com")],
  [ 
    - A high-level overview of the architecture.
  ],

  [*Development Team*],
  [#link("mailto:devs@gargasmurf.com")],
  [ 
    - Access to detailed class diagrams and data flows.
  ],

  [*End Users*],
  [#link("mailto:support@gargasmurf.com")],
  [ 
    - A fun, engaging game, running smooth without unexpected behaviour.
  ],
)

Now comes the main requirements needed by our game:

*Functional Requirements:*
#figure(
    caption: [Functional Requirements],
    table(
        columns: (auto, auto),
        [*Type of Requirement*],
        [*Requirements*],

        [*Game Setup*],
        [
            - The game must have a board consisting of multiple lanes.
            - Each lane must contain a number of grid cells.
            - Each lane must track attackers.
        ],

        [*Defender Management*],
        [
            - Defenders must be placeable on the grid at specific positions.
            - Each defender must have attributes for health, attack damage, range, and cost.
        ],

        [*Attacker Management*],
        [
            - Attackers must move forward in their respective lanes.  
            - Each attacker must have attributes for health, attack damage, speed, and a resource reward.
        ],

        [*Game Progression*],
        [
            - The game must support waves of attackers with increasing difficulty.
            - The game must end once an attacker reaches the end of a lane.
        ],

        [*Resource Management*],
        [
            - The game must track resources available to the player.
        ]
    )
)

*Non-Functional Requirements*
#figure(
    caption: [Non-Functional Requirements],
    table(
        columns: (auto, auto),
        [*Type of Requirement*],
        [*Requirements*],

        [*Performance*],
        [
            - The game must process updates at a consistant rate. 
        ],

        [*Scalability*],
        [
            - The game must support configurable lane and grid sizes.
        ],

        [*Maintainability*],
        [
            - The game must adhere to object-oriented design, with responsibilities devided among all classes.
        ],

    )
)

#pagebreak()

Below is the domain model for Smurfs vs. Gargamel:

#figure(
    caption: [Domain model for Smurfs vs. Gargamel],
    image("res/domain_model.png", width: 90%)
)

Here is a mockup of the game, together with an early rendering:

#figure(
    caption: [GUI mockup],
    image("res/mockup1.jpg", width: 60%)
)

#figure(
    caption: [Early rendering of the game],
    image("res/early_rendering.png", width: 80%)
)