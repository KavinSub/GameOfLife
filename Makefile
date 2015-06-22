# Makefile for the Conway's game of life program

JCC = javac

MainSetup: MainSetup.class MainDisplay.class ControlPanel.class GridPanel.class Simulation.class
	jar cvfm GameOfLife.jar Manifest.txt *.class
	chmod 700 GameOfLife.jar
	rm *.class

MainSetup.class: MainSetup.java
	$(JCC) MainSetup.java

MainDisplay.class: MainDisplay.java
	$(JCC) MainDisplay.java

ControlPanel.class: ControlPanel.java
	$(JCC) ControlPanel.java

GridPanel.class: GridPanel.java
	$(JCC) GridPanel.java

Simulation.class: Simulation.java
	$(JCC) Simulation.java

clean:
	rm *.class;
