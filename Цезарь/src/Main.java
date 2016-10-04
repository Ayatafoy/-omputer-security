import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Main extends JFrame {
    Dimension size = new Dimension(1750, 50);
    private JTextArea textArea1 = new JTextArea(50, 170);
    private JLabel text1 = new JLabel("Введите Ключ:           ");
    private JTextField textField1 = new JTextField();
    private JLabel text2 = new JLabel("Введите сообщение:");
    private JTextField textField2 = new JTextField();
    private JButton зашифроватьТекстButton = new JButton("Зашифровать сообщение");
    private JButton расшифроватьТекстButton = new JButton("Расшифровать сообщение");

    public void result() throws Exception {
        this.textArea1.setText(null);
        String result = "", message = this.textField2.getText().toLowerCase(),
                ab = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя0123456789,.;:@-=+<>[]{}/|!?%* ",
                Key = this.textField1.getText().toLowerCase();
        int keyValue, k = 0;
        while(k != message.length()) {
            char c1 = message.charAt(k);
            this.textArea1.append("Символ из исходного сообщения:   " + c1 + "\n");
            int i = ab.indexOf(c1);
            if(i == -1) {
                result = result + c1;
                k++;
            } else {
                this.textArea1.append("Позиция этого символа в алфавите:   " + i + "\n");
                this.textArea1.append("Текущий символ ключа:   " + Key.charAt(k % Key.length()) + "\n");
                keyValue = ab.indexOf(Key.charAt(k % Key.length()));
                this.textArea1.append("Позиция текущего символа ключа в алфавите:   " + keyValue + "\n");
                this.textArea1.append("Сумма по модулю алфавита символа ключа и сообщения:   " +
                        "" + (i + keyValue) % 64 + "\n");
                result = result + ab.charAt((i + keyValue) % 64);
                this.textArea1.append("Продолжение результата:   " + result + "\n");
                k++;
            }
        }

        this.textArea1.append("\n" + "\n" + "Результат: " + result);
    }

    public void result1() throws Exception {
        this.textArea1.setText(null);
        String result = "", message = this.textField2.getText().toLowerCase(),
                ab = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя0123456789,.;:@-=+<>[]{}/|!?%* ",
                Key = this.textField1.getText().toLowerCase();
        int keyValue, k = 0, x;
        while(k != message.length()) {
            char c1 = message.charAt(k);
            this.textArea1.append("Символ криптограммы:   " + c1 + "\n");
            int i = ab.indexOf(c1);
            if(i == -1) {
                result = result + c1;
                k++;
            } else {
                this.textArea1.append("Позиция этого символа в алфавите:   " + i + "\n");
                this.textArea1.append("Текущий символ ключа:   " + Key.charAt(k % Key.length()) + "\n");
                keyValue = ab.indexOf(Key.charAt(k % Key.length()));
                this.textArea1.append("Позиция текущего символа ключа в алфавите:   " + keyValue + "\n");
                x = (i - keyValue);
                if (x < 0)
                    x += 64;
                this.textArea1.append("Позиция символа исходного сообщения:   " +
                        "" + x + "\n");
                result = result + ab.charAt(x);
                this.textArea1.append("Продолжение результата:   " + result + "\n");
                k++;
            }
        }

        this.textArea1.append("\n" + "\n" + "Результат: " + result);
    }

    public Main() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1032);
        setLocation(0, 0);
        setVisible(true);
        JPanel p = new JPanel();
        add(p);
        p.setLayout(new FlowLayout());
        p.add(text2);
        p.add(textField2);
        p.add(text1);
        p.add(textField1);
        this.textField1.setPreferredSize(size);
        this.textField2.setPreferredSize(size);
        p.add(зашифроватьТекстButton);
        p.add(расшифроватьТекстButton);
        p.add(new JScrollPane(textArea1));

        this.зашифроватьТекстButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.this.result();
                } catch (Exception var3) {
                    var3.printStackTrace();
                }

            }
        });

        this.расшифроватьТекстButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.this.result1();
                } catch (Exception var3) {
                    var3.printStackTrace();
                }

            }
        });
    }

    public static void main(String[] args) throws Exception {
        Main app = new Main();
        app.setVisible(true);
    }
}