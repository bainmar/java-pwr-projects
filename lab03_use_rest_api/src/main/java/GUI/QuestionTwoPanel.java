package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.bartoszek.jtzrestapi.GeoDbClientApi;
import com.bartoszek.jtzrestapi.Utils;



public class QuestionTwoPanel extends JPanel {
	JLabel questionLabel;
	QuestionTwo questionTwo;
	UserAnswerTwo userAnswerTwo;
	AnswerTwo answerTwo;
	GeoDbClientApi geoDbClientApi;

	public QuestionTwoPanel() {
		setLayout(new GridLayout(4,1));
		setBorder(BorderFactory.createRaisedBevelBorder());
		geoDbClientApi = new GeoDbClientApi();
		questionLabel=new JLabel(Messages.getString("QuestionTwoPanel.0")); //$NON-NLS-1$

		questionTwo=new QuestionTwo();
		userAnswerTwo=new UserAnswerTwo();
		answerTwo =new AnswerTwo();


		add(questionLabel);
		add(questionTwo);
		add(userAnswerTwo);
		add(answerTwo);
	}

	public void updateLanguage(ResourceBundle bundle) {
		questionLabel.setText(bundle.getString("QuestionTwoPanel.0")); //$NON-NLS-1$
		questionTwo.languageUpdate(bundle);
		answerTwo.languageUpdate(bundle);
		userAnswerTwo.languageUpdate(bundle);
	}

	class QuestionTwo extends JPanel{
		JLabel question_one;
		JComboBox<String> countries;

		public QuestionTwo() {
			question_one= new JLabel(Messages.getString("QuestionTwoPanel.1")); //$NON-NLS-1$
			countries = new JComboBox<>(Utils.COUNTRIES_PL);		 //$NON-NLS-1$


			add(question_one);
			add(countries);



		}

		private void languageUpdate(ResourceBundle bundle) {
			question_one.setText(bundle.getString("QuestionTwoPanel.1")); //$NON-NLS-1$

		}
	}

	class AnswerTwo extends JPanel{
		JLabel currencyApi;
		JLabel answer;
		JLabel countryApi;

		public AnswerTwo() {
			currencyApi= new JLabel("___"); //$NON-NLS-1$
			answer = new JLabel(Messages.getString("QuestionTwoPanel.4")); //$NON-NLS-1$
			countryApi =new JLabel("___"); //$NON-NLS-1$

			add(currencyApi);
			add(answer);
			add(countryApi);


		}

		private void languageUpdate(ResourceBundle bundle) {
			answer.setText(bundle.getString("QuestionTwoPanel.4")); //$NON-NLS-1$

		}

	}

	class UserAnswerTwo extends JPanel implements ActionListener{
		JLabel answerLabel;
		JTextField userAnswer;
		JButton checkButton;

		public UserAnswerTwo() {
			answerLabel = new JLabel(Messages.getString("QuestionTwoPanel.6")); //$NON-NLS-1$
			userAnswer = new JTextField(10);
			checkButton = new JButton(Messages.getString("QuestionTwoPanel.7")); //$NON-NLS-1$
			checkButton.addActionListener(this);

			add(answerLabel);
			add(userAnswer);
			add(checkButton);
			setBorder(BorderFactory.createRaisedBevelBorder());

		}

		private void languageUpdate(ResourceBundle bundle) {
			answerLabel.setText(bundle.getString("QuestionTwoPanel.6")); //$NON-NLS-1$
			checkButton.setText(bundle.getString("QuestionTwoPanel.7")); //$NON-NLS-1$

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String selectedCountry = questionTwo.countries.getSelectedItem().toString();
			System.out.println(selectedCountry);
			try {
				String countryWikiData = geoDbClientApi.getCountryWikiData(selectedCountry);
				System.out.println(countryWikiData);
				Thread.sleep(1000);
				if(countryWikiData==null) {
					answerTwo.currencyApi.setText("");
					answerTwo.countryApi.setText(selectedCountry);
				}else {
				List<String> currencyCodesForCountry = geoDbClientApi.getCurrencyCodesForCountry(countryWikiData);
				System.out.println(currencyCodesForCountry);
				answerTwo.currencyApi.setText(currencyCodesForCountry.toString());
				answerTwo.countryApi.setText(selectedCountry);
				}
			} catch (IOException | InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}