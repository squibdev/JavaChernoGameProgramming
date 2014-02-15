package com.squib.rotmg.entity.particle;

import java.util.ArrayList;
import java.util.List;

import com.squib.rotmg.entity.Entity;
import com.squib.rotmg.graphics.Screen;
import com.squib.rotmg.graphics.Sprite;

/*
 * @squibdev
 * Derived from TheCherno: Game Programming YouTube Series
 * Last Update: 1.13.14 
 */

public class Particle extends Entity {

	private List<Particle> particles = new ArrayList<Particle>();
	private Sprite sprite;

	private int life;

	protected double xx, yy, xa, ya; // the amount that the particle will move on the x/y axes

	public Particle(int x, int y, int life) {
		this.x = x;
		this.y = y;
		this.xx = x;
		this.yy = y;
		this.life = life;
		sprite = Sprite.particle_normal;

		this.xa = random.nextGaussian();
		this.ya = random.nextGaussian();
	}

	public Particle(int x, int y, int life, int amount) {
		this(x, y, life); // this class' Particle method (method above)
		for (int i = 0; i < amount - 1; i++) { // use amount - 1 because amount used once above,
												// adds a particle for total entered at init
			particles.add(new Particle(x, y, life)); // add a new instance of particle to ArrayList
		}
		particles.add(this);

	}

	public void update() {
		this.xx += xa;
		this.yy += ya;
	}

	public void render(Screen screen) {
		screen.renderSprite((int) xx, (int) yy, sprite, true);
	}

}
