package com.arun.threadapplication;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.arun.threadsync.ThreadSync;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import java.awt.Color;
import javax.swing.JFormattedTextField;

public class ThreadApplication extends JFrame {

	
	private JPanel contentPane;
	
	/* Thread Execution Progress Bars */
	
	JProgressBar oneThreadProgressBar = new JProgressBar(0,100);
	JProgressBar secondThreadProgressBar = new JProgressBar(0,100);
	JProgressBar thirdThreadProgressBar = new JProgressBar(0,100);
	JProgressBar fourthThreadProgressBar = new JProgressBar(0,100);
	
	/* Individual Thread Execution Total */
	
	JFormattedTextField firstThreadTotal = new JFormattedTextField();
	JFormattedTextField secondThreadTotal = new JFormattedTextField();
	JFormattedTextField thirdThreadTotal = new JFormattedTextField();
	JFormattedTextField fourthThreadTotal = new JFormattedTextField();
	
	/* Grand Total of Thread Execution */
	
	JFormattedTextField grandTotal = new JFormattedTextField();
	
	/* Thread Synchronization class which sends sleep params */
	
	ThreadSync threadSync = new ThreadSync();
	
	/* Thread Instances */
	
	FirstThread first = new FirstThread();
	SecondThread second = new SecondThread();
	ThirdThread third = new ThirdThread();
	FourthThread fourth = new FourthThread();
	
	/* Current Total which Is used to increment the grand total of all threads execution time */ 
	
	public static int currentTotal = 0;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThreadApplication frame = new ThreadApplication();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the frame.
	 */
	public ThreadApplication() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/*   Application Name Label   */
		
		JLabel lblThreadTestApplication = new JLabel("Thread Test Application");
		lblThreadTestApplication.setBounds(135, 6, 163, 30);
		contentPane.add(lblThreadTestApplication);
		
		/* Start Button */
		
		JButton btnStart = new JButton("Start");
		btnStart.setBounds(6, 242, 75, 30);
		contentPane.add(btnStart);
		btnStart.addActionListener(new StartThread());
		
		/* Pause Button */
		
		JButton btnPause = new JButton("Pause");
		btnPause.setBounds(78, 242, 67, 30);
		contentPane.add(btnPause);
		
		/* Resume Button */
		
		JButton btnResume = new JButton("Resume");
		btnResume.setBounds(144, 242, 75, 30);
		contentPane.add(btnResume);
		btnResume.addActionListener(new ResumeThread());
		
		/* Grand Total label */
		
		JLabel lblGrandTotal = new JLabel("Grand Total:");
		lblGrandTotal.setBounds(231, 248, 97, 16);
		contentPane.add(lblGrandTotal);
		
		/* Thread One Label  */
		
		JLabel thread_label_1 = new JLabel("1");
		thread_label_1.setBounds(6, 51, 8, 16);
		contentPane.add(thread_label_1);
		
		oneThreadProgressBar.setForeground(Color.RED);
		oneThreadProgressBar.setStringPainted(true);
		oneThreadProgressBar.setBounds(42, 48, 256, 20);
		contentPane.add(oneThreadProgressBar);
		
		/* Thread two Label */
		
		JLabel thread_label_2 = new JLabel("2");
		thread_label_2.setBounds(6, 93, 8, 16);
		contentPane.add(thread_label_2);
		
		secondThreadProgressBar.setStringPainted(true);
		secondThreadProgressBar.setForeground(Color.BLUE);
		secondThreadProgressBar.setBounds(42, 89, 256, 20);
		contentPane.add(secondThreadProgressBar);
		
		/* Thread Three Label */
		
		JLabel thread_label_3 = new JLabel("3");
		thread_label_3.setBounds(6, 138, 8, 16);
		contentPane.add(thread_label_3);
		
		/* Third thread Progress Bar */
		
		thirdThreadProgressBar.setForeground(Color.PINK);
		thirdThreadProgressBar.setStringPainted(true);
		thirdThreadProgressBar.setBounds(42, 134, 256, 20);
		contentPane.add(thirdThreadProgressBar);
		
		/* Thread Four Label */
		
		JLabel thread_label_4 = new JLabel("4");
		thread_label_4.setBounds(6, 182, 8, 16);
		contentPane.add(thread_label_4);
		
		/* Fourth Thread Progress Bar */
		
		fourthThreadProgressBar.setForeground(Color.ORANGE);
		fourthThreadProgressBar.setStringPainted(true);
		fourthThreadProgressBar.setBounds(42, 178, 256, 20);
		contentPane.add(fourthThreadProgressBar);
		
		/* First Thread Total */
		
		firstThreadTotal.setBounds(346, 46, 47, 26);
		contentPane.add(firstThreadTotal);
		
