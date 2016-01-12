package hr.fer.zemris.lab5.GUI;

import hr.fer.zemris.lab5.backend.NeuralNet;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClassifierConfig extends JFrame {

	public ClassifierConfig(String title) {
		
		super(title);
		
		this.setSize(280, 260); this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
	    this.setLocation(x, y);

		// Layout Manager
		Container container = getContentPane();
	    setLayout(new BorderLayout());
	    
	    String labels[]   = {"Path to examples:", "Iterations: ", "Example size: ", "Hidden layer size: ", "Number of layers: ", "Learning rate: ", "Batch size: "};
	    int    widths[]   = {10, 5, 5, 5, 5, 5, 5};
	    String defaults[] = {"examples.txt", "10000", "40", "5", "4", "0.01", "1"};
	    
	    final JPanel form = new ClassifierForm(labels, widths, defaults);
		form.setAlignmentX(container.CENTER_ALIGNMENT);
	    container.add(form, BorderLayout.NORTH);
		
		JButton submit = new JButton("Submit!");
		
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JFrame classifier = new Classifier(getNet((ClassifierForm) form), Integer.parseInt(((ClassifierForm)form).getText(6)));
					classifier.setVisible(true);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JPanel buttons = new JPanel();
		buttons.add(submit);
		
		container.add(buttons, BorderLayout.SOUTH);
		
		
	}

	private NeuralNet getNet(ClassifierForm form) throws NumberFormatException, Exception {
		return new NeuralNet(form.getText(0), 
				Integer.parseInt(form.getText(1)), 
				Integer.parseInt(form.getText(2)), 
				Integer.parseInt(form.getText(3)), 
				Integer.parseInt(form.getText(4)), 
				Double.parseDouble(form.getText(5)));
	}
	
}
