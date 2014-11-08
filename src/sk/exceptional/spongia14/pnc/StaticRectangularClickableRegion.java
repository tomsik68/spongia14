package sk.exceptional.spongia14.pnc;

import sk.exceptional.spongia14.api.actions.Action;

public abstract class StaticRectangularClickableRegion extends
	RectangularClickableRegion {
    public StaticRectangularClickableRegion(int x, int y, int w, int h) {
	this.x = x;
	this.y = y;
	this.w = w;
	this.h = h;
    }

    protected final int x, y, w, h;

    @Override
    protected abstract Action getAction();

    @Override
    public int getX() {
	return x;
    }

    @Override
    public int getY() {
	return y;
    }

    @Override
    public int getWidth() {
	return w;
    }

    @Override
    public int getHeight() {
	return h;
    }

}
