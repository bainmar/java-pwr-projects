package GUI;

import java.awt.GridLayout;
import java.io.IOException;
import java.util.Locale;

import javax.swing.JPanel;

public class MainPanel extends JPanel {
	public MainPanel() throws IOException, InterruptedException {
		
		setLayout(new GridLayout(3,1,10,10));
		Locale.setDefault(new Locale("pl","PL"));
		
		QuestionOnePanel questionOnePanel = new QuestionOnePanel();
		QuestionTwoPanel questionTwoPanel = new QuestionTwoPanel();
		add(questionOnePanel);
		add(questionTwoPanel);		
		add(new InfoPanel(questionOnePanel,questionTwoPanel));
	}
}
