package com.appmerc.shmup;

import com.badlogic.gdx.Gdx;

public class TestAI {
    Shooter basicShooter;
    Shooter targetShooter;
    float cooldown;
    float cdTimer;

    public void init()
    {
        basicShooter = new Shooter();
        basicShooter.init();
        targetShooter = null;
        cooldown = 1.0f;
        cdTimer = cooldown;
    }

    public void update(float dt)
    {
        if(targetShooter != null)
        {
            cdTimer -= dt;
            if(cdTimer <= 0)
            {
                basicShooter.shootBullet(basicShooter.getDirection(targetShooter));
                cdTimer = cooldown;
            }
        }
        basicShooter.update(dt);
    }

    public void setTarget(Shooter toShoot)
    {
        targetShooter = toShoot;
    }
}
