package com.squib.rotmg;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import javax.swing.JFrame;

import com.squib.rotmg.entity.mob.Player;
import com.squib.rotmg.graphics.Screen;
import com.squib.rotmg.graphics.Sprite;
import com.squib.rotmg.input.Keyboard;
import com.squib.rotmg.input.Mouse;
import com.squib.rotmg.level.Level;
import com.squib.rotmg.level.RandomLevel;
import com.squib.rotmg.level.SpawnLevel;
import com.squib.rotmg.level.TileCoordinate;

/*
 * @squibdev
 * Derived from TheCherno: Game Programming YouTube Series
 * Last Update: 1.13.14 
 */

/* 
 using Runnable allows this.* to be used (uses this instance of Game instead 

 of new instance of game in start() method)

 when Runnable is implemented, run method is kicked off automatically at 
 initiation of Game

 Canvas pulls a blank rectangular area (a built in canvas for the game to be 
 drawn upon)

 using extends Canvas, makes Game a subclass of Canvas (so that everything in
 Canvas is usable by Game
 */

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	private static int width = 300;
	private static int height = width / 16 * 9;
	private static int scale = 3;

	final static String title = "Realm of the Mad God";

	private Thread thread; // private = only visible to class Game.java
	private JFrame frame;
	private Keyboard key;
	private Level level;
	private Player player;
	private boolean running = false;

	private Screen screen;

	// in order to write data to image, create array below
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public Game() { // this is a constructor; will only be run when Game class
					// initiated
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size); // by extending Canvas, able to pull methods
								// from Canvas such as setPreferredSize

		screen = new Screen(width, height);
		frame = new JFrame();
		key = new Keyboard();
		level = Level.spawn;
		TileCoordinate playerSpawn = new TileCoordinate(19, 61);
		player = new Player(playerSpawn.x(), playerSpawn.y(), key);
		player.init(level);

		addKeyListener(key);

		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}

	public static int getWindowWidth() {
		return width * scale;
	}

	public static int getWindowHeight() {
		return height * scale;
	}

	/*
	 * inside thread will be visible to multiple threads (game thread, start thread) synchronized prevents overlaps of
	 * instructions into the thread, helps avoid crashing/locking up computer
	 */
	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display"); // two arguments, Thread to be run
												// (Game) and name of thread
												// (Display)
		thread.start();
	}

	/*
	 * provides a fallback when thread is minimized, stops effectively... catch pops stacktrace message if try fails
	 */
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
			;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() { // void means that nothing is returned (no return
						// execution at end)
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int ticks = 0;
		requestFocus(); // does function of clicking window, allowing key input
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				ticks++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(ticks + " ups, " + frames + " fps");
				frame.setTitle(title + "  |  " + ticks + " ups, " + frames + " fps");
				ticks = 0;
				frames = 0;
			}
		}
	}

	public void tick() {
		key.update();
		player.update();
		level.update();

	}

	public void render() {
		BufferStrategy bs = getBufferStrategy(); // temporary storage for image
													// data
		if (bs == null) {
			// need to use 3 for triple-buffering, so that images can be
			// rendered to two buffers,
			// giving improvement to speed
			createBufferStrategy(3);
			return;
		}

		screen.clear();
		int xScroll = player.x - screen.width / 2;
		int yScroll = player.y - screen.height / 2;
		level.render(xScroll, yScroll, screen);
		player.render(screen);

		Sprite sprite = new Sprite(2, 2, 0xffffff);
		Random random = new Random();
		for (int i = 0; i < 100; i++) {
			int x = random.nextInt(10);
			int y = random.nextInt(10);
			screen.renderSprite(width - 60, 50, sprite, true);
		}
		
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Verdana", 0, 20));
		// g.fillRect(Mouse.getX() - 32, Mouse.getY() - 32, 64, 64);
		if (Mouse.getB() != -1) g.drawString("Mouse Button: " + Mouse.getB(), 30, 450);
		g.drawString("X: " + player.x + ", Y: " + player.y, 720, 450);

		g.dispose(); // disposes of current graphics, releasing system resources
		bs.show();
	}

	/*
	 * when program is run, this method is found and executed (entry point of program, similar to template match="/")
	 */
	public static void main(String[] args) {
		Game game = new Game(); // initiates Game object game by calling new
								// Game
		game.frame.setResizable(false);
		game.frame.setTitle("Rain");
		game.frame.add(game); // able to add our instance of game to the frame
								// component
		game.frame.pack(); // uses PreferredSize from above to pack frame into
							// given proportions
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // terminates
																	// process
																	// on close
		game.frame.setLocationRelativeTo(null); // sets frame in middle of
												// laptop screen
		game.frame.setVisible(true);

		game.start();
	}

}
