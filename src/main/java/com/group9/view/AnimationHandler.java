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

public class AnimationHandler implements Observer {

    private final Map<String, Map<EntityState, List<Image>>> animations = new HashMap<>();
    private final Map<String, Integer> entityFrameTickrate;
    private int currentFrame = 0;
    private final int TICKS_PER_SECOND;

    public AnimationHandler(int TICKS_PER_SECOND) {
        this.entityFrameTickrate = new HashMap<>();
        this.TICKS_PER_SECOND = TICKS_PER_SECOND;
    }

    // Load images from a folder
    private List<Image> loadImagesFromFolder(String folderPath) {
        List<Image> frames = new ArrayList<>();
        try {
            File folder = new File(getClass().getResource(folderPath).toURI());
            File[] files = folder.listFiles((dir, name) -> name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".jpeg"));

            if (files != null && files.length > 0) {
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

    private void setUpStateAnimations(EntityType entityType, EntityState state, List<Image> frames) {
        animations.putIfAbsent(entityType.getName(), new HashMap<>());
        Map<EntityState, List<Image>> stateAnimations = animations.get(entityType.getName());
        stateAnimations.put(state, frames);
    }

    // Register animations for a CharacterType
    public void registerCharacterAnimations(CharacterType characterType, EntityState state, String folderPath) {
        List<Image> frames = loadImagesFromFolder(folderPath);
        setUpStateAnimations(characterType, state, frames);

        int ticksPerFrame = (int) (characterType.getAttackSpeed() * this.TICKS_PER_SECOND / frames.size());
        entityFrameTickrate.put(characterType.getName(), ticksPerFrame == 0 ? 10 : ticksPerFrame); // Default to 10 ticks per frame if attackspeed is instant
    }

    // Register animations for a ProjectileType
    public void registerProjectileAnimations(ProjectileType projectileType, EntityState state, String folderPath) {
        List<Image> frames = loadImagesFromFolder(folderPath);
        setUpStateAnimations(projectileType, state, frames);

        int ticksPerFrame = this.TICKS_PER_SECOND / frames.size();
        entityFrameTickrate.put(projectileType.getName(), ticksPerFrame);
    }

    // Get the current frame for an entity in a given state
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

    // Update current frame on each clock tick
    @Override
    public void update() {
        currentFrame++;
    }
}
