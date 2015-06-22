import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainDisplay extends JFrame implements ActionListener{

	int delay = 50;

	int runTimes = 0;

	int windowHeight, windowLength;

	ControlPanel controlPanel;
	GridPanel gamePanel;

	boolean playStepsOn = false;

	Timer timer;

	public enum displayState{
		PAUSE, PLAY, STEP, STEP_NT;
	}

	displayState state = displayState.PAUSE;

	public MainDisplay(){
		super();

		timer = new Timer(delay, this);
		timer.setActionCommand("timer");
		
		setLayout(null);

		// Generate and position the control panel
		controlPanel = new ControlPanel();		
		add(controlPanel);
		controlPanel.setBounds(0, 0, ControlPanel.width, ControlPanel.height);

		// Add action listeners to the control panel
		controlPanel.play.addActionListener(this);
		controlPanel.pause.addActionListener(this);
		controlPanel.playStep.addActionListener(this);
		controlPanel.step.addActionListener(this);
		controlPanel.setDelay.addActionListener(this);

		// Generate and position the game panel
		gamePanel = new GridPanel();
		add(gamePanel);
		gamePanel.setBounds(251, 0, gamePanel.width, gamePanel.height);

		// Sets default values in text fields
		controlPanel.setHeight.setText(Integer.toString(gamePanel.heightFactor));
		controlPanel.setDelay.setText(Integer.toString(timer.getDelay()));

		// Settings for MainDisplay
		setVisible(true);
		setSize(850, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("The Game of Life");

	}

	// Performs actions based on the buttons pressed
	public void actionPerformed(ActionEvent e){

		// State machine for simulation
		if(state == displayState.PAUSE){
			if(e.getActionCommand().equals("play")){
				state = displayState.PLAY;
				timer.start();
				controlPanel.step.setEnabled(false);
				controlPanel.playStep.setEnabled(false);
			}else if(e.getActionCommand().equals("step")){
				state = displayState.STEP;
			}else if(e.getActionCommand().equals("play_steps")){
				try{
					runTimes = Integer.parseInt(controlPanel.numSteps.getText());
					timer.start();
					state = displayState.STEP_NT;
					timer.start();
					controlPanel.step.setEnabled(false);
					controlPanel.play.setEnabled(false);
					controlPanel.playStep.setEnabled(false);
					controlPanel.numSteps.setEditable(false);					
				}catch(NumberFormatException ex){
					System.out.println("Error: Non - Integer entered in steps field");
				}
			}
		}else if(state == displayState.PLAY){
			if(e.getActionCommand().equals("pause")){
				state = displayState.PAUSE;
				timer.stop();
				controlPanel.step.setEnabled(true);
				controlPanel.playStep.setEnabled(true);				
			}
		}else if(state == displayState.STEP){
			if(e.getActionCommand().equals("step")){
				state = displayState.STEP;
			}else if(e.getActionCommand().equals("play")){
				state = displayState.PLAY;
				timer.start();
				controlPanel.step.setEnabled(false);
				controlPanel.playStep.setEnabled(false);
			}else if(e.getActionCommand().equals("play_steps")){
				try{
					runTimes = Integer.parseInt(controlPanel.numSteps.getText());
					timer.start();
					state = displayState.STEP_NT;
					timer.start();
					controlPanel.step.setEnabled(false);
					controlPanel.play.setEnabled(false);
					controlPanel.playStep.setEnabled(false);
					controlPanel.numSteps.setEditable(false);							
				}catch(NumberFormatException ex){
					System.out.println("Error: Non - Integer entered in steps field");
				}				
			}else{
				state = displayState.PAUSE;				
			}
		}else if(state == displayState.STEP_NT){
			if(e.getActionCommand().equals("pause") || runTimes <= 0){
				state = displayState.PAUSE;
				timer.stop();
				controlPanel.step.setEnabled(true);
				controlPanel.play.setEnabled(true);
				controlPanel.numSteps.setEditable(true);
				controlPanel.playStep.setEnabled(true);
			}else{
				controlPanel.numSteps.setText(Integer.toString(runTimes));				
			}
		}



		if(e.getActionCommand().equals("setdelay")){
			try{
				setDelay(Integer.parseInt(controlPanel.setDelay.getText()));
			}catch(NumberFormatException ex){
				System.out.println("Error: Non - Integer entered in delay field");
			}
		}

		if((state == displayState.PLAY && e.getActionCommand().equals("timer")) || (state == displayState.STEP) || (state == displayState.STEP_NT)){
			Simulation.step();
			gamePanel.repaint();
			if(state == displayState.STEP_NT) runTimes--;
		}


		/*if(e.getActionCommand().equals("play")){
			controlPanel.step.setEnabled(false);
			controlPanel.playStep.setEnabled(false);
			timer.start();
			playStepsOn = false;
		}else if(e.getActionCommand().equals("pause")){
			controlPanel.play.setEnabled(true);
			controlPanel.step.setEnabled(true);
			controlPanel.playStep.setEnabled(true);			
			timer.stop();
			playStepsOn = false;
		}else if(e.getActionCommand().equals("play_steps")){
			try{
				runTimes = Integer.parseInt(controlPanel.numSteps.getText());
				timer.start();
				controlPanel.play.setEnabled(false);
				controlPanel.step.setEnabled(false);
				controlPanel.playStep.setEnabled(false);
				controlPanel.numSteps.setEditable(false);
				playStepsOn = true;
			}catch(NumberFormatException ex){
				System.out.println("Error: non-Integer number entered.");
			}

		}

		// If the sim should be running
		if((timer.isRunning() || e.getActionCommand().equals("step")) && !playStepsOn){
			Simulation.step();
			gamePanel.repaint();
		}else if(runTimes > 0 && playStepsOn){
			runTimes--;
			controlPanel.numSteps.setText(Integer.toString(runTimes));
			Simulation.step();
			gamePanel.repaint();
			if(runTimes == 0){
				timer.stop();
				controlPanel.play.setEnabled(true);
				controlPanel.step.setEnabled(true);
				controlPanel.playStep.setEnabled(true);
				controlPanel.numSteps.setEditable(true);				
			}			
		}*/

		// Updates the textfields, and their respective variables

		// Updates height factor
		try{
			int temp = Integer.parseInt(controlPanel.setHeight.getText());
			if(temp > 0){
				gamePanel.setHeightFactor(temp);
				gamePanel.createBounds();
			}
		}catch(NumberFormatException ex){
			//System.out.println("Non-integer entered into Height Factor field");
		}

		// Updates the delay
		try{
			timer.setDelay(Integer.parseInt(controlPanel.setDelay.getText()));
		}catch(NumberFormatException ex){

		}

		// Updates labels for info regarding the current state of the simulation
		controlPanel.total.setText("Total Cells: " + Integer.toString(MainSetup.rowMax * MainSetup.colMax));
		controlPanel.alive.setText("Alive Cells: " + Integer.toString(Simulation.aliveCells));
		controlPanel.dead.setText("Dead Cells: " + Integer.toString(Simulation.deadCells));
		controlPanel.generation.setText("Generation: " + Integer.toString(Simulation.generation));		
	}

	public void setDelay(int delay){
		this.delay = delay;
	}

}