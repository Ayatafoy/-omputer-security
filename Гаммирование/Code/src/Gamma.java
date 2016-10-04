import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gamma extends JFrame {
    Dimension size = new Dimension(1750, 50);
    private JTextArea textArea1 = new JTextArea(45, 170);
    private JLabel text0 = new JLabel("Введите сообщение:");
    private JTextField textField0 = new JTextField();
    private JLabel text1 = new JLabel("Введите A:                  ");
    private JTextField textField1 = new JTextField();
    private JLabel text2 = new JLabel("Введите C:                  ");
    private JTextField textField2 = new JTextField();
    private JLabel text3 = new JLabel("Введите T:                  ");
    private JTextField textField3 = new JTextField();
    private JButton зашифроватьТекстButton = new JButton("Зашифровать / Расшифровать сообщение");

    public static int tNum(int T, int A, int C) {
        return (A * T + C) % 64;
    }

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

    public void fOut() throws Exception {
        this.textArea1.setText(null);
        String result = "", message = this.textField0.getText().toLowerCase(),
                ab = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя0123456789,.;:@-=+<>[]{}/|!?%* ";
        Integer t = new Integer(this.textField3.getText());
        Integer a = new Integer(this.textField1.getText());
        Integer c = new Integer(this.textField2.getText());
        int k = 0;

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
                t = Integer.valueOf(tNum(t.intValue(), a.intValue(), c.intValue()));
                this.textArea1.append("T:   " + t + "\n");
                String kod2 = binarhyCoding(t.intValue());
                this.textArea1.append("Двоичное значение T:   " + kod2 + "\n");
                String kod3 = additionTwo(kod1, kod2);
                this.textArea1.append("Двоичное значение суммы по модулю 2 первого и второго кодов:   " + kod3 + "\n");
                result = result + ab.charAt(binaryDecoding(kod3));
                this.textArea1.append("Продолжение результата:   " + result + "\n");
                k++;
            }
        }
        this.textArea1.append("\n" + "\n" + "Результат: " + result);
    }

    public Gamma() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1032);
        setLocation(0, 0);
        setVisible(true);
        JPanel p = new JPanel();
        add(p);
        p.setLayout(new FlowLayout());
        p.add(text0);
        p.add(textField0);
        p.add(text1);
        p.add(textField1);
        p.add(text2);
        p.add(textField2);
        p.add(text3);
        p.add(textField3);
        this.textField0.setPreferredSize(size);
        this.textField1.setPreferredSize(size);
        this.textField2.setPreferredSize(size);
        this.textField3.setPreferredSize(size);
        p.add(зашифроватьТекстButton);
        p.add(new JScrollPane(textArea1));

        this.зашифроватьТекстButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Gamma.this.fOut();
                } catch (Exception var3) {
                    var3.printStackTrace();
                }

            }
        });
    }

    public static void main(String[] args) throws Exception {
        Gamma app = new Gamma();
        app.setVisible(true);
    }
}