package fileMapViews;

import java.awt.EventQueue;

import javax.swing.JFrame;

import org.springframework.context.ApplicationContext; 

import org.springframework.context.support.ClassPathXmlApplicationContext; 

import Testing.HelloWorldService;

  


  


public class TestSpring {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					ApplicationContext context = new ClassPathXmlApplicationContext( "applicationContext.xml"); 

					 HelloWorldService obj = (HelloWorldService) context.getBean("helloWorldService"); 

					   
					    String message =obj.sayHello(); 
					    	System.out.println(message); 

					TestSpring window = new TestSpring();
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
	public TestSpring() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}