package com.appmerc.shmup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class UI {
    FreeTypeFontGenerator fontGenerator;
    FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
    BitmapFont font;

    public void init()
    {
        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("arial.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 64;
        fontParameter.borderWidth = 5;
        fontParameter.borderColor = Color.BLACK;
        fontParameter.color = Color.WHITE;
        font = fontGenerator.generateFont(fontParameter);
    }
    public void draw(SpriteBatch batch)
    {
        batch.begin();
        font.draw(batch, "Score " + Statistics.score, 360, 1920);
        font.draw(batch, "Health " + Player.stats.health, 50, 50);
        batch.end();
    }
}
