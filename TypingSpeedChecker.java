import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TypingSpeedChecker extends JFrame implements ActionListener {
    private static final String[] TEST_TEXTS = {
        "The quick brown fox jumps over the lazy dog.",
        "Sphinx of black quartz, judge my vow.",
        "How vexingly quick daft zebras jump!",
        "Pack my box with five dozen liquor jugs.",
        "Waltz, bad nymph, for quick jigs vex."
    };

    private JLabel testTextLabel;
    private JTextField userInputField;
    private JButton startButton;
    private int currentTextIndex;
    private long startTime;

    public TypingSpeedChecker() {
        super("Typing Speed Checker");

        // Create GUI components
        testTextLabel = new JLabel();
        userInputField = new JTextField();
        startButton = new JButton("Start");

        // Add components to content pane
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(3, 1));
        contentPane.add(testTextLabel);
        contentPane.add(userInputField);
        contentPane.add(startButton);

        // Set event listeners
        userInputField.addActionListener(this);
        startButton.addActionListener(this);

        // Initialize state
        currentTextIndex = 0;
        updateTestText();

        // Set window properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 150);
        setVisible(true);
    }

    private void updateTestText() {
        // Set new test text and clear user input field
        testTextLabel.setText(TEST_TEXTS[currentTextIndex]);
        userInputField.setText("");
    }

    private void startTest() {
        // Start timer and update test text
        startTime = System.currentTimeMillis();
        updateTestText();
    }

    private void endTest() {
        // Calculate typing speed and display results
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        int wordCount = TEST_TEXTS[currentTextIndex].split("\\s+").length;
        double typingSpeed = (double) wordCount / ((double) elapsedTime / 1000.0) * 60.0;
        JOptionPane.showMessageDialog(this, String.format("Your typing speed: %.2f wpm", typingSpeed));

        // Reset state and update test text
        currentTextIndex = (currentTextIndex + 1) % TEST_TEXTS.length;
        updateTestText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == userInputField) {
            // Check if user input matches test text
            if (userInputField.getText().equals(TEST_TEXTS[currentTextIndex])) {
                endTest();
            }
        } else if (e.getSource() == startButton) {
            // Start new test
            startTest();
        }
    }

    public static void main(String[] args) {
        new TypingSpeedChecker();
    }
}