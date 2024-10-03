package assignment4.listeners;

import assignment4.gui.TestGUI;

public class GUITestListener implements TestListener{

	String test = "";
	TestGUI gui;
	public GUITestListener(TestGUI gui) {
		this.gui = gui;
	}
	
	
    // Call this method right before the test method starts running
    @Override
    public void testStarted(String str){
    	gui.checkRunning(str);
    }
    // Call this method right after the test method finished running successfully
    @Override
    public void testSucceeded(String str) {
    	gui.checkPassed(str);
    	}

    // Call this method right after the test method finished running and failed
    @Override
    public void testFailed(String str) {
    	gui.checkFailed(str);
    }
}