		/* Second Thread Total */
		
		secondThreadTotal.setBounds(346, 88, 47, 26);
		contentPane.add(secondThreadTotal);
		
		/* Third Thread Total  */
		
		thirdThreadTotal.setBounds(346, 133, 47, 26);
		contentPane.add(thirdThreadTotal);
		
		/* Fourth Thread Total  */
		
		fourthThreadTotal.setBounds(346, 177, 47, 26);
		contentPane.add(fourthThreadTotal);
		
		/* Grand Thread Execution Total  */		
		
		grandTotal.setBounds(346, 243, 47, 26);
		contentPane.add(grandTotal);
		
	}
	
	/* First Thread Class implementing Runnable Interface  */
	
	class FirstThread implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int i=1; i < 100; i++) {
				
			/*  First Thread Execution time in ms	*/	
			
				oneThreadProgressBar.setValue(i);
				oneThreadProgressBar.repaint();
				oneThreadProgressBar.setString(Integer.toString(i)+ "ms");
		
			/* First Thread total execution time in ms */
				
				firstThreadTotal.setValue(i);
			
		    /* Grand Total execution time in ms	 */
				
				grandTotal.setValue(grandTotal());// Grand Total method which handles critical situation and returns total
			
				
			/* Thread synchronization sleep time	*/	
				
				threadSync.timeParams();
		
			}
		}	
	}
	
	
	/* Second Thread Class implementing Runnable Interface  */
	
	class SecondThread implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int i=1; i < 100; i++) {
				
				/*  Second Thread Execution time in ms	*/	
				
				secondThreadProgressBar.setValue(i);
				secondThreadProgressBar.repaint();
				secondThreadProgressBar.setString(Integer.toString(i)+ "ms");
				
				/* Second Thread total execution time in ms */	
				secondThreadTotal.setValue(i);
				
				/* Grand Total execution time in ms	 */
				
					grandTotal.setValue(grandTotal());// Grand Total method which handles critical situation and returns total
				
			
				/* Thread synchronization sleep time	*/	
				threadSync.timeParams();
			}
		}
		
	}
	
	/* Third Thread Class implementing Runnable Interface  */
	
	
	class ThirdThread implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int i=1; i < 100; i++) {
				
				/*  Third Thread Execution time in ms	*/	
				thirdThreadProgressBar.setValue(i);
				thirdThreadProgressBar.repaint();
				thirdThreadProgressBar.setString(Integer.toString(i)+ "ms");
				
				/* Third Thread total execution time in ms */	
				thirdThreadTotal.setValue(i);
				/* Grand Total execution time in ms	 */
				
				
					grandTotal.setValue(grandTotal());// Grand Total method which handles critical situation and returns total
				
				
				/* Thread synchronization sleep time	*/	
				threadSync.timeParams();
			}
		}
		
	}
	
	/* Fourth Thread Class implementing Runnable Interface  */
	
	class FourthThread implements Runnable{
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int i=1; i < 100; i++) {
				/*  Fourth Thread Execution time in ms	*/	
				fourthThreadProgressBar.setValue(i);
				fourthThreadProgressBar.repaint();
				fourthThreadProgressBar.setString(Integer.toString(i)+ "ms");
				/* Fourth Thread total execution time in ms */
				fourthThreadTotal.setValue(i);
				/* Grand Total execution time in ms	 */
					grandTotal.setValue(grandTotal());  // Grand Total method which handles critical situation and returns total
				/* Thread synchronization sleep time	*/	
				threadSync.timeParams();
			}
		}
		
	}
	
	/* Thread Execution onClick Start Button */
	
	class StartThread implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		
			/* First Thread Start execution */
			
			Thread firstThread = new Thread(first);
			firstThread.start();
			
			/* Second Thread Start Execution */
			
			Thread secondThread = new Thread(second);
			secondThread.start();
			
			/* Third Thread Start Execution */
			
			Thread thirdThread = new Thread(third);
			thirdThread.start();
			
			/* Fourth Thread Start Execution */
			
			Thread fourthThread = new Thread(fourth);
			fourthThread.start();
			
		}
	}
	/* On click resume btn Implementation which will resume the execution process */
	
	class ResumeThread implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			Thread firstThreadResume = new Thread(first);
			firstThreadResume.resume();
			
			Thread secondThreadResume = new Thread(second);
			secondThreadResume.resume();
			
			Thread thirdThreadResume = new Thread(third);
			thirdThreadResume.resume();
			
			Thread fourthThreadResume = new Thread(fourth);
			fourthThreadResume.resume();
			
		}	
	}
	/* Critical Situation for Grand Total */
	public static synchronized int grandTotal() {
		return currentTotal++;
	}
	
}
