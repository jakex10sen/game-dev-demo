package com.capture.entity;

import com.badlogic.gdx.graphics.Texture;
import com.capture.utils.Direction;
import com.capture.utils.Utilities;

public class Projectile extends Entity {
    private Direction _direction;
    private boolean _alive;

    public Projectile(Texture texture, Entity owner, Direction direction) {
        super(texture);
        _damage = owner.getDamage();
        _direction = direction;
        _alive = true;
        _speed = 1000;
        float x = owner.getX() + owner.getWidth() / 2;
        float y = owner.getY() + owner.getHeight() / 2;
        switch (_direction) {
            case UP:
                y = owner.getY() + owner.getHeight();
                setRotation(0);
                break;
            case RIGHT:
                x = owner.getX() + owner.getWidth();
                setRotation(270);
                break;
            case DOWN:
                y = owner.getY();
                setRotation(180);
                break;
            case LEFT:
                x = owner.getX();
                setRotation(90);
                break;
        }
        setPosition(x, y);
    }

    public void update(float delta) {
        physics(delta);
        bounds();
        move();
    }

    @Override
    public void bounds() {
        if (getY() < Utilities.BLOCK_HEIGHT) {
            setY(Utilities.BLOCK_HEIGHT);
            _vel.y = 0;
            _alive = false;
        } else if (getY() + getHeight() > Utilities.HEIGHT - Utilities.BLOCK_HEIGHT) {
            setY(Utilities.HEIGHT - Utilities.BLOCK_HEIGHT - getHeight());
            _vel.y = 0;
            _alive = false;
        }

        if (getX() < Utilities.BLOCK_HEIGHT) {
            setX(Utilities.BLOCK_WIDTH);
            _vel.y = 0;
            _alive = false;
        } else if (getX() + getWidth() > Utilities.WIDTH - Utilities.BLOCK_WIDTH) {
            setX(Utilities.WIDTH - Utilities.BLOCK_WIDTH - getWidth());
            _vel.x = 0;
            _alive = false;
        }
    }

    private void move() {
        switch (_direction) {
            case UP:
                _vel.set(0, _speed);
                break;
            case LEFT:
                _vel.set(-_speed, 0);
                break;
            case RIGHT:
                _vel.set(_speed, 0);
                break;
            case DOWN:
                _vel.set(0, -_speed);
                break;
        }
    }

    @Override
    public boolean isAlive() {
        return _alive;
    }

    public void setAlive(boolean alive) {
        _alive = alive;
    }
}
