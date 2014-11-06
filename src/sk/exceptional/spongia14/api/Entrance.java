package sk.exceptional.spongia14.api;

public class Entrance {
    public int x, y, w, h;
    public String destination;

    public Entrance(String d, int x, int y, int w, int h) {
	destination = d;
	this.x = x;
	this.y = y;
	this.w = w;
	this.h = h;
    }
}
