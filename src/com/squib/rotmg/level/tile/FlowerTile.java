package com.squib.rotmg.level.tile;

import com.squib.rotmg.graphics.Screen;
import com.squib.rotmg.graphics.Sprite;

public class FlowerTile extends Tile {

	public FlowerTile(Sprite sprite) {
		super(sprite);
	}

	@Override
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this.sprite);
	}
}
