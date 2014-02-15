package com.squib.rotmg.graphics;

/*
 * @squibdev
 * Derived from TheCherno: Game Programming YouTube Series
 * Last Update: 1.13.14 
 */

public class Sprite {

	public final int SIZE; // size of specific sprite
	private int width, height;
	private int x, y;
	public int[] pixels;
	private SpriteSheet sheet;

	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite flower = new Sprite(16, 0, 1, SpriteSheet.tiles);
	public static Sprite rock = new Sprite(16, 2, 3, SpriteSheet.tiles);
	public static Sprite rose = new Sprite(16, 4, 2, SpriteSheet.tiles);
	public static Sprite voidSprite = new Sprite(16, 9, 0, SpriteSheet.tiles);

	// Spawn Level Sprites
	public static Sprite spawn_grass = new Sprite(16, 0, 0, SpriteSheet.spawn_level);
	public static Sprite spawn_water = new Sprite(16, 2, 0, SpriteSheet.spawn_level);
	public static Sprite spawn_wall1 = new Sprite(16, 0, 1, SpriteSheet.spawn_level);
	public static Sprite spawn_wall2 = new Sprite(16, 2, 1, SpriteSheet.spawn_level);
	public static Sprite spawn_stone = new Sprite(16, 0, 2, SpriteSheet.spawn_level);
	public static Sprite spawn_floor = new Sprite(16, 1, 1, SpriteSheet.spawn_level);
	public static Sprite spawn_dirt = new Sprite(16, 1, 0, SpriteSheet.spawn_level);

	// Player Sprites
	public static Sprite player_up = new Sprite(32, 0, 5, SpriteSheet.tiles);
	public static Sprite player_side = new Sprite(32, 1, 5, SpriteSheet.tiles);
	public static Sprite player_down = new Sprite(32, 2, 5, SpriteSheet.tiles);

	public static Sprite player_up_a = new Sprite(32, 0, 6, SpriteSheet.tiles);
	public static Sprite player_up_b = new Sprite(32, 0, 7, SpriteSheet.tiles);

	public static Sprite player_side_a = new Sprite(32, 1, 6, SpriteSheet.tiles);
	public static Sprite player_side_b = new Sprite(32, 1, 7, SpriteSheet.tiles);

	public static Sprite player_down_a = new Sprite(32, 2, 6, SpriteSheet.tiles);
	public static Sprite player_down_b = new Sprite(32, 2, 7, SpriteSheet.tiles);

	// Projectile Sprites
	public static Sprite projectile_wizard = new Sprite(16, 0, 0, SpriteSheet.projectile_wizard);

	// Particle Sprites
	public static Sprite particle_normal = new Sprite(3, 0x66AAAAAA);

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.width = size;
		this.height = size;
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}

	public Sprite(int width, int height, int color) {
		SIZE = -1;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		setColor(color);
	}

	public Sprite(int size, int color) {
		this.width = size;
		this.height = size;
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);
	}

	private void setColor(int color) {
		for (int i = 0; i < width * height; i++) {
			pixels[i] = color;
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}

}
