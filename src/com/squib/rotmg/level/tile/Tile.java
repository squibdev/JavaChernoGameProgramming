package com.squib.rotmg.level.tile;

import com.squib.rotmg.graphics.Screen;
import com.squib.rotmg.graphics.Sprite;
import com.squib.rotmg.level.tile.spawn_level.SpawnDirtTile;
import com.squib.rotmg.level.tile.spawn_level.SpawnFloorTile;
import com.squib.rotmg.level.tile.spawn_level.SpawnGrassTile;
import com.squib.rotmg.level.tile.spawn_level.SpawnWallTile;
import com.squib.rotmg.level.tile.spawn_level.SpawnWaterTile;

public class Tile {

	public int x, y;
	public Sprite sprite;

	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile flower = new FlowerTile(Sprite.flower);
	public static Tile rose = new FlowerTile(Sprite.rose);
	public static Tile rock = new RockTile(Sprite.rock);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);

	public static Tile spawn_grass = new SpawnGrassTile(Sprite.spawn_grass);
	public static Tile spawn_water = new SpawnWaterTile(Sprite.spawn_water);
	public static Tile spawn_wall1 = new SpawnWallTile(Sprite.spawn_wall1);
	public static Tile spawn_wall2 = new SpawnWallTile(Sprite.spawn_wall2);
	public static Tile spawn_stone = new SpawnWallTile(Sprite.spawn_stone);
	public static Tile spawn_floor = new SpawnFloorTile(Sprite.spawn_floor);
	public static Tile spawn_dirt = new SpawnDirtTile(Sprite.spawn_dirt);

	public static final int col_spawn_grass = 0xFF00FF00;
	public static final int col_spawn_water = 0;
	public static final int col_spawn_wall1 = 0xFF303030;
	public static final int col_spawn_wall2 = 0xFF005500;
	public static final int col_spawn_stone = 0;
	public static final int col_spawn_floor = 0xFF994904;
	public static final int col_spawn_dirt = 0;

	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}

	public void render(int x, int y, Screen screen) {

	}
	
	public boolean solid() {
		return false;
	}
}
