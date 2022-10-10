/**
 * 
 */
package reader;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

/**
 * @author Kevin O'Shea
 *
 */
public class SpeakBook implements Runnable {

	private boolean stop = false;

	@Override
	public void run() {
		System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
		Voice voice;
		voice = VoiceManager.getInstance().getVoice("kevin16");

		if (voice != null) {
			voice.allocate();// Allocating Voice
			try {
				voice.setRate(170);// Setting the rate of the voice
				voice.setPitch(100);// Setting the Pitch of the voice
				voice.setVolume(1);// Setting the volume of the voice			
				while(!Start.wholeText.isEmpty()) {
					voice.speak(Start.wholeText.remove());
					if(stop) {
						System.out.println("Voice Stopped");
						return;
					}				
				}
			} catch (Exception e1) {
			}
		} else {
			throw new IllegalStateException("Cannot find voice: kevin");
		}
	}
	
	public void setStop(boolean stop) {
		this.stop = stop;

	}


}
