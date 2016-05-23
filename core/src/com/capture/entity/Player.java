package com.capture.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.capture.utils.Utilities;

public class Player extends Entity {
    public Player(Texture texture) {
        super(texture);
        setPosition(Utilities.WIDTH / 2, Utilities.HEIGHT / 2);
    }

    public void update(float delta) {
        phsyics(delta);
        bounds();
        move(delta);
    }

    private void move(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.W) && _vel.y < _speed) {
            _vel.y += _speed * delta;
        } else if (Gdx.input.isKeyPressed(Input.Keys.S) && _vel.y > -_speed) {
            _vel.y -= _speed * delta;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D) && _vel.x < _speed) {
            _vel.x += _speed * delta;
        } else if (Gdx.input.isKeyPressed(Input.Keys.A) && _vel.y > -_speed) {
            _vel.x -= _speed * delta;
        }
    }
}
