/**
 * 
 */
package com.arun.threadsync;

/**
 * @author ArunMannuru
 *
 */
public class ThreadSync {
	
	public synchronized void timeParams() {  // with synchronized
			try {
				Thread.sleep(50);
			}catch(InterruptedException err) {
				err.printStackTrace();
			}
	}
}
