package com.capture.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Timer;
import com.capture.entity.Enemy;
import com.capture.entity.Player;
import com.capture.entity.Projectile;
import com.capture.utils.Direction;
import com.capture.utils.Utilities;

import java.util.ArrayList;
import java.util.Random;

public class MainGameScreen extends ScreenAdapter {

    private SpriteBatch batch;
    private ShapeRenderer sr;

    private Random rng;

    private Texture floor;
    private Texture wall;

    private BitmapFont font;

    private Player player;

    private ArrayList<Enemy> enemies;
    private Timer.Task enemyGen;

    private ArrayList<Projectile> projectiles;

    public MainGameScreen() {
        batch = new SpriteBatch();
        sr = new ShapeRenderer();
        sr.setAutoShapeType(true);

        rng = new Random();

        floor = new Texture(Gdx.files.internal("floor.png"));
        wall = new Texture(Gdx.files.internal("wall.png"));

        font = new BitmapFont();

        player = new Player(new Texture(Gdx.files.internal("player.png")));

        enemies = new ArrayList<Enemy>();
        enemyGen = new Timer.Task() {
            @Override
            public void run() {
                int num = rng.nextInt(2) + 2;
                for (int i = 0; i < num; i++) {
                    enemies.add(new Enemy(new Texture(Gdx.files.internal("goblin.png"))));
                }
            }
        };
        Timer.schedule(enemyGen, 2f, 1.5f);

        projectiles = new ArrayList<Projectile>();
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


    private void shoot() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            projectiles.add(new Projectile(new Texture(Gdx.files.internal("fireball.png")), player, Direction.UP));
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            projectiles.add(new Projectile(new Texture(Gdx.files.internal("fireball.png")), player, Direction.DOWN));
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            projectiles.add(new Projectile(new Texture(Gdx.files.internal("fireball.png")), player, Direction.LEFT));
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            projectiles.add(new Projectile(new Texture(Gdx.files.internal("fireball.png")), player, Direction.RIGHT));
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

        if (player.isAlive()) {
            // Update Entities
            player.update(delta);
            shoot();

            for (Enemy enemy : enemies) {
                enemy.update(delta, player);
            }

            for (Projectile projectile : projectiles) {
                projectile.update(delta);
            }

            for (Enemy enemy : enemies) {
                if (player.collidesWith(enemy)) {
                    player.takeDamageFrom(enemy);
                }
            }

            for (Projectile projectile : projectiles) {
                for (Enemy enemy : enemies) {
                    if (enemy.isAlive() && projectile.isAlive() && projectile.collidesWith(enemy)) {
                        projectile.setAlive(false);
                        enemy.takeDamageFrom(projectile);
                        break;
                    }
                }
            }

            // Begin drawing
            batch.begin();
            drawBackground();

            player.draw(batch);

            for (Enemy enemy : enemies) {
                enemy.draw(batch);
            }

            for (Projectile projectile : projectiles) {
                projectile.draw(batch);
            }
            font.draw(batch, "Health:", 0, font.getLineHeight() * .75f);
            batch.end();
            sr.begin();
            sr.setColor(Color.RED);
            sr.set(ShapeRenderer.ShapeType.Line);
            sr.rect(48, 2, 160, 12);
            sr.set(ShapeRenderer.ShapeType.Filled);
            sr.rect(48, 2, player.getHealth() * 16, 12);
            sr.end();

            // Clean up dead Entities
            for (int i = 0; i < enemies.size(); i++) {
                if (!enemies.get(i).isAlive()) {
                    enemies.remove(i);
                }
            }

            for (int i = 0; i < projectiles.size(); i++) {
                if (!projectiles.get(i).isAlive()) {
                    projectiles.remove(i);
                }
            }
        }
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
        font.dispose();

        player.dispose();

        for (Enemy enemy : enemies) {
            enemy.dispose();
        }
        enemyGen.cancel();

        for (Projectile projectile : projectiles) {
            projectile.dispose();
        }
    }
}
