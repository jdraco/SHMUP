package com.appmerc.shmup;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class SHMUP extends Game
{
	GameScreen gameScreen;
	public static SpriteBatch batch;
	Texture img;
	TestAI[] tai;
	Player dummy;
	public static Controller controller;
	int numOfAI = 2;

	@Override
	public void create ()
	{
		gameScreen = new GameScreen();
		setScreen(gameScreen);

		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		controller = new Controller();

		dummy = new Player();
		dummy.init();
		dummy.shooter.pos = new Vector2(500,500);

		tai = new TestAI[numOfAI];
		for (int i = 0; i < numOfAI; i++)
		{
			tai[i] = new TestAI();
			tai[i].init();
			tai[i].setTarget(dummy.shooter);
		}
		tai[0].basicShooter.pos.add(64, 64);
		tai[1].basicShooter.pos.add(64, 564);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		super.render();

		batch.begin();

		renderAI();
		renderDummy();

		batch.end();

		controller.draw();
	}

	public void renderShooter(Shooter s)
	{
		batch.draw(img, s.pos.x-(s.size.x/2), s.pos.y-(s.size.y/2), s.size.x, s.size.y);
		for(int j = 0; j < 128; j++)
		{
			if(s.bullets[j].active)
			{
				batch.draw(img,
						s.bullets[j].pos.x-(s.bullets[j].size.x/2),
						s.bullets[j].pos.y-(s.bullets[j].size.y/2),
						s.bullets[j].size.x, s.bullets[j].size.y
				);
			}
		}
	}

	public void renderAI()
	{
		for (int i = 0; i < numOfAI; i++) {
			tai[i].update(Gdx.graphics.getDeltaTime());
			renderShooter(tai[i].basicShooter);
		}
	}

	public void renderDummy()
	{
		dummy.update(Gdx.graphics.getDeltaTime());
		renderShooter(dummy.shooter);
	}

	@Override
	public void dispose ()
	{
		gameScreen.dispose();
		batch.dispose();
		img.dispose();
	}

	@Override
	public void resize(int width, int height)
	{
		gameScreen.resize(width, height);
	}
}
