package com.group9.view;


import com.group9.model.Clock;
import com.group9.model.Observer;
import com.group9.model.entities.Entity;
import com.group9.model.entities.EntityState;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class AnimationHandler implements Observer {
    private final Map<Enum, Map<EntityState, List<Image>>> animations = new HashMap<>();
    //private final Map<Enum, Integer> currentFrameIndex = new HashMap<>();
    private int currentFrame = 0;

    public void registerEntityAnimations(Enum entityType, EntityState state, String folderPath) {
        animations.putIfAbsent(entityType, new HashMap<>());
        Map<EntityState, List<Image>> stateAnimations = animations.get(entityType);

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
                stateAnimations.put(state, frames);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public Image getFrame(Enum entityType, EntityState state) {
        Map<EntityState, List<Image>> stateAnimations = animations.get(entityType);

        if (stateAnimations == null || !stateAnimations.containsKey(state)) {
            throw new IllegalArgumentException("No animations registered for " + entityType + " in state " + state);
        }

        List<Image> frames = stateAnimations.get(state);
        // Modulus hos many frames a specific animation has
        return frames.get(currentFrame % frames.size());
    }

    // Update current frame from the clock after set time
    @Override
    public void update() {
        currentFrame += 1;
    }
}
