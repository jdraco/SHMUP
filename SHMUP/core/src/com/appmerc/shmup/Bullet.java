package com.appmerc.shmup;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
    Vector2 pos;
    Vector2 dir;
    Vector2 size;
    float speed;
    boolean active;

    public void init()
    {
        pos = new Vector2(0,0);
        dir = new Vector2(0,0);
        size = new Vector2(64,64);
        active = false;
        speed = 5.0f;
    }

    public void update(float dt)
    {
        if(active)
        {
            Vector2 fdir = new Vector2(dir);
            fdir.mulAdd(fdir, speed);
            fdir.mulAdd(fdir, dt);
            pos.add(fdir);
        }
        //if out of screen active = false;
    }

    public boolean checkAABB(Vector2 targetPos, Vector2 targetSize)
    {
        if(AABB.AABBCheck(targetPos,targetSize,pos,size))
        {
            return true;
        }
        return false;
    }

    public void draw(SpriteBatch batch, Texture img)
    {
        batch.draw(img, pos.x, pos.y);
    }
}
