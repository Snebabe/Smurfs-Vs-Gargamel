package com.group9.view;


import com.group9.model.Observer;
import com.group9.model.entities.EntityState;
import com.group9.model.entities.characters.CharacterType;
import com.group9.model.entities.projectiles.ProjectileType;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class AnimationHandler implements Observer {

    // The string inside the "Maps" are the name of the EntityTypes
    private final Map<String, Map<EntityState, List<Image>>> animations = new HashMap<>();
    private final Map<String, Integer> entityFrameTickrate;
    //private final Map<Enum, Integer> currentFrameIndex = new HashMap<>();
    private int currentFrame = 0;
    private final int TICKS_PER_SECOND;


    public AnimationHandler(int TICKS_PER_SECOND) {
        this.entityFrameTickrate = new HashMap<>();
        this.TICKS_PER_SECOND = TICKS_PER_SECOND;
    }

    public void registerEntityAnimations(CharacterType characterType, EntityState state, String folderPath) {
        animations.putIfAbsent(characterType.getName(), new HashMap<>());
        Map<EntityState, List<Image>> stateAnimations = animations.get(characterType.getName());

        List<Image> frames = new ArrayList<>();
        try {
            // Get the folder inside resources
            File folder = new File(getClass().getResource(folderPath).toURI());
            File[] files = folder.listFiles((dir, name) -> name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".jpeg"));

            if (files != null && files.length > 0) {
                for (File file : files) {
                    try {
                        // Load the image and add it to the list
                        frames.add(ImageIO.read(file));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                // Add frames to stateAnimations
                stateAnimations.put(state, frames);

                // Set how many ticks per frame should be executed
                int ticksPerFrame = (int) (characterType.getAttackSpeed() * this.TICKS_PER_SECOND / files.length);
                if (ticksPerFrame == 0) { // TO avoid division by 0
                    ticksPerFrame = this.TICKS_PER_SECOND;
                }

                entityFrameTickrate.put(characterType.getName(), ticksPerFrame);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registerProjectileAnimations(ProjectileType projectileType, EntityState state, String folderPath) {
        animations.putIfAbsent(projectileType.getName(), new HashMap<>());
        Map<EntityState, List<Image>> stateAnimations = animations.get(projectileType.getName());

        List<Image> frames = new ArrayList<>();
        try {
            // Get the folder inside resources
            File folder = new File(getClass().getResource(folderPath).toURI());
            File[] files = folder.listFiles((dir, name) -> name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".jpeg"));

            if (files != null && files.length > 0) {
                for (File file : files) {
                    try {
                        // Load the image and add it to the list
                        frames.add(ImageIO.read(file));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                // Add frames to stateAnimations
                stateAnimations.put(state, frames);

                // Animation cycle for projectiles take one second
                int ticksPerFrame = (this.TICKS_PER_SECOND / files.length);
                entityFrameTickrate.put(projectileType.getName(), ticksPerFrame);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public Image getFrame(String entityName, EntityState state) {
        Map<EntityState, List<Image>> stateAnimations = animations.get(entityName);

        if (stateAnimations == null || !stateAnimations.containsKey(state)) {
            throw new IllegalArgumentException("No animations registered for " + entityName + " in state " + state);
        }

        List<Image> frames = stateAnimations.get(state);

        int frameCount = frames.size();
        int tickRate = entityFrameTickrate.get(entityName); // Get the tick rate for the entity
        int frameIndex = (currentFrame / tickRate) % frameCount; // Use division for tick rate and modulo for looping

        return frames.get(frameIndex);
    }

    // Update current frame from the clock after set time
    @Override
    public void update() {
        currentFrame += 1;
    }
}
