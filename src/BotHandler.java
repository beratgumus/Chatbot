import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.*;


public class BotHandler extends JFrame {
    private JTextField inputBox = new JTextField();
    private JTextArea chatArea = new JTextArea();
    private JScrollPane scrollPane;

    private List<Product> products;
    private HashMap<String, String[]> botAnswers;
    private TreeNode root;
    private TreeNode currentNode;
    private String[] options;
    private String state;

    private static final String adminName = "admin";
    private static final String adminPass = "pw123";

    public BotHandler() {
        //TODO: (Maybe) Store all messages in a database. This is ugly. (fetch as JSON?)
        botAnswers = new HashMap<>();
        String messages[] = new String[]{"Hello.", "Hi.", "Hey there."};
        botAnswers.put("greeting", messages);
        messages = new String[]{"I'm fine, thank you, and you?", "Great! You?", "Pretty good, and you?"};
        botAnswers.put("ask_about", messages);
        messages = new String[]{"Sorry. I can't understand your message.", "Ugh! I can't understand this.", "I can't understand this. May you repeat?"};
        botAnswers.put("unknown", messages);
        state = "";

        List<Product> tempProducts = new ArrayList<>();

        tempProducts.add(new Laptop("10", "Lenovo", "G5080A", 1450.0, 26.0, 51.0, 8.0, 2560, 15.6, 500, 4, "i3 4030u", "Windows 10") );
        tempProducts.add(new Laptop("11", "Lenovo", "G5080B", 1600.0, 26.0, 51.0, 8.0, 2560, 15.6, 500, 4, "i3 4035u", "Windows 10") );
        tempProducts.add(new Laptop("12", "Lenovo", "G5080C", 1799.0, 26.0, 51.0, 8.0, 2560, 15.6, 750, 4, "i5 4060u", "Windows 10") );

        tempProducts.add(new MobilePhone("20", "MyPhone", "Vision", 800.0, 6.25, 1.01, 2.23, 98, 5.2, 16, 13, "Android 7.0", 2) );
        tempProducts.add(new MobilePhone("21", "Apple", "Iphone X 16GB", 372253.0, 7.12, 5.23, 2.22, 111, 5.325, 16, 13, "iOS 11", 2) );
        tempProducts.add(new MobilePhone("21", "Apple", "Iphone X 32GB", 462451.0, 7.12, 5.23, 2.22, 111, 5.325, 32, 13, "iOS 11", 2) );

        root = new TreeNode(tempProducts);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(620, 620);

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

        scrollPane = new JScrollPane(chatArea);
        scrollPane.setAutoscrolls(true);
        scrollPane.setSize(560, 510);
        scrollPane.setLocation(20, 5);
        scrollPane.setAutoscrolls(true);


    	// etkilesim
		inputBox.addActionListener(new ActionListener() {

            @Override
			public void actionPerformed(ActionEvent e) {
				String uText = inputBox.getText();

                if (uText.length() < 1){
                    //do not trigger answering logic if message is empty
                    return;
                }
                if (!state.equals("admin_login"))
				    chatArea.append("You: " + uText + "\n");
                else {
                    // lets dont show password
                    chatArea.append("You: " + String.join("", Collections.nCopies(uText.length(), "*")) + "\n");
                }
                inputBox.setText("");

                if (uText.equals(adminName)){
                    //admin login logic starts here
                    answer("Enter password : ");
                    state = "admin_login";
                } else if ( state.equals("admin_login")) {
                    if (uText.equals(adminPass)){
                        answer("Welcome "+adminName+". What kind of product do you want to add?");
                        answer("1: Mobile Phone");
                        answer("2: Laptop");
                        state = "admin_choose_product";
                    } else {
                        answer("Wrong password!");
                        state = "";
                    }

                } else if (state.equals("admin_choose_product")){
                    if ( !uText.matches("[0-9]+") || Integer.parseInt(uText) > 2 ) {
                        answer("Thats not a valid selection.");
                        state = ""; //reset state;
                        return;
                    }
                    int selection = Integer.parseInt(uText);
                    if (selection == 1){
                        answer("Opening new form...");
                        AddPhone.runApp();
                    }
                } else if (uText.contains("hello") || uText.contains("hi") || uText.contains("hey there")) {
                    decideRandom("greeting");
                } else if((uText.contains("how") && uText.contains("you") ) || (uText.contains("what") && uText.contains("up"))){
                    decideRandom("ask_about");
                } else if (uText.contains("buy something") || uText.contains("product") ) {

                    currentNode = root;
                    printOptions(currentNode);
                    state = "select_category";

                } else if (state.equals("select_category")) {
                    if ( !uText.matches("[0-9]+")) {
                        answer("Thats not a valid selection.");
                        state = ""; //reset state;
                        return;
                    }

                    int selectedIndex = Integer.parseInt(uText) - 1; //user selection starts from 1, arrays starts from 0
                    currentNode = currentNode.getNextNode(options[selectedIndex]);
                    printOptions(currentNode);
                    state = "select_product_type"; //set next state
                } else if (state.equals("select_product_type")) {
                    if ( !uText.matches("[0-9]+") || Integer.parseInt(uText) >= options.length) {
                        answer("Thats not a valid selection.");
                        state = ""; //reset state;
                        return;
                    }

                    int selectedIndex = Integer.parseInt(uText) - 1; //user selection starts from 1, arrays starts from 0
                    currentNode = currentNode.getNextNode(options[selectedIndex]);
                    products = currentNode.getProductList();
                    for (int i = 0; i < products.size(); i++){
                        int j = i + 1; // visual only!
                        answer(j+ ": "+ products.get(i).toShortString() );
                    }
                    state = "select_product"; //set next state
				} else if (state.equals("select_product")) {
                    if ( !uText.matches("[0-9]+") || Integer.parseInt(uText) >= options.length) {
                        answer("Thats not a valid selection.");
                        state = ""; //reset state;
                        return;
                    }
                    int selectedIndex = Integer.parseInt(uText) - 1; //user selection starts from 1, arrays starts from 0
                    String selection = options[selectedIndex];

                    // TODO: show product info
                    answer(" TODO: I should show you information about product you have choosen but its not implemented yet. Sorry.");

                    state = ""; //end of product selection cycle
                } else {
					decideRandom("unknown");
				}
			}
		});

        // elemanlari JFrame'e ekle

//        this.add(chatArea);
        this.add(inputBox);
        this.add(scrollPane);

        this.setVisible(true);
	}
	
	private void answer(String message){
        chatArea.append("AI: " + message + "\n");
    }

    /**
     * Prints options in given node. If node is 
     * @param node
     */
    private void printOptions(TreeNode node){
        options = node.getAllKeys();
        answer("Please select a " + node.nodeType + ":");
        for (int i = 0; i < options.length; i++){
            int j = i + 1; // visual only!
            answer(j+ ": "+ options[i]);
        }
    }

    private void decideRandom(String key) {
        String messageList[] = botAnswers.get(key);
        int randomIndex = ThreadLocalRandom.current().nextInt(0, messageList.length);
        answer(messageList[randomIndex]);
    }

}
