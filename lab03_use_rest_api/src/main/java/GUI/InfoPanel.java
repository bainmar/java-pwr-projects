package GUI;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoPanel extends JPanel implements ItemListener {
	JLabel languageLabel;
	JComboBox<String> language;
	JLabel info;
	QuestionOnePanel questionOnePanel;
	QuestionTwoPanel questionTwoPanel;

	public InfoPanel(QuestionOnePanel questionOnePanel, QuestionTwoPanel questionTwoPanel) {
		this.questionOnePanel=questionOnePanel;
		this.questionTwoPanel=questionTwoPanel;
		setBorder(BorderFactory.createRaisedBevelBorder());
		languageLabel = new JLabel(Messages.getString("InfoPanel.0")); //$NON-NLS-1$
		language = new JComboBox<>(new String[] { "PL", "EN" }); //$NON-NLS-1$ //$NON-NLS-2$
		language.addItemListener(this);
		add(languageLabel);
		add(language);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {//
		JComboBox ev = (JComboBox) e.getSource();
		int selectedIndex = ev.getSelectedIndex();
		System.out.println(selectedIndex);
		Locale locale;
		switch (selectedIndex) {
		case 0:
			locale = new Locale("pl");
			Locale.setDefault(locale);
			updateLanguage();
			break;
		case 1:
			locale = new Locale("en","EN");
			Locale.setDefault(locale);
			updateLanguage();

			break;
		
		default:
			locale = new Locale("pl");
			Locale.setDefault(locale);
			updateLanguage();
		}
	}

	private void updateLanguage() {

		ResourceBundle bundle = ResourceBundle.getBundle("GUI/messages");
		languageLabel.setText(bundle.getString("InfoPanel.0"));
		questionOnePanel.updateLanguage(bundle);
		questionTwoPanel.updateLanguage(bundle);
		

	}

}
