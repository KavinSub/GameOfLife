import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel{
	static int width = 250;
	static int height = 600;

	JButton play, pause, playStep, step;
	JTextField numSteps, setDelay, setHeight;
	JLabel delay, heightFactor;
	JLabel total, alive, dead, generation;

	ControlPanel(){
		super();
		setSize(width, height);

		setLayout(null);

		// Initializes all the various components
		play = new JButton("Play");
		add(play);
		play.setBounds(20, 50, 90, 30);
		play.setActionCommand("play");

		pause = new JButton("Pause");
		add(pause);
		pause.setBounds(140, 50, 90, 30);
		pause.setActionCommand("pause");

		playStep = new JButton("Play Steps");
		add(playStep);
		playStep.setBounds(20, 110, 90, 30);
		playStep.setActionCommand("play_steps");

		numSteps = new JTextField(90);
		add(numSteps);
		numSteps.setBounds(140, 110, 90, 30);

		step = new JButton("Step");
		add(step);
		step.setBounds(80, 170, 90, 30);
		step.setActionCommand("step");

		delay = new JLabel("Delay");
		add(delay);
		delay.setBounds(90, 230, 90, 30);

		setDelay = new JTextField(90);
		add(setDelay);
		setDelay.setBounds(140, 230, 90, 30);
		setDelay.setActionCommand("setdelay");

		heightFactor = new JLabel("Height Factor");
		add(heightFactor);
		heightFactor.setBounds(40, 290, 90, 30);

		setHeight = new JTextField(90);
		add(setHeight);
		setHeight.setBounds(140, 290, 90, 30);
		setHeight.setActionCommand("setheight");

		total = new JLabel("Total Cells: ");
		add(total);
		total.setBounds(80, 350, 130, 30);

		alive = new JLabel("Alive Cells: ");
		add(alive);
		alive.setBounds(80, 410, 130, 30);

		dead = new JLabel("Dead Cells: ");
		add(dead);
		dead.setBounds(80, 470, 130, 30);

		generation = new JLabel("Generation: ");
		add(generation);
		generation.setBounds(80, 530, 130, 30);


	}
}