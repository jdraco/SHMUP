package com.appmerc.shmup;

import com.badlogic.gdx.Gdx;

public class TestAI {
    public Shooter basicShooter;
    public boolean active;
    Shooter targetShooter;
    public Stats stats;
    float cooldown;
    float cdTimer;

    public void init()
    {
        active = true;
        basicShooter = new Shooter();
        basicShooter.init();
        stats = new Stats();
        stats.init();
        targetShooter = null;
        cooldown = 1.0f;
        cdTimer = cooldown;
    }

    public void update(float dt)
    {
        if(stats.health <= 0)
            active = false;

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
        for (int i = 0; i < 128; i++) {
            if(basicShooter.bullets[i].active)
            {
                if(basicShooter.bullets[i].checkAABB(Player.shooter.pos, Player.shooter.size))
                {
                    Player.stats.health -= stats.damage;
                    basicShooter.bullets[i].active = false;
                }
            }
        }
    }

    public void setTarget(Shooter toShoot)
    {
        targetShooter = toShoot;
    }
}
