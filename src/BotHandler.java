import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BotHandler extends JFrame {
	private JTextField inputBox = new JTextField();
	private JTextArea chatArea = new JTextArea();

	public BotHandler() {
		//TODO: Improve bot answers system
		ArrayList<String> exceptionMessageList = new ArrayList<String>(
				Arrays.asList("maalesef anlasilmadi...", "lutfen tekrarlar misin", "???"));

		ArrayList<String> greetingMessageList = new ArrayList<String>(
				Arrays.asList("ooo selammm..", "merhabalar", "mrb"));

		// urun olusturma
		CellPhone cellPhone = new CellPhone(1, "Asus", "Zenphone 3", 16, "3GB");
		ArrayList<CellPhone> cellPhoneList = new ArrayList<CellPhone>();
		cellPhoneList.add(cellPhone);
		cellPhoneList.add(new CellPhone(2, "Apple", "Iphone 7", 64, "2GB"));
		System.out.println("CellPhone:" + cellPhoneList.get(0));

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(620, 620);
		this.setVisible(true);
		this.setResizable(false);
		this.setLayout(null);
		this.setTitle("Chatbot");

		inputBox.setLocation(5, 540);
		inputBox.setSize(590, 30);
		inputBox.requestFocusInWindow();


		chatArea.setLocation(20, 5);
		chatArea.setSize(560, 510);
		chatArea.setEditable(false);

		this.add(inputBox);
		this.add(chatArea);

		// etkilesim
		inputBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String uText = inputBox.getText();
				chatArea.append("You: " + uText + "\n");
				inputBox.setText("");

				if (uText.contains("selam")) {
					// chatArea.append("AI:" + "ooo selamlar" + "\n");
					decideRandom(greetingMessageList);

				} else if (uText.contains("urun sececegim")) {
					// urun secimi
					chatArea.append("AI: " + "Lutfen urunu seciniz:" + "\n");
					chatArea.append("AI: " + "1: Cep Telefonu" + "\n");
					chatArea.append("AI: " + "2: Televizyon" + "\n");

				} else if (uText.equals("1")) {
					chatArea.append("AI: " + "Cep Telefonu secildi..." + "\n");
					chatArea.append("AI: " + "Listedeki Telefonlar:" + "\n");
					for (CellPhone cellP : cellPhoneList) {
						chatArea.append("AI: " + cellP.getpModel() + "\n");
					}

				} else if (uText.equals("2")) {
					chatArea.append("AI: " + "Televizyon secildi..." + "\n");

				} else {
					decideRandom(exceptionMessageList);
				}
			}
		});

		// elemanlari JFrame'e ekle

	}

	private void decideRandom(ArrayList<String> messageList) {
		int decider = (int) (Math.random() * messageList.size());
		chatArea.append("AI: " + messageList.get(decider) + "\n");
	}

}
