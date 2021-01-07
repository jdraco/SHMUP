package com.appmerc.shmup;

import com.badlogic.gdx.math.Vector2;

public class AABB {
    public static boolean AABBCheck(Vector2 apos, Vector2 asize, Vector2 bpos, Vector2 bsize)
    {
        if (apos.x < bpos.x + bsize.x &&
                apos.x + asize.x > bpos.x &&
                apos.y < bpos.y + bsize.y &&
                apos.y + asize.y > bpos.y) {
            return true;
        }
        return false;
    }
}
