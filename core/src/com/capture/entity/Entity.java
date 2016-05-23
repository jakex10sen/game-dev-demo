package com.capture.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.capture.utils.Utilities;

import java.util.Date;

public class Entity extends Sprite {
    protected Vector2 _vel;
    protected int _friction;
    protected int _speed;
    protected int _health;
    protected int _damage;
    protected Date _lastHit;

    public Entity(Texture texture) {
        super(texture);
        _vel = new Vector2();
        _friction = 7;
        _speed = 1000;
        _health = 10;
        _damage = 1;
    }

    /**
     * Updates the {@link Entity} position based on some simple physics
     *
     * @param delta time since the screen was last drawn
     */
    public void physics(float delta) {
        setX(getX() + _vel.x * delta);
        setY(getY() + _vel.y * delta);
        _vel.x = _vel.x * (1 - Math.min(delta * _friction, 1));
        _vel.y = _vel.y * (1 - Math.min(delta * _friction, 1));

    }

    /**
     * Makes sure the {@link Entity} stays in bounds of the screen
     */
    public void bounds() {
        if (getY() < Utilities.BLOCK_HEIGHT) {
            setY(Utilities.BLOCK_HEIGHT);
            _vel.y = 0;
        } else if (getY() + getHeight() > Utilities.HEIGHT - Utilities.BLOCK_HEIGHT) {
            setY(Utilities.HEIGHT - Utilities.BLOCK_HEIGHT - getHeight());
            _vel.y = 0;
        }

        if (getX() < Utilities.BLOCK_HEIGHT) {
            setX(Utilities.BLOCK_WIDTH);
            _vel.y = 0;
        } else if (getX() + getWidth() > Utilities.WIDTH - Utilities.BLOCK_WIDTH) {
            setX(Utilities.WIDTH - Utilities.BLOCK_WIDTH - getWidth());
            _vel.x = 0;
        }

    }

    /**
     * Checks either the {@link Entity} has collided with another {@link Entity}
     *
     * @param other other {@link Entity} to check for collision with
     * @return true if collision occurred false otherwise
     */
    public boolean collidesWith(Entity other) {
        return getX() + getWidth() > other.getX() &&
                getX() < other.getX() + other.getWidth() &&
                getY() + getHeight() > other.getY() &&
                getY() < other.getY() + other.getHeight();
    }

    /**
     * Reduces health based on the other {@link Entity}'s damage
     *
     * @param attacker {@link Entity} attacking this {@link Entity}
     */
    public void takeDamageFrom(Entity attacker) {
        Date now = new Date();
        if (_lastHit == null) {
            _lastHit = now;
            _health -= attacker.getDamage();
        } else if (now.getTime() - _lastHit.getTime() > 250) {
            _lastHit = now;
            _health -= attacker.getDamage();
        }
    }


    public int getDamage() {
        return _damage;
    }

    public int getHealth() {
        return _health;
    }

    public boolean isAlive() {
        return _health > 0;
    }

    public void dispose() {
        getTexture().dispose();
    }
}
