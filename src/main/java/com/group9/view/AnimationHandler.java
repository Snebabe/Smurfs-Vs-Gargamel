package com.group9.view;

import com.group9.model.Observer;
import com.group9.model.entities.EntityState;
import com.group9.model.entities.EntityType;
import com.group9.model.entities.characters.CharacterType;
import com.group9.model.entities.projectiles.ProjectileType;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

/**
 * The AnimationHandler class is responsible for managing and retrieving animation frames
 * for various entities in the game. It implements the Observer interface to
 * update the current frame on each clock tick.
 */
public class AnimationHandler implements Observer {

    /** A map storing animations for each entity type and state. */
    private final Map<String, Map<EntityState, List<Image>>> animations = new HashMap<>();

    /** A map storing the frame tick rate that should pass before switching frame, for each entity type. */
    private final Map<String, Integer> entityFrameTickrate;
    private int currentFrame = 0;
    private final int TICKS_PER_SECOND;

    public AnimationHandler(int TICKS_PER_SECOND) {
        entityFrameTickrate = new HashMap<>();
        this.TICKS_PER_SECOND = TICKS_PER_SECOND;
    }

    /**
     * Retrieves all images from a specified folder.
     *
     * @param folderPath the path to the folder containing the images
     * @return a list of images loaded from the folder
     */
    private List<Image> loadImagesFromFolder(String folderPath) {
        List<Image> frames = new ArrayList<>();
        try {
            File folder = new File(getClass().getResource(folderPath).toURI());
            File[] files = folder.listFiles((dir, name) -> name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".jpeg"));

            if (files != null) {
                for (File file : files) {
                    frames.add(ImageIO.read(file));
                }
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        if (frames.isEmpty()) {
            throw new IllegalArgumentException("No images found");
        }
        return frames;
    }

    /**
     * Sets up animations for a specific entity type and state.
     *
     * @param entityType the type of the entity
     * @param state the state of the entity
     * @param frames the list of frames for the animation
     */
    private void setUpStateAnimations(EntityType entityType, EntityState state, List<Image> frames) {
        animations.putIfAbsent(entityType.getName(), new HashMap<>());
        Map<EntityState, List<Image>> stateAnimations = animations.get(entityType.getName());
        stateAnimations.put(state, frames);
    }

    /**
     * Registers animations for a character type.
     *
     * @param characterType the type of the character
     * @param state the state of the character
     * @param folderPath the path to the folder containing the animation frames
     */
    public void registerAnimations(CharacterType characterType, EntityState state, String folderPath) {
        List<Image> frames = loadImagesFromFolder(folderPath);
        setUpStateAnimations(characterType, state, frames);

        int ticksPerFrame = (int) (characterType.getAttackDelay() * TICKS_PER_SECOND / frames.size());
        entityFrameTickrate.put(characterType.getName(), ticksPerFrame == 0 ? 10 : ticksPerFrame); // Default to 10 ticks per frame if attackspeed is instant
    }

    /**
     * Registers animations for a projectile type.
     *
     * @param projectileType the type of the projectile
     * @param state the state of the projectile
     * @param folderPath the path to the folder containing the animation frames
     */
    public void registerAnimations(ProjectileType projectileType, EntityState state, String folderPath) {
        List<Image> frames = loadImagesFromFolder(folderPath);
        setUpStateAnimations(projectileType, state, frames);

        int ticksPerFrame = TICKS_PER_SECOND / frames.size();
        entityFrameTickrate.put(projectileType.getName(), ticksPerFrame);
    }

    /**
     * Gets the current frame for an entity in a given state.
     *
     * @param entityName the name of the entity
     * @param state the state of the entity
     * @return the current frame image
     */
    public Image getFrame(String entityName, EntityState state) {
        Map<EntityState, List<Image>> stateAnimations = animations.get(entityName);

        if (stateAnimations == null || !stateAnimations.containsKey(state)) {
            throw new IllegalArgumentException("No animations registered for " + entityName + " in state " + state);
        }

        List<Image> frames = stateAnimations.get(state);
        int frameCount = frames.size();
        int tickRate = entityFrameTickrate.get(entityName);
        int frameIndex = (currentFrame / tickRate) % frameCount;

        return frames.get(frameIndex);
    }

    @Override
    public void update() {
        currentFrame++;
    }
}
