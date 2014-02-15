package com.squib.rotmg.entity.mob;

import com.squib.rotmg.Game;
import com.squib.rotmg.entity.projectile.Projectile;
import com.squib.rotmg.entity.projectile.WizardProjectile;
import com.squib.rotmg.graphics.Screen;
import com.squib.rotmg.graphics.Sprite;
import com.squib.rotmg.input.Keyboard;
import com.squib.rotmg.input.Mouse;

/*
 * @squibdev
 * Derived from TheCherno: Game Programming YouTube Series
 * Last Update: 1.13.14 
 */

public class Player extends Mob {

	private Keyboard input;
	private Sprite sprite;
	private int anim = 0;
	private boolean walking = false;

	private int fireRate = 0;

	public Player(Keyboard input) {
		this.input = input;
		sprite = Sprite.player_down;
	}

	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.player_down;
		fireRate = WizardProjectile.FIRE_RATE;
	}

	@Override
	public void update() {
		if (fireRate > 0) fireRate--;
		int xa = 0, ya = 0;
		if (anim < 7500) anim++;
		else anim = 0;
		if (input.up) ya--;
		if (input.down) ya++;
		if (input.left) xa--;
		if (input.right) xa++;

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
		clear();
		updateShooting();
	}

	private void clear() {
		for (int i = 0; i < level.getProjectiles().size(); i++) {
			Projectile p = level.getProjectiles().get(i);
			if (p.isRemoved()) level.getProjectiles().remove(i);
		}
	}

	private void updateShooting() {
		if (Mouse.getB() == 1 && fireRate <= 0) {
			double dx = Mouse.getX() - Game.getWindowWidth() / 2;
			double dy = Mouse.getY() - Game.getWindowHeight() / 2;
			double dir = Math.atan2(dy, dx);
			shoot(x, y, dir);
			fireRate = WizardProjectile.FIRE_RATE;
		}

	}

	@Override
	public void render(Screen screen) {
		int flip = 0;
		if (dir == 0) {
			sprite = Sprite.player_up;
			if (walking) {
				if (anim % 20 < 6) {
					sprite = Sprite.player_up_a;
				} else if (anim % 20 > 14) {
					sprite = Sprite.player_up_b;
				} else sprite = Sprite.player_up;
			}
		}
		if (dir == 1) {
			sprite = Sprite.player_side;
			if (walking) {
				if (anim % 20 < 6) {
					sprite = Sprite.player_side_a;
				} else if (anim % 20 > 14) {
					sprite = Sprite.player_side_b;
				} else sprite = Sprite.player_side;
			}
		}
		if (dir == 2) {
			sprite = Sprite.player_down;
			if (walking) {
				if (anim % 20 < 6) {
					sprite = Sprite.player_down_a;
				} else if (anim % 20 > 14) {
					sprite = Sprite.player_down_b;
				} else sprite = Sprite.player_down;
			}
		}
		if (dir == 3) {
			sprite = Sprite.player_side;
			if (walking) {
				if (anim % 20 < 6) {
					sprite = Sprite.player_side_a;
				} else if (anim % 20 > 14) {
					sprite = Sprite.player_side_b;
				} else sprite = Sprite.player_side;
			}
			flip = 1;
		}
		screen.renderPlayer(x - 16, y - 16, sprite, flip);
	}

}
