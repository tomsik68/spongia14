package sk.wolfi.modelengine;

public class Pendulum {
    private final double amplitude, frequency, phase;
    private double time = 0;

    public Pendulum(double ampl, double freq) {
	this(ampl, freq, Math.random() * 360);
    }

    public Pendulum(double ampl, double freq, double phase) {
	this.amplitude = ampl;
	this.frequency = freq;
	this.phase = phase;
	time = 0;
    }

    public void update(double deltaTime) {
	time += deltaTime;
	// ochrana proti preteceniu
	if (time >= Double.MAX_VALUE - 50) {
	    time = 0;
	}
    }

    public double getPos() {
	return amplitude * Math.sin(frequency * time + phase);
    }
}
