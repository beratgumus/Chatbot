import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BotHandler extends JFrame {
    private JTextField inputBox = new JTextField();
    private JTextArea chatArea = new JTextArea();
    private HashMap<String, String[]> botAnswers;
    private String state;

    public BotHandler() {
        //TODO: (Maybe) Store all messages in a database. This is ugly. (fetch as JSON?)
        botAnswers = new HashMap<>();
        String messages[] = new String[]{"Hello.", "Hi.", "Hey there."};
        botAnswers.put("greeting", messages);
        messages = new String[]{"Sorry. I can't understand your message.", "Ugh! I can't understand this.", "I can't understand this. May you repeat?"};
        botAnswers.put("unknown", messages);

        state = "";

        // urun olusturma
//		CellPhone cellPhone = new CellPhone(1, "Asus", "Zenphone 3", 16, "3GB");
//		ArrayList<CellPhone> cellPhoneList = new ArrayList<CellPhone>();
//		cellPhoneList.add(cellPhone);
//		cellPhoneList.add(new CellPhone(2, "Apple", "Iphone 7", 64, "2GB"));
//		System.out.println("CellPhone:" + cellPhoneList.get(0));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(620, 620);
        this.setVisible(true);
        this.setResizable(true);
        this.setLayout(null);
        this.setTitle("Chatbot");

        inputBox.setLocation(5, 540);
        inputBox.setSize(590, 30);
        inputBox.requestFocusInWindow();

        chatArea.setLocation(20, 5);
        chatArea.setSize(560, 510);
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);

        // elemanlari JFrame'e ekle
        this.add(inputBox);
        this.add(chatArea);

    	// etkilesim
		inputBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String uText = inputBox.getText();
				chatArea.append("You: " + uText + "\n");
                inputBox.setText("");

				if (uText.contains("hello") || uText.contains("hi") || uText.contains("hey there")) {
					// chatArea.append("AI:" + "ooo selamlar");
					decideRandom("greeting");
                } else if (uText.contains("buy something") || uText.contains("choose product") || uText.contains("buy product")) {

					answer("Please select a produc category:");
					answer("1: Consumer Electronics");
					answer("2: Major Appliances");
                    answer("3: Vehicles");
                    state = "select_category";

                } else if (state.equals("select_category")) {
                    //TODO: maybe better solution with Tree or HashMap? We have to keep track of states somehow.
                    if (uText.equals("1")) {
                        answer("Please select a product type:");
                        // TODO: write a loop that prints product types of selected category
                        answer("1: Phone");
                        answer("2: Laptop");
                        answer("3: Camera");
                    } else if (uText.equals("1")){

                    } else if (uText.equals("1")){

                    } else {
                        answer("Thats not a valid selection.");
                        state = ""; //reset state;
                    }

                    state = "select_product_type"; //set next state
                } else if (state.equals("select_product_type")) {
                    // choose a product type from selected category
                    if (uText.equals("1")) {
                        answer("Please select a phone model");
                        answer(" TODO: I should show you list of products but its not implemented yet. Sorry.");
                    } else if (uText.equals("2")){

                    } else if (uText.equals("3")){

                    } else {
                        answer("Thats not a valid selection.");
                        state = ""; //reset state;
                    }

                    state = "select_product"; //set next state
				} else if (state.equals("select_product")) {
                    // TODO: show product info
                    answer(" TODO: I should show you information about product you have choosen but its not implemented yet. Sorry.");

                    state = ""; //end of product selection cycle
                } else {
					decideRandom("unknown");
				}
			}
		});



	}
	
	private void answer(String message){
        chatArea.append("AI: " + message + "\n");
    }

    private void decideRandom(String key) {
        String messageList[] = botAnswers.get(key);
        int randomIndex = ThreadLocalRandom.current().nextInt(0, messageList.length);
        answer(messageList[randomIndex]);
    }

}
