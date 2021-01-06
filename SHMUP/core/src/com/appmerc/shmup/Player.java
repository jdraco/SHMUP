package com.appmerc.shmup;

import com.badlogic.gdx.math.Vector2;

public class Player {
    Shooter shooter;
    Vector2 dir;
    float speed;
    float cooldown;
    float cdTimer;

    public void init()
    {
        dir = new Vector2(-1,0);
        shooter = new Shooter();
        shooter.init();
        speed = 1000.0f;
        cooldown = 1.0f;
        cdTimer = cooldown;
    }
    public void update(float dt){
        if(SHMUP.controller.isRightPressed())
        {
            shooter.pos.x+= speed * dt;
        }
        else if (SHMUP.controller.isLeftPressed())
        {
            shooter.pos.x-= speed * dt;
        }
        else if (SHMUP.controller.isUpPressed())
        {
            shooter.pos.y+= speed * dt;
        }
        else if (SHMUP.controller.isDownPressed())
        {
            shooter.pos.y-= speed * dt;
        }
        else if (SHMUP.controller.isShootPressed())
        {

            if(cdTimer <= 0)
            {
                shooter.shootBullet(dir);
                cdTimer = cooldown;
            }
        }
        cdTimer -= dt;
        shooter.update(dt);
    }

}
