package com.appmerc.shmup;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

class GameScreen implements Screen
{
    // screen
    private Camera camera;
    private Viewport viewport;

    // graphics
    private SpriteBatch batch;
    private Texture[] backgrounds;

    // timing
    private float[] backgroundOffsets = { 0, 0, 0 };
    private float backgroundMaxScrollingSpeed;

    // world parameters
    private final int WORLD_WIDTH = 72;
    private final int WORLD_HEIGHT = 128;

    GameScreen()
    {
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

        backgrounds = new Texture[3];
        backgrounds[0] = new Texture("Parallax100.png");
        backgrounds[1] = new Texture("Parallax80.png");
        backgrounds[2] = new Texture("Parallax60.png");

        backgroundMaxScrollingSpeed = (float)WORLD_HEIGHT / 4;

        batch = new SpriteBatch();
    }

    @Override
    public void render(float deltaTime)
    {
        batch.begin();

        // scrolling background
        renderBackground(deltaTime);

        batch.end();
    }

    private void renderBackground(float deltaTime)
    {
        backgroundOffsets[0] += deltaTime * backgroundMaxScrollingSpeed / 8;
        backgroundOffsets[1] += deltaTime * backgroundMaxScrollingSpeed / 4;
        backgroundOffsets[2] += deltaTime * backgroundMaxScrollingSpeed;

        for (int layer = 0; layer < backgroundOffsets.length; ++layer)
        {
            if (backgroundOffsets[layer] > WORLD_HEIGHT)
                backgroundOffsets[layer] = 0;

            batch.draw(backgrounds[layer], 0, -backgroundOffsets[layer], WORLD_WIDTH, WORLD_HEIGHT);
            batch.draw(backgrounds[layer], 0, -backgroundOffsets[layer] + WORLD_HEIGHT, WORLD_WIDTH, WORLD_HEIGHT);
        }
    }


    @Override
    public void resize(int width, int height)
    {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void hide()
    {

    }

    @Override
    public void dispose()
    {

    }

    @Override
    public void show()
    {

    }
}
