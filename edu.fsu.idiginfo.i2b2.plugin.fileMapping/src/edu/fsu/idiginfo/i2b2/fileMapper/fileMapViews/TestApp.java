package edu.fsu.idiginfo.i2b2.fileMapper.fileMapViews;

import java.awt.EventQueue;
import javax.swing.JFrame;

import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
public class TestApp {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					TestApp window = new TestApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TestApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 721, 544);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JDesktopPane desktopPane = new JDesktopPane();
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		TypeSourceMap typeSourceMap = new TypeSourceMap();
		typeSourceMap.setBounds(68, 13, 457, 192);
		desktopPane.add(typeSourceMap);
		typeSourceMap.setVisible(true);
	}
}
