import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class OnlineQuizSystem extends JFrame implements ActionListener {

    // Quiz data
    String[][] questions = {
            {"Java is a ___ language?", "Programming", "Markup", "Scripting", "Styling", "Programming"},
            {"Which keyword is used to inherit a class?", "this", "super", "extends", "implements", "extends"},
            {"Which company developed Java?", "Google", "Microsoft", "Sun Microsystems", "Apple", "Sun Microsystems"},
            {"Which is not a Java feature?", "Object-Oriented", "Platform Independent", "Use of pointers", "Secure", "Use of pointers"},
            {"JVM stands for?", "Java Virtual Machine", "Java Visual Model", "Joint Virtual Machine", "None", "Java Virtual Machine"}
    };

    int index = 0;
    int score = 0;
    int timeLeft = 15;
    javax.swing.Timer timer;


    // UI components
    JLabel title, questionLabel, timerLabel;
    JRadioButton opt1, opt2, opt3, opt4;
    ButtonGroup options;
    JButton nextBtn, startBtn;

    JPanel loginPanel, quizPanel;

    public OnlineQuizSystem() {
        setTitle("Online Quiz System");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        showLoginScreen();
    }

    // ---------------- LOGIN SCREEN ----------------
    void showLoginScreen() {
        loginPanel = new JPanel();
        loginPanel.setBackground(new Color(45, 52, 54));
        loginPanel.setLayout(null);

        JLabel heading = new JLabel("ONLINE QUIZ SYSTEM");
        heading.setBounds(200, 50, 400, 40);
        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel nameLabel = new JLabel("Enter Your Name:");
        nameLabel.setBounds(200, 140, 200, 30);
        nameLabel.setForeground(Color.WHITE);

        JTextField nameField = new JTextField();
        nameField.setBounds(200, 180, 250, 30);

        startBtn = new JButton("Start Quiz");
        startBtn.setBounds(250, 240, 150, 40);
        startBtn.setBackground(new Color(0, 184, 148));
        startBtn.setForeground(Color.WHITE);

        startBtn.addActionListener(e -> {
            if (!nameField.getText().trim().isEmpty()) {
                remove(loginPanel);
                showQuizScreen();
            } else {
                JOptionPane.showMessageDialog(this, "Please enter your name");
            }
        });

        loginPanel.add(heading);
        loginPanel.add(nameLabel);
        loginPanel.add(nameField);
        loginPanel.add(startBtn);

        add(loginPanel);
        setVisible(true);
    }

    // ---------------- QUIZ SCREEN ----------------
    void showQuizScreen() {
        quizPanel = new JPanel();
        quizPanel.setLayout(null);
        quizPanel.setBackground(Color.WHITE);

        title = new JLabel("Online Quiz");
        title.setBounds(20, 10, 300, 30);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        timerLabel = new JLabel("Time: 15");
        timerLabel.setBounds(580, 10, 100, 30);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        timerLabel.setForeground(Color.RED);

        questionLabel = new JLabel();
        questionLabel.setBounds(20, 60, 650, 30);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));

        opt1 = new JRadioButton();
        opt2 = new JRadioButton();
        opt3 = new JRadioButton();
        opt4 = new JRadioButton();

        opt1.setBounds(50, 120, 600, 30);
        opt2.setBounds(50, 160, 600, 30);
        opt3.setBounds(50, 200, 600, 30);
        opt4.setBounds(50, 240, 600, 30);

        options = new ButtonGroup();
        options.add(opt1);
        options.add(opt2);
        options.add(opt3);
        options.add(opt4);

        nextBtn = new JButton("Next");
        nextBtn.setBounds(280, 300, 120, 40);
        nextBtn.setBackground(new Color(9, 132, 227));
        nextBtn.setForeground(Color.WHITE);
        nextBtn.addActionListener(this);

        quizPanel.add(title);
        quizPanel.add(timerLabel);
        quizPanel.add(questionLabel);
        quizPanel.add(opt1);
        quizPanel.add(opt2);
        quizPanel.add(opt3);
        quizPanel.add(opt4);
        quizPanel.add(nextBtn);

        add(quizPanel);
        loadQuestion();
        startTimer();
        setVisible(true);
    }

    // ---------------- LOAD QUESTION ----------------
    void loadQuestion() {
        if (index < questions.length) {
            questionLabel.setText((index + 1) + ". " + questions[index][0]);
            opt1.setText(questions[index][1]);
            opt2.setText(questions[index][2]);
            opt3.setText(questions[index][3]);
            opt4.setText(questions[index][4]);
            options.clearSelection();
            timeLeft = 15;
        } else {
            showResult();
        }
    }

    // ---------------- TIMER ----------------
    void startTimer() {
        timer = new javax.swing.Timer(1000, e -> {
            timerLabel.setText("Time: " + timeLeft);
            timeLeft--;

            if (timeLeft < 0) {
                checkAnswer();
                index++;
                loadQuestion();
            }
        });
        timer.start();
    }

    // ---------------- CHECK ANSWER ----------------
    void checkAnswer() {
        String correct = questions[index][5];
        if (opt1.isSelected() && opt1.getText().equals(correct)) score++;
        if (opt2.isSelected() && opt2.getText().equals(correct)) score++;
        if (opt3.isSelected() && opt3.getText().equals(correct)) score++;
        if (opt4.isSelected() && opt4.getText().equals(correct)) score++;
    }

    // ---------------- BUTTON ACTION ----------------
    public void actionPerformed(ActionEvent e) {
        checkAnswer();
        index++;
        loadQuestion();
    }

    // ---------------- RESULT ----------------
    void showResult() {
        timer.stop();
        JOptionPane.showMessageDialog(this,
                "Quiz Finished!\nYour Score: " + score + " / " + questions.length,
                "Result",
                JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    // ---------------- MAIN ----------------
    public static void main(String[] args) {
        new OnlineQuizSystem();
    }
}