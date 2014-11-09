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
    }

    public double getPos() {
	return amplitude * Math.sin(frequency * time + phase);
    }
}
