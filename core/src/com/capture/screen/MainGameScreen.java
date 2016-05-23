package com.capture.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.capture.entity.Player;
import com.capture.utils.Utilities;

public class MainGameScreen extends ScreenAdapter {

    private SpriteBatch batch;
    private ShapeRenderer sr;

    private Texture floor;
    private Texture wall;

    private Player player;

    public MainGameScreen() {
        batch = new SpriteBatch();
        sr = new ShapeRenderer();
        sr.setAutoShapeType(true);

        floor = new Texture(Gdx.files.internal("floor.png"));
        wall = new Texture(Gdx.files.internal("wall.png"));

        player = new Player(new Texture(Gdx.files.internal("player.png")));

    }

    /**
     * Draw the background tiles for the game
     */
    private void drawBackground() {
        int screenWidth = Utilities.WIDTH;
        int screenHeight = Utilities.HEIGHT;
        int numRows = screenWidth / Utilities.BLOCK_WIDTH;
        int numCols = screenHeight / Utilities.BLOCK_HEIGHT;
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (row == 0 || col == 0 || row == numRows - 1 || col == numCols - 1) {
                    batch.draw(wall, row * Utilities.BLOCK_WIDTH, col * Utilities.BLOCK_HEIGHT);
                } else {
                    batch.draw(floor, row * Utilities.BLOCK_WIDTH, col * Utilities.BLOCK_HEIGHT);
                }
            }
        }
    }

    /**
     * Draws the screen every 60th of a second
     *
     * @param delta time passed since last draw
     */
    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update the player
        player.update(delta);

        // Begin drawing
        batch.begin();
        drawBackground();

        player.draw(batch);
        batch.end();
        sr.begin();

        sr.end();
    }

    /**
     * Cleans up all resources
     */
    @Override
    public void dispose() {
        batch.dispose();
        sr.dispose();
        floor.dispose();
        wall.dispose();
        player.dispose();
    }
}
