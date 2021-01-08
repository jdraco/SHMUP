package com.appmerc.shmup;

import com.badlogic.gdx.Gdx;

import java.util.Random;

public class EnemyManager
{
    public enum E_ENEMY_TYPE
    {
        STANDARD,
        KAMIKAZE
    }

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
            //enemies[i] = new TestAI();
            RandomizeType(i);
            enemies[i].active = false;
            enemies[i].setTarget(dummy.shooter);
        }
    }

    private void RandomizeType(int i)
    {
        Random random = new Random();
        int randType = random.nextInt(E_ENEMY_TYPE.values().length);

        switch (randType)
        {
            case 0:
                enemies[i] = new TestAI();
                break;

            case 1:
                enemies[i] = new MovingEnemy();
                break;
        }

        enemies[i].init();
    }

    private void SpawnRandomEnemy(Player dummy)
    {
        Random random = new Random();

        for (int i = 0; i < enemies.length; ++i)
        {
            if (!enemies[i].active)
            {
                RandomizeType(i);
                enemies[i].setTarget(dummy.shooter);
                enemies[i].basicShooter.pos.y = 1800;// top of screen
                enemies[i].basicShooter.pos.x = random.nextInt(1024);
                ++curEnemyCount;
                break;
            }
        }
    }

    public void Update(Player dummy)
    {
        if (curEnemyCount < maxEnemyCount)
            spawnTimer += Gdx.graphics.getDeltaTime();

        if (spawnTimer >= nextEnemySpawnTime && curEnemyCount < maxEnemyCount)
        {
            SpawnRandomEnemy(dummy);
            spawnTimer = 0.f;
        }

        for (TestAI e : enemies)
            e.update(Gdx.graphics.getDeltaTime(), this);
    }

    public void Render()
    {
    }
}
