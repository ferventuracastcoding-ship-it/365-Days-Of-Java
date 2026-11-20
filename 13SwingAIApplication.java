// Code to practice syntax
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class SwingAIApplication extends JFrame {

    // Main chat display.
    private JTextArea chatArea;

    // User input box.
    private JTextArea inputArea;

    // Status label.
    private JLabel statusLabel;

    // Stores conversation history.
    private final List<String> conversation = new ArrayList<>();

    public SwingAIApplication() {

        setTitle("Java Swing AI Research Assistant");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        buildInterface();
    }

    private void buildInterface() {

        // Main window.
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Sidebar.
        JPanel sidebar = createSidebar();

        // Chat panel.
        JPanel chatPanel = createChatPanel();

        mainPanel.add(sidebar, BorderLayout.WEST);
        mainPanel.add(chatPanel, BorderLayout.CENTER);

        add(mainPanel);

        addWelcomeMessage();
    }

    private JPanel createSidebar() {

        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(210, 0));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel title = new JLabel("AI CONTROL CENTER");

        title.setFont(new Font(
                "SansSerif",
                Font.BOLD,
                18
        ));

        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        sidebar.add(title);
        sidebar.add(Box.createVerticalStrut(20));

        JButton newChatButton =
                createButton("New Chat");

        JButton codeButton =
                createButton("Analyze Code");

        JButton summarizeButton =
                createButton("Summarize Text");

        JButton explainButton =
                createButton("Explain Java");

        JButton clearButton =
                createButton("Clear Chat");

        sidebar.add(newChatButton);
        sidebar.add(Box.createVerticalStrut(8));

        sidebar.add(codeButton);
        sidebar.add(Box.createVerticalStrut(8));

        sidebar.add(summarizeButton);
        sidebar.add(Box.createVerticalStrut(8));

        sidebar.add(explainButton);
        sidebar.add(Box.createVerticalStrut(8));

        sidebar.add(clearButton);

        sidebar.add(Box.createVerticalGlue());

        statusLabel = new JLabel("AI Status: ONLINE");

        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        sidebar.add(statusLabel);

        newChatButton.addActionListener(e -> newChat());

        codeButton.addActionListener(e -> analyzeCode());

        summarizeButton.addActionListener(e -> summarizeText());

        explainButton.addActionListener(e -> explainJava());

        clearButton.addActionListener(e -> clearChat());

        return sidebar;
    }

    private JPanel createChatPanel() {

        JPanel panel = new JPanel(new BorderLayout(10, 10));

        JLabel header =
                new JLabel("🤖 Java AI Assistant");

        header.setFont(new Font(
                "SansSerif",
                Font.BOLD,
                24
        ));

        panel.add(header, BorderLayout.NORTH);

        chatArea = new JTextArea();

        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);

        chatArea.setFont(new Font(
                "Monospaced",
                Font.PLAIN,
                14
        ));

        JScrollPane chatScroll =
                new JScrollPane(chatArea);

        panel.add(
                chatScroll,
                BorderLayout.CENTER
        );

        JPanel inputPanel =
                new JPanel(new BorderLayout(8, 8));

        inputArea = new JTextArea(4, 40);

        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);

        inputArea.setFont(new Font(
                "SansSerif",
                Font.PLAIN,
                15
        ));

        JButton sendButton =
                new JButton("Send");

        inputPanel.add(
                new JScrollPane(inputArea),
                BorderLayout.CENTER
        );

        inputPanel.add(
                sendButton,
                BorderLayout.EAST
        );

        panel.add(
                inputPanel,
                BorderLayout.SOUTH
        );

        sendButton.addActionListener(
                this::sendMessage
        );

        return panel;
    }

    private JButton createButton(String text) {

        JButton button = new JButton(text);

        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        40
                )
        );

        return button;
    }

    private void addWelcomeMessage() {

        addAIMessage(
                "Welcome to the Java AI Assistant.\n\n"
                + "I can help with:\n"
                + "• Java programming\n"
                + "• Code analysis\n"
                + "• Text analysis\n"
                + "• Basic explanations\n"
                + "• Algorithm concepts\n\n"
                + "Type a question below."
        );
    }

    private void sendMessage(ActionEvent event) {

        String input =
                inputArea.getText().trim();

        if (input.isEmpty()) {
            return;
        }

        addUserMessage(input);

        inputArea.setText("");

        statusLabel.setText("AI Status: THINKING...");

        // Prevent blocking the Swing interface.
        SwingWorker<String, Void> worker =
                new SwingWorker<>() {

                    @Override
                    protected String doInBackground() {

                        return generateResponse(input);
                    }

                    @Override
                    protected void done() {

                        try {

                            addAIMessage(get());

                            statusLabel.setText(
                                    "AI Status: ONLINE"
                            );

                        } catch (Exception e) {

                            addAIMessage(
                                    "AI error: "
                                    + e.getMessage()
                            );

                            statusLabel.setText(
                                    "AI Status: ERROR"
                            );
                        }
                    }
                };

        worker.execute();
    }

    private String generateResponse(String input) {

        String text =
                input.toLowerCase();

        if (text.contains("hello")
                || text.contains("hi")) {

            return "Hello! I'm your Java Swing AI assistant.";
        }

        if (text.contains("java")) {

            return "Java is an object-oriented language "
                    + "commonly used for desktop applications, "
                    + "backend systems, Android development, "
                    + "and enterprise software.";
        }

        if (text.contains("swing")) {

            return "Swing is Java's classic desktop GUI toolkit. "
                    + "Important components include JFrame, JPanel, "
                    + "JButton, JTextArea, JTable, JList, and JMenu.";
        }

        if (text.contains("arraylist")) {

            return "ArrayList is a resizable Java collection. "
                    + "It provides indexed access and is useful "
                    + "when the number of elements can change.";
        }

        if (text.contains("binary search")) {

            return "Binary search repeatedly divides a sorted "
                    + "search range in half. Its time complexity "
                    + "is O(log n).";
        }

        if (text.contains("hash map")
                || text.contains("hashmap")) {

            return "HashMap stores key-value pairs. Average "
                    + "lookup, insertion, and deletion are O(1), "
                    + "assuming good hash distribution.";
        }

        if (text.contains("bug")
                || text.contains("error")) {

            return "I can help debug Java code. Paste the code "
                    + "and identify the error message or describe "
                    + "what behavior you expected.";
        }

        if (text.contains("algorithm")) {

            return "An algorithm is a step-by-step procedure "
                    + "for solving a problem. Common examples "
                    + "include binary search, merge sort, BFS, "
                    + "DFS, and dynamic programming.";
        }

        return "I understand your question as: \""
                + input
                + "\"\n\n"
                + "This local AI engine uses rule-based "
                + "analysis. You can expand generateResponse() "
                + "with your own AI models or an external API.";
    }

    private void addUserMessage(String message) {

        String formatted =
                "\nYOU:\n"
                + message
                + "\n";

        chatArea.append(formatted);

        conversation.add(
                "USER: " + message
        );
    }

    private void addAIMessage(String message) {

        String formatted =
                "\nAI:\n"
                + message
                + "\n"
                + "----------------------------------------\n";

        chatArea.append(formatted);

        conversation.add(
                "AI: " + message
        );

        chatArea.setCaretPosition(
                chatArea.getDocument().getLength()
        );
    }

    private void newChat() {

        chatArea.setText("");

        conversation.clear();

        addWelcomeMessage();
    }

    private void clearChat() {

        chatArea.setText("");

        conversation.clear();
    }

    private void analyzeCode() {

        String code =
                JOptionPane.showInputDialog(
                        this,
                        "Paste Java code:"
                );

        if (code == null
                || code.trim().isEmpty()) {

            return;
        }

        StringBuilder report =
                new StringBuilder();

        report.append(
                "JAVA CODE ANALYSIS\n\n"
        );

        if (code.contains("==")
                && code.contains("String")) {

            report.append(
                    "⚠ Possible String comparison issue.\n"
            );

            report.append(
                    "Use .equals() when comparing String values.\n\n"
            );
        }

        if (code.contains("System.out.println")) {

            report.append(
                    "ℹ Console output detected.\n"
            );

            report.append(
                    "Consider a logging framework in production applications.\n\n"
            );
        }

        if (code.contains("Scanner")) {

            report.append(
                    "ℹ Scanner detected.\n"
            );

            report.append(
                    "Make sure the resource lifecycle is handled appropriately.\n\n"
            );
        }

        if (!code.contains("try")
                && code.contains("parseInt")) {

            report.append(
                    "⚠ Integer parsing may throw NumberFormatException.\n\n"
            );
        }

        if (report.toString().equals(
                "JAVA CODE ANALYSIS\n\n")) {

            report.append(
                    "No basic patterns detected.\n"
            );

            report.append(
                    "This does not guarantee the code is bug-free."
            );
        }

        addAIMessage(
                report.toString()
        );
    }

    private void summarizeText() {

        String text =
                JOptionPane.showInputDialog(
                        this,
                        "Enter text to summarize:"
                );

        if (text == null
                || text.trim().isEmpty()) {

            return;
        }

        String[] words =
                text.trim().split("\\s+");

        int limit =
                Math.min(words.length, 30);

        StringBuilder summary =
                new StringBuilder();

        for (int i = 0; i < limit; i++) {

            summary.append(
                    words[i]
            ).append(" ");
        }

        addAIMessage(
                "TEXT SUMMARY\n\n"
                + summary.toString().trim()
                + (words.length > 30
                ? "..."
                : "")
        );
    }

    private void explainJava() {

        String topic =
                JOptionPane.showInputDialog(
                        this,
                        "Java topic:"
                );

        if (topic == null
                || topic.trim().isEmpty()) {

            return;
        }

        addAIMessage(
                "JAVA EXPLANATION\n\n"
                + topic
                + " is a Java programming concept. "
                + "For a more advanced version, connect "
                + "this interface to an actual language model "
                + "API and return generated explanations."
        );
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> {

                    SwingAIApplication app =
                            new SwingAIApplication();

                    app.setVisible(true);
                }
        );
    }
}
