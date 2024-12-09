package com.group9.view;

import com.group9.model.entities.Entity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class AnimationHandler{
    private int currentFrame = 0;
    private Entity entity;
    private List<String> animationFrames;

    AnimationHandler(Entity entity, String imageFolder){
        this.entity = entity;
        File folder = new File(imageFolder);
        if (folder.exists()) {
            this.animationFrames = iterateFolder(folder);
        } else {
            System.out.println("The image folder: " + folder + " does not exist.");
        }
    }

    private List<String> iterateFolder(File folder) {
        List<String> frames = new ArrayList<String>();
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        frames.add(file.toString());
                    }
                }
            }
        }
        return frames;
    }

    public String getFrame() {
        return animationFrames.get(currentFrame);
    }
}
