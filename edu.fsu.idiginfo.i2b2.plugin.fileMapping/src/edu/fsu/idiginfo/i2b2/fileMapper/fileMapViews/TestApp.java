package edu.fsu.idiginfo.i2b2.fileMapper.fileMapViews;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import org.springframework.beans.factory.config.ListFactoryBean;

import edu.fsu.idiginfo.i2b2.fileMapper.fileMapperUtil.IFileParser;
public class TestApp {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationContext context = new ClassPathXmlApplicationContext( "applicationContext.xml"); 

					 List<IFileParser> list = (List) context.getBean("FileParserList"); 

					   
					
					  for(int index = 0; index < list.size() ; index ++)
					  {
					   
						 IFileParser parser = (IFileParser) obj.getObject().get(index);
					  }
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
