package GUI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.bartoszek.jtzrestapi.GeoDbClientApi;
import com.bartoszek.jtzrestapi.Utils;

public class QuestionOnePanel extends JPanel {
	JLabel questionLabel;
	Question question;
	Answer answer;
	UserAnswer userAnswer;
	GeoDbClientApi geoDbClientApi;

	public QuestionOnePanel() {
		setLayout(new GridLayout(4, 1));
		setBorder(BorderFactory.createRaisedBevelBorder());
		questionLabel = new JLabel(Messages.getString("QuestionOnePanel.0")); //$NON-NLS-1$
		question = new Question();
		answer = new Answer();
		userAnswer = new UserAnswer();
		geoDbClientApi = new GeoDbClientApi();

		add(questionLabel);
		add(question);
		add(answer);
		add(userAnswer);

	}

	public void updateLanguage(ResourceBundle bundle) {
		questionLabel.setText(bundle.getString("QuestionOnePanel.0")); //$NON-NLS-1$
		question.updateLanguage(bundle);
		answer.updateLanguage(bundle);
		userAnswer.updateLanguage(bundle);
	}

	class Question extends JPanel {
		JLabel question_one;
		JComboBox<String> countries;
		JLabel question_two;
		JTextField answeredPopulation;

		public Question() {
			question_one = new JLabel(Messages.getString("QuestionOnePanel.1")); //$NON-NLS-1$
			countries = new JComboBox<>(Utils.COUNTRIES_PL); // $NON-NLS-1$

			question_two = new JLabel(Messages.getString("QuestionOnePanel.3")); //$NON-NLS-1$
			answeredPopulation = new JTextField(10);

			add(question_one);
			add(countries);
			add(question_two);
			add(answeredPopulation);

		}

		private void updateLanguage(ResourceBundle bundle) {
			question_one.setText(bundle.getString("QuestionOnePanel.1")); //$NON-NLS-1$
			countries = new JComboBox<>(new String[] { bundle.getString("QuestionOnePanel.2") }); //$NON-NLS-1$
			question_two.setText(bundle.getString("QuestionOnePanel.3")); //$NON-NLS-1$
		}
	}

	class Answer extends JPanel {
		JLabel answer_one;
		JLabel api_country;
		JLabel answer_two;
		JLabel api_population;
		JLabel answer_three;

		public Answer() {
			answer_one = new JLabel(Messages.getString("QuestionOnePanel.4")); //$NON-NLS-1$
			api_country = new JLabel("___"); //$NON-NLS-1$
			answer_two = new JLabel(Messages.getString("QuestionOnePanel.6")); //$NON-NLS-1$
			api_population = new JLabel("___"); //$NON-NLS-1$
			answer_three = new JLabel(Messages.getString("QuestionOnePanel.8")); //$NON-NLS-1$

			add(answer_one);
			add(api_country);
			add(answer_two);
			add(api_population);
			add(answer_three);

		}

		private void updateLanguage(ResourceBundle bundle) {
			answer_one.setText(bundle.getString("QuestionOnePanel.4")); //$NON-NLS-1$
			api_country = new JLabel("___"); //$NON-NLS-1$
			answer_two.setText(bundle.getString("QuestionOnePanel.6")); //$NON-NLS-1$
			api_population = new JLabel("___"); //$NON-NLS-1$
			answer_three.setText(bundle.getString("QuestionOnePanel.8")); //$NON-NLS-1$
		}

	}

	class UserAnswer extends JPanel implements ActionListener {
		JLabel answerLabel;
		JTextField userAnswer;
		JButton checkButton;

		public UserAnswer() {
			answerLabel = new JLabel(Messages.getString("QuestionOnePanel.9")); //$NON-NLS-1$
			userAnswer = new JTextField(10);
			checkButton = new JButton(Messages.getString("QuestionOnePanel.10")); //$NON-NLS-1$

			add(answerLabel);
			add(userAnswer);
			add(checkButton);

			checkButton.addActionListener(this);
			setBorder(BorderFactory.createRaisedBevelBorder());

		}

		private void updateLanguage(ResourceBundle bundle) {
			answerLabel.setText(bundle.getString("QuestionOnePanel.9")); //$NON-NLS-1$
			checkButton.setText(bundle.getString("QuestionOnePanel.10")); //$NON-NLS-1$
		}

		@Override
		public void actionPerformed(ActionEvent e) {

				int answeredPopulation = Integer.parseInt(question.answeredPopulation.getText());
				String selectedCountry = question.countries.getSelectedItem().toString();
				System.out.println(answeredPopulation);
				System.out.println(selectedCountry);
				try {
					String countryWikiData = geoDbClientApi.getCountryWikiData(selectedCountry);

					if (countryWikiData == null) {
						answer.api_country.setText(selectedCountry);
						answer.api_population.setText("");
					} else {
						System.out.println(countryWikiData);

						Thread.sleep(1000);

						int citiesCountForCountry = geoDbClientApi.getCitiesCountForCountry(countryWikiData,
								answeredPopulation);

						answer.api_country.setText(selectedCountry);

						answer.api_population.setText(Integer.toString(citiesCountForCountry));
						if (citiesCountForCountry == Integer.parseInt(userAnswer.getText())) {
							answerLabel.setForeground(Color.GREEN);
						} else {
							answerLabel.setForeground(Color.RED);
						}
					}

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}


	}

}