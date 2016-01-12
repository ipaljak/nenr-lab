package hr.fer.zemris.lab5.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClassifierForm extends JPanel {

	private JTextField[] fields;

	public ClassifierForm(String[] labels, int[] widths, String defaults[]) {
		
		super(new BorderLayout());
	    
		JPanel labelPanel = new JPanel(new GridLayout(labels.length, 1));
	    JPanel fieldPanel = new JPanel(new GridLayout(labels.length, 1));
	    
	    add(labelPanel, BorderLayout.WEST);
	    add(fieldPanel, BorderLayout.CENTER);
	    
	    fields = new JTextField[labels.length];

	    for (int i = 0; i < labels.length; ++i) {
	    	
	      fields[i] = new JTextField(defaults[i]);
	      
	      if (i < widths.length)
	        fields[i].setColumns(widths[i]);

	      JLabel lab = new JLabel(labels[i], JLabel.RIGHT);
	      lab.setLabelFor(fields[i]);

	      labelPanel.add(lab);
	      JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
	      p.add(fields[i]);
	      fieldPanel.add(p);
	    }
	  }

	  public String getText(int i) {
	    return (fields[i].getText());
	  }
	
}
