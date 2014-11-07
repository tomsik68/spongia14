package sk.exceptional.spongia14.engine;

import org.newdawn.slick.AppGameContainer;

public class GameLauncher implements Runnable {
    public static void main(String[] args) {
	GameLauncher launcher = new GameLauncher();
	launcher.run();
    }

    @Override
    public void run() {
	try {
	    AppGameContainer container = new AppGameContainer(new SpongiaGame());
	    // MAX FPS
	    container.setMaximumLogicUpdateInterval(1000/60);
	    container.setDisplayMode(800, 600, false);
	    container.start();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
