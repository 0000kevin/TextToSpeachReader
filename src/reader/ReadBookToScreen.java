/**
 * 
 */
package reader;

/**
 * @author Kevin O'Shea
 *
 */
public class ReadBookToScreen implements Runnable {
	
	private boolean stop = false;

	@Override
	public void run() {
		readLineByLine();
	}
	
	private void readLineByLine() {
		while(!Start.wholeText.isEmpty()) {
			System.out.println(Start.wholeText.remove());
			
			if(stop) {
				System.out.println("Stopping read to screen...");
				return;
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void setStop(boolean stop) {
		this.stop = stop;
	}
	

}
