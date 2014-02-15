package com.squib.rotmg.entity.mob;

import java.util.ArrayList;
import java.util.List;

import com.squib.rotmg.entity.Entity;
import com.squib.rotmg.entity.particle.Particle;
import com.squib.rotmg.entity.projectile.Projectile;
import com.squib.rotmg.entity.projectile.WizardProjectile;
import com.squib.rotmg.graphics.Sprite;

/*
 * @squibdev
 * Derived from TheCherno: Game Programming YouTube Series
 * Last Update: 1.13.14 
 */

public abstract class Mob extends Entity { // Entities have Sprites

	protected Sprite sprite;
	protected int dir = 2;
	protected boolean moving = false;
	protected boolean walking = false;

	public void move(int xa, int ya) {
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}

		if (xa > 0) dir = 1;
		if (xa < 0) dir = 3;
		if (ya > 0) dir = 2;
		if (ya < 0) dir = 0;

		if (!collision(xa,ya)) {
			x += xa;
			y += ya;
		} else {
			Particle p = new Particle(x, y, 50, 50);
			level.add(p);
		}
	}

	@Override
	public void update() {

	}

	protected void shoot(int x, int y, double dir) {
		// dir *= 180 / Math.PI;
		Projectile p = new WizardProjectile(x, y, dir); // (int) casts dir as an int from a double
		level.addProjectile(p);

	}

	private boolean collision(int xa, int ya) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xa) + c % 2 * 14 - 8) / 16;
			int yt = ((y + ya) + c / 2 * 12 + 3) / 16;
			if (level.getTile(xt, yt).solid()) solid = true;
		}
		return solid;
	}

	public void render() {
	}
}
