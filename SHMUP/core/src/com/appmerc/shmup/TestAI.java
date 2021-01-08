package com.appmerc.shmup;

import com.badlogic.gdx.Gdx;

public class TestAI
{
    public Shooter basicShooter;
    public boolean active;
    Shooter targetShooter;
    public Stats stats;
    float cooldown;
    float cdTimer;

    public void init()
    {
        basicShooter = new Shooter();
        basicShooter.init();
        stats = new Stats();
        stats.init();
        targetShooter = null;
        cooldown = 1.0f;
        cdTimer = cooldown;
        active = true;
    }

    protected void CheckHealth(EnemyManager em)
    {
        // If health < 0, this enemy is destroyed
        if (stats.health <= 0)
        {
            active = false;
            Statistics.score += 50;
            --em.curEnemyCount;
        }
    }

    public void update(float dt, EnemyManager em)
    {
        // If inactive, do not update anything
		if (!active)
            return;

        CheckHealth(em);

        // If there is a set target, fire at it
        if (targetShooter != null)
        {
            cdTimer -= dt;

            if(cdTimer <= 0)
            {
                basicShooter.shootBullet(basicShooter.getDirection(targetShooter));
                cdTimer = cooldown;
            }
        }
		
        basicShooter.update(dt);
		
        for (int i = 0; i < 128; i++)
        {
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
