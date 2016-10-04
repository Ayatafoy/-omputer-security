import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class XOR extends JFrame {
    Dimension size = new Dimension(1750, 50);
    private JTextArea textArea1 = new JTextArea(50, 170);
    private JLabel text1 = new JLabel("Введите Ключ:           ");
    private JTextField textField1 = new JTextField();
    private JLabel text2 = new JLabel("Введите сообщение:");
    private JTextField textField2 = new JTextField();
    private JButton зашифроватьТекстButton = new JButton("Зашифровать / Расшифровать сообщение");

    public static String additionTwo(String s1, String s2) {
        int i = 0;
        String s3 = "";

        while(s1.length() != 6 || s2.length() != 6) {
            if(s1.length() != 6) {
                s1 = "0".concat(s1);
            }

            if(s2.length() != 6) {
                s2 = "0".concat(s2);
            }
        }

        for(; i < s1.length(); ++i) {
            int k1 = Character.getNumericValue(s1.charAt(s1.length() - i - 1));
            int k2 = Character.getNumericValue(s2.charAt(s2.length() - i - 1));
            if(k1 != k2) {
                s3 = Integer.toString(1).concat(s3);
            } else {
                s3 = Integer.toString(0).concat(s3);
            }
        }

        return s3;
    }

    public static int binaryDecoding(String s) {
        int i = 0;

        double x;
        for(x = 0.0D; i <= s.length() - 1; ++i) {
            char k = s.charAt(s.length() - i - 1);
            x += (double)Character.getNumericValue(k) * Math.pow(2.0D, (double)i);
        }

        return (int)x;
    }

    public static String binarhyCoding(int x) {
        String bynaryCod = "";

        do {
            int x1 = x % 2;
            x /= 2;
            bynaryCod = Integer.toString(x1).concat(bynaryCod);
        } while(x != 1 && x != 0);

        return Integer.toString(x).concat(bynaryCod);
    }

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
                String kod1 = binarhyCoding(i);
                this.textArea1.append("Двоичное значение позиции:   " + kod1 + "\n");
                this.textArea1.append("Текущий символ ключа:   " + Key.charAt(k % Key.length()) + "\n");
                keyValue = ab.indexOf(Key.charAt(k % Key.length()));
                this.textArea1.append("keyValue:   " + keyValue + "\n");
                String kod2 = binarhyCoding(keyValue);
                this.textArea1.append("Двоичное значение позиции в алфавите текущего символа из ключа: " + kod2 + "\n");
                String kod3 = additionTwo(kod1, kod2);
                this.textArea1.append("Двоичное значение суммы по модулю 2 первого и второго кодов:   " + kod3 + "\n");
                result = result + ab.charAt(binaryDecoding(kod3));
                this.textArea1.append("Продолжение результата:   " + result + "\n");
                k++;
            }
        }

        this.textArea1.append("\n" + "\n" + "Результат: " + result);
    }

    public XOR() {
        //http://www.quizful.net/post/swing-layout-managers
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
        p.add(new JScrollPane(textArea1));

        this.зашифроватьТекстButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    XOR.this.result();
                } catch (Exception var3) {
                    var3.printStackTrace();
                }

            }
        });
    }

    public static void main(String[] args) throws Exception {
        XOR app = new XOR();
        app.setVisible(true);
    }
}