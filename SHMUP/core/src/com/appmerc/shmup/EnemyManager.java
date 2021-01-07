package com.appmerc.shmup;

import com.badlogic.gdx.Gdx;

import java.util.Random;

public class EnemyManager
{
    private static float spawnTimer = 0.f;
    private final float nextEnemySpawnTime = 2.f;
    public static int curEnemyCount = 0;
    public final int maxEnemyCount = 3;
    public TestAI[] enemies;

    EnemyManager()
    {
        enemies = new TestAI[maxEnemyCount];
    }

    public void Init(Player dummy)
    {
        for (int i = 0; i < enemies.length; i++)
        {
            enemies[i] = new TestAI();
            enemies[i].init();
            enemies[i].setTarget(dummy.shooter);
        }
    }

    private void SpawnRandomEnemy()
    {
        Random random = new Random();

        for (TestAI e : enemies)
        {
            if (!e.active)
            {
                e.Respawn();
                e.basicShooter.pos.y = 1800;// top of screen
                e.basicShooter.pos.x = random.nextInt(1024);
                ++curEnemyCount;
                break;
            }
        }
    }

    public void Update()
    {
        if (curEnemyCount < maxEnemyCount)
            spawnTimer += Gdx.graphics.getDeltaTime();

        if (spawnTimer >= nextEnemySpawnTime && curEnemyCount < maxEnemyCount)
        {
            SpawnRandomEnemy();
            spawnTimer = 0.f;
        }

        for (TestAI e : enemies)
            e.update(Gdx.graphics.getDeltaTime(), this);
    }

    public void Render()
    {
    }
}
