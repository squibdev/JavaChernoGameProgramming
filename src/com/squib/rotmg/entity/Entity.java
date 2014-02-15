package com.squib.rotmg.entity;

import java.util.Random;

import com.squib.rotmg.graphics.Screen;
import com.squib.rotmg.level.Level;

/*
 * @squibdev
 * Derived from TheCherno: Game Programming YouTube Series
 * Last Update: 1.13.14 
 */

public class Entity {

	public int x, y;
	private boolean removed = false; // Still present in the level? no
	protected Level level;
	protected final Random random = new Random();

	public int spriteSize = Screen.spriteSize;

	public void update() {
	}

	public void render(Screen screen) {

	}

	public void remove() {
		removed = true; // Remove entity from level
	}

	public boolean isRemoved() {
		return removed;
	}

	public void init(Level level) {
		this.level = level;
	}
}
