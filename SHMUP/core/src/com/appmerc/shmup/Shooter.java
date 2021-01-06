package com.appmerc.shmup;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Shooter {
    int iter;
    Vector2 pos;
    Vector2 size;
    Bullet[] bullets;

    public void init()
    {
        iter = 0;
        pos = new Vector2(0,0);
        size = new Vector2(128,128);
        bullets = new Bullet[128];
        for (int i = 0; i < 128; i++) {
            bullets[i] = new Bullet();
            bullets[i].init();
        }
    }

    public void update(float dt)
    {
        for (int i = 0; i < 128; i++) {
            if(bullets[i].active)
            {
                bullets[i].update(dt);
            }
        }
    }

    public void draw(SpriteBatch batch, Texture img)
    {
        batch.begin();
        batch.draw(img, pos.x, pos.y);
        batch.end();
        for (int i = 0; i < 128; i++) {
            if(bullets[i].active)
            {
                bullets[i].draw(batch,img);
            }
        }
    }

    public void shootBullet(Vector2 dir)
    {
        if(bullets[iter].active)
        {
            iter++;
            shootBullet(dir);
        }
        else {
            bullets[iter].dir = dir;
            bullets[iter].pos = new Vector2(pos);
            bullets[iter].active = true;
            iter++;
        }
    }

    public Vector2 getDirection(Shooter otherShooter)
    {
        Vector2 dir = new Vector2(otherShooter.pos.x,otherShooter.pos.y);
        dir.add(-pos.x,-pos.y);
        dir.nor();
        return dir;
    }
}
