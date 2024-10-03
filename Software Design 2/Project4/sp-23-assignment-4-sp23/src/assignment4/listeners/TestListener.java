package assignment4.listeners;

public interface TestListener {

    // Call this method right before the test method starts running
    public void testStarted(String str);

    // Call this method right after the test method finished running successfully
    public void testSucceeded(String str);

    // Call this method right after the test method finished running and failed
    public void testFailed(String str);
}
