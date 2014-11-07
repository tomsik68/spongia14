package sk.exceptional.spongia14.pnc;

public abstract class RectangularClickableRegion extends ClickableRegion {
    protected final int x, y, w, h;
    protected boolean shouldRemove = false;

    public RectangularClickableRegion(int x, int y, int w, int h) {
	this.x = x;
	this.y = y;
	this.w = w;
	this.h = h;
    }

    @Override
    public boolean contains(int x, int y) {
	return (x >= this.x && x <= this.x + this.w)
		&& (y >= this.y && y < this.y + this.h);
    }

    final void remove() {
	this.shouldRemove = true;
    }

}
