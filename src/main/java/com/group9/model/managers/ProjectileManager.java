package com.group9.model.managers;

import com.group9.model.Observer;
import com.group9.model.board.Board;
import com.group9.model.board.Lane;
import com.group9.model.entities.projectiles.Projectile;

import java.util.ArrayList;
import java.util.List;

public class ProjectileManager {
    private Board board;
    private static ProjectileManager instance;
    //private List<Projectile> projectiles = new ArrayList<>();

    private ProjectileManager(Board board) {
        this.board = board;
    }

    public static void initialize(Board board) {
        if (instance == null) {
            instance = new ProjectileManager(board);
        }
    }

    public static ProjectileManager getInstance() {
        if(instance == null) {
            throw new IllegalStateException("ProjectileManager has not been initialized.");
        }
        return instance;
    }

    public void addProjectile(Projectile projectile, Lane lane) {
        lane.addProjectile(projectile);
        board.addMovable(projectile, lane);

    }


    /*public void updateProjectiles() {
        /*for (int i = 0; i < projectiles.size(); i++) {
            Projectile projectile = projectiles.get(i);
            projectile.update();

            if (projectile.isDestroyed()) {
                projectiles.remove(i--); // Remove destroyed projectiles
            }
        }

        for(int currentLane = 0; currentLane < board.getLaneAmount(); currentLane++) {
            List<Projectile> projectiles = board.getLanes().get(currentLane).getProjectiles();
            for (int currentProjectileIndex = 0; currentProjectileIndex < projectiles.size(); currentProjectileIndex++) {
                Projectile projectile = projectiles.get(currentProjectileIndex);
                projectile.update();

                if (projectile.isDestroyed()) {
                    projectiles.remove(currentProjectileIndex--); // Remove destroyed projectiles
                }
            }
        }
    }*/


}