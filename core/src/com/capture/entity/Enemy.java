package com.capture.entity;

import com.badlogic.gdx.graphics.Texture;
import com.capture.utils.Utilities;

import java.util.Random;

public class Enemy extends Entity {
    public Enemy(Texture texture) {
        super(texture);
        Random rng = new Random();
        int x = rng.nextInt(Utilities.WIDTH - 2 * Utilities.BLOCK_WIDTH) + Utilities.BLOCK_WIDTH;
        int y = rng.nextInt(Utilities.HEIGHT - 2 * Utilities.BLOCK_HEIGHT) + Utilities.BLOCK_HEIGHT;
        int side = rng.nextInt(4);
        switch (side) {
            // left
            case 0:
                x = Utilities.BLOCK_WIDTH;
                break;
            // top
            case 1:
                y = (int) (Utilities.HEIGHT - Utilities.BLOCK_HEIGHT - getHeight());
                break;
            // right
            case 2:
                x = (int) (Utilities.WIDTH - Utilities.BLOCK_WIDTH - getWidth());
                break;
            // bottom
            case 3:
                y = Utilities.BLOCK_HEIGHT;
                break;
        }
        setPosition(x, y);
        _speed = 500;
        _health = 1;
    }

    public void update(float delta, Player player) {
        physics(delta);
        bounds();
        move(delta, player);
    }


    private void move(float delta, Player player) {
        if (player.getY() > getY() && _vel.y < _speed) {
            _vel.y += _speed * delta;
        } else if (player.getY() < getY() && _vel.y > -_speed) {
            _vel.y -= _speed * delta;
        }

        if (player.getX() > getX() && _vel.x < _speed) {
            _vel.x += _speed * delta;
        } else if (player.getX() < getX() && _vel.y > -_speed) {
            _vel.x -= _speed * delta;
        }
    }
}
