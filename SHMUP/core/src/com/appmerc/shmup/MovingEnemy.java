package com.appmerc.shmup;

public class MovingEnemy extends TestAI
{
    private float movementSpeed = 300.f;

    MovingEnemy() {}

    public void update(float dt, EnemyManager em)
    {
        // If inactive, do not update anything
        if (!active)
            return;

        CheckHealth(em);

        // Simply fly downwards
        basicShooter.pos.y -= dt * movementSpeed;

        // Once this enemy hits the bottom, it will be set to inactive,
        // and EnemyManager's enemy count is decremented
        if (basicShooter.pos.y <= 0)
        {
            active = false;
            --em.curEnemyCount;
        }
    }
}
