package sk.exceptional.spongia14.pnc;

public abstract class RectangularClickableRegion extends ClickableRegion {

    protected boolean shouldRemove = false;

    public RectangularClickableRegion() {

    }

    @Override
    public boolean contains(int x, int y) {
	return (x >= getX() && x <= getX() + getWidth())
		&& (y >= getY() && y < getY() + getHeight());
    }

    final void remove() {
	this.shouldRemove = true;
    }

    public abstract int getX();

    public abstract int getY();

    public abstract int getWidth();

    public abstract int getHeight();

}
