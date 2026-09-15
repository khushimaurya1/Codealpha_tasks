import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class AIChatbot {

    // FAQ / training data
    private static final Map<String, String> faq = new HashMap<>();

    // Initialize chatbot knowledge
    public static void trainBot() {

        faq.put("hello",
                "Hello! How can I help you today?");

        faq.put("hi",
                "Hi! Nice to meet you.");

        faq.put("hey",
                "Hey! What can I do for you?");

        faq.put("how are you",
                "I'm doing great! Thanks for asking.");

        faq.put("your name",
                "My name is JavaBot. I am a Java-based AI chatbot.");

        faq.put("who are you",
                "I am JavaBot, a simple rule-based AI chatbot.");

        faq.put("what is java",
                "Java is a high-level, object-oriented programming language.");

        faq.put("what is oop",
                "OOP stands for Object-Oriented Programming. "
                + "Its main concepts include Encapsulation, "
                + "Inheritance, Polymorphism and Abstraction.");

        faq.put("what is nlp",
                "NLP stands for Natural Language Processing. "
                + "It allows computers to process and understand human language.");

        faq.put("what is ai",
                "AI stands for Artificial Intelligence. "
                + "It enables machines to perform tasks that normally require human intelligence.");

        faq.put("what is machine learning",
                "Machine Learning is a branch of AI where computers learn "
                + "patterns from data and improve their predictions.");

        faq.put("thank you",
                "You're welcome! I'm always happy to help.");

        faq.put("thanks",
                "You're welcome!");

        faq.put("bye",
                "Goodbye! Have a great day.");
    }


    // NLP text preprocessing
    public static String preprocess(String input) {

        // Convert to lowercase
        input = input.toLowerCase();

        // Remove punctuation
        input = input.replaceAll("[^a-zA-Z0-9\\s]", "");

        // Remove extra spaces
        input = input.trim().replaceAll("\\s+", " ");

        return input;
    }


    // Calculate similarity between input and keyword
    public static int calculateSimilarity(
            String input,
            String keyword) {

        String[] inputWords = input.split(" ");
        String[] keywordWords = keyword.split(" ");

        int score = 0;

        for (String inputWord : inputWords) {

            for (String keywordWord : keywordWords) {

                if (inputWord.equals(keywordWord)) {
                    score++;
                }
            }
        }

        return score;
    }


    // Generate chatbot response
    public static String getResponse(String userInput) {

        String input = preprocess(userInput);

        if (input.isEmpty()) {
            return "Please type something so I can help you.";
        }


        // Check exact FAQ match
        if (faq.containsKey(input)) {
            return faq.get(input);
        }


        // Special intent: Date
        if (input.contains("date") ||
                input.contains("today")) {

            LocalDate date = LocalDate.now();

            return "Today's date is "
                    + date.format(
                    DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }


        // Special intent: Time
        if (input.contains("time")) {

            LocalTime time = LocalTime.now();

            return "The current time is "
                    + time.format(
                    DateTimeFormatter.ofPattern("HH:mm:ss"));
        }


        // Find best matching FAQ
        String bestMatch = null;
        int highestScore = 0;

        for (String keyword : faq.keySet()) {

            int score =
                    calculateSimilarity(
                            input,
                            keyword);

            if (score > highestScore) {

                highestScore = score;
                bestMatch = keyword;
            }
        }


        // Return best matching response
        if (highestScore > 0) {

            return faq.get(bestMatch);
        }


        // Additional rule-based responses

        if (input.contains("help")) {

            return "Sure! I can answer questions about "
                    + "Java, OOP, AI, NLP, Machine Learning, "
                    + "and basic programming.";
        }


        if (input.contains("programming")) {

            return "Programming is the process of writing "
                    + "instructions that computers can execute.";
        }


        // Unknown question
        return "I'm sorry, I don't understand that yet. "
                + "Try asking me about Java, OOP, AI, NLP, "
                + "Machine Learning, or programming.";
    }


    // Create GUI
    public static void createGUI() {

        JFrame frame =
                new JFrame("Java AI Chatbot");

        frame.setSize(650, 550);

        frame.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE);

        frame.setLocationRelativeTo(null);


        // Main panel
        JPanel mainPanel =
                new JPanel(new BorderLayout());

        mainPanel.setBackground(
                new Color(240, 240, 240));


        // Title
        JLabel title =
                new JLabel(
                        "JavaBot - AI Chatbot",
                        SwingConstants.CENTER);

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24));

        title.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 10, 15, 10));


        // Chat area
        JTextArea chatArea =
                new JTextArea();

        chatArea.setEditable(false);

        chatArea.setLineWrap(true);

        chatArea.setWrapStyleWord(true);

        chatArea.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16));

        chatArea.setText(
                "JavaBot: Hello! I'm JavaBot. "
                + "How can I help you?\n\n");


        JScrollPane scrollPane =
                new JScrollPane(chatArea);


        // Input field
        JTextField inputField =
                new JTextField();

        inputField.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16));


        // Send button
        JButton sendButton =
                new JButton("Send");

        sendButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14));


        // Bottom panel
        JPanel bottomPanel =
                new JPanel(
                        new BorderLayout(
                                10, 10));

        bottomPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 10, 10, 10));

        bottomPanel.add(
                inputField,
                BorderLayout.CENTER);

        bottomPanel.add(
                sendButton,
                BorderLayout.EAST);


        // Send message function
        Runnable sendMessage = () -> {

            String userMessage =
                    inputField.getText();

            if (userMessage.trim().isEmpty()) {
                return;
            }

            // Display user message
            chatArea.append(
                    "You: "
                    + userMessage
                    + "\n");

            // Generate response
            String response =
                    getResponse(userMessage);

            // Display bot response
            chatArea.append(
                    "JavaBot: "
                    + response
                    + "\n\n");

            // Clear input
            inputField.setText("");

            // Scroll to bottom
            chatArea.setCaretPosition(
                    chatArea.getDocument()
                            .getLength());
        };


        // Button click
        sendButton.addActionListener(
                e -> sendMessage.run());


        // Enter key
        inputField.addActionListener(
                e -> sendMessage.run());


        // Add components
        mainPanel.add(
                title,
                BorderLayout.NORTH);

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER);

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH);


        frame.add(mainPanel);

        frame.setVisible(true);
    }


    // Main method
    public static void main(String[] args) {

        // Train chatbot
        trainBot();

        // Start GUI
        SwingUtilities.invokeLater(
                AIChatbot::createGUI);
    }
}