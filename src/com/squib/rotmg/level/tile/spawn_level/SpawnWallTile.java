package com.squib.rotmg.level.tile.spawn_level;

import com.squib.rotmg.graphics.Screen;
import com.squib.rotmg.graphics.Sprite;
import com.squib.rotmg.level.tile.Tile;

public class SpawnWallTile extends Tile {

	public SpawnWallTile(Sprite sprite) {
		super(sprite);
	}

	@Override
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this.sprite);
	}

	public boolean solid() {
		return true;
	}
}
