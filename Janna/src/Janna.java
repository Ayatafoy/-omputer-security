import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Janna extends JFrame {
    Dimension size = new Dimension(1750, 50);
    private JTextArea textArea1 = new JTextArea(30, 170);
    private JLabel text1 = new JLabel("Введите Ключ:           ");
    private JTextField textField1 = new JTextField();
    private JLabel text2 = new JLabel("Введите сообщение:");
    private JTextField textField2 = new JTextField();
    private JButton зашифроватьТекстButton = new JButton("Зашифровать сообщение");
    private JButton расшифроватьТекстButton = new JButton("Расшифровать сообщение");

    public static String find(String mas[][], String x) {
        String result = "";
        int k = 0;
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++){
                if (mas[i][j].equals(x)) {
                    result = Integer.toString(i);
                    result += Integer.toString(j);
                    k++;
                }
            }
        if (k == 0)
            result = "z";

        return result;
    }

    public void result() throws Exception {
        this.textArea1.setText(null);
        String message = this.textField2.getText().toLowerCase(),
                ab = "abcdefghiklmnopqrstuvwxyz", ab1, ab2, t1, t2,
                Key = this.textField1.getText().toLowerCase(), del = "", ij;
        int k, i, j;
        char x;
        for (i = 0; i < Key.length(); i++) {
            ij = "";
            x = Key.charAt(i);
            ij += x;
            if (ij.equals("j")) {
                ij = "i";
                x = 'i';
            }
            if (del.indexOf(x) == -1) {
                if (!ij.equals(" "))
                    del += x;
            }
        }
        for (i = 0; i < del.length(); i++){
            k = ab.indexOf(del.charAt(i));
            if (k != -1)
                if (k != 0 && k!= ab.length() - 1) {
                    ab1 = ab.substring(0, k);
                    ab2 = ab.substring(k + 1, ab.length());
                    ab = ab1 + ab2;
                }
                else{
                    if (k == 0)
                        ab = ab.substring(1, ab.length());
                    else
                        ab = ab.substring(0, ab.length() - 1);
                }
        }
        ab = del + ab;
        this.textArea1.append("Полученный алфавит: " + ab);
        this.textArea1.append("\n");
        this.textArea1.append("\n");
        String mas[][] = new String[5][5];
        for (i = 0; i < 5; i++){
            for (j = 0; j < 5; j++){
                x = ab.charAt(j + i * 5);
                mas[i][j] = String.valueOf(x);
            }
        }
        this.textArea1.append("Матрица: ");
        this.textArea1.append("\n");
        this.textArea1.append("\n");
        for (i = 0; i < 5; i++){
            for (j = 0; j < 5; j++) {
                this.textArea1.append(mas[i][j] + "         ");
            }
            this.textArea1.append("\n");
        }

        this.textArea1.append("\n");
        this.textArea1.append("\n");
        char x1;
        String  x3;
        for (i = 0; i < message.length(); i++) {
            x3 = "";
            x1 = message.charAt(i);
            x3 += x1;
            if (x3.equals(" ")) {
                message = message.substring(0, i) + message.substring(i + 1, message.length());
                i--;
            }
        }
        int a[] = {0, 1, 2, 3}, b;
        this.textArea1.append("Криптограмма: ");
        for (k = 0; k < message.length(); k++) {
            x1 = message.charAt(k);
            t1 = "";
            t2 = "";
            t1 += x1;
            if (t1.equals("j"))
                t1 = "i";
            if (!find(mas, t1).equals("z")) {
                i = Character.getNumericValue(find(mas, t1).charAt(0));
                j = Character.getNumericValue(find(mas, t1).charAt(1));
                b = a[k % 4];
                if (b == 0) {
                    if (i != 0)
                        this.textArea1.append(mas[i - 1][j]);
                    else
                        this.textArea1.append(mas[4][j]);
                }
                if (b == 1) {
                    if (j != 4)
                    this.textArea1.append(mas[i][j + 1]);
                    else
                        this.textArea1.append(mas[i][0]);
                }
                if (b == 2) {
                    if (i != 4)
                        this.textArea1.append(mas[i + 1][j]);
                    else
                        this.textArea1.append(mas[0][j]);
                }
                if (b == 3) {
                    if (j != 0)
                        this.textArea1.append(mas[i][j - 1]);
                    else
                        this.textArea1.append(mas[i][4]);
                }
            }
            else {
                this.textArea1.append("**");
            }
        }

    }

    public void result1() throws Exception {
        this.textArea1.setText(null);
        String message = this.textField2.getText().toLowerCase(),
                ab = "abcdefghiklmnopqrstuvwxyz", ab1, ab2, t1, t2,
                Key = this.textField1.getText().toLowerCase(), del = "", ij;
        int k, i, j;
        char x;
        for (i = 0; i < Key.length(); i++) {
            ij = "";
            x = Key.charAt(i);
            ij += x;
            if (ij.equals("j")) {
                ij = "i";
                x = 'i';
            }
            if (del.indexOf(x) == -1) {
                if (!ij.equals(" "))
                    del += x;
            }
        }
        for (i = 0; i < del.length(); i++){
            k = ab.indexOf(del.charAt(i));
            if (k != -1)
                if (k != 0 && k!= ab.length() - 1) {
                    ab1 = ab.substring(0, k);
                    ab2 = ab.substring(k + 1, ab.length());
                    ab = ab1 + ab2;
                }
                else{
                    if (k == 0)
                        ab = ab.substring(1, ab.length());
                    else
                        ab = ab.substring(0, ab.length() - 1);
                }
        }
        ab = del + ab;
        this.textArea1.append("Полученный алфавит: " + ab);
        this.textArea1.append("\n");
        this.textArea1.append("\n");
        String mas[][] = new String[5][5];
        for (i = 0; i < 5; i++){
            for (j = 0; j < 5; j++){
                x = ab.charAt(j + i * 5);
                mas[i][j] = String.valueOf(x);
            }
        }
        this.textArea1.append("Матрица: ");
        this.textArea1.append("\n");
        this.textArea1.append("\n");
        for (i = 0; i < 5; i++){
            for (j = 0; j < 5; j++) {
                this.textArea1.append(mas[i][j] + "         ");
            }
            this.textArea1.append("\n");
        }

        this.textArea1.append("\n");
        this.textArea1.append("\n");
        char x1;
        int a[] = {0, 1, 2, 3}, b;
        this.textArea1.append("Криптограмма: ");
        for (k = 0; k < message.length(); k++) {
            x1 = message.charAt(k);
            t1 = "";
            t2 = "";
            t1 += x1;
            if (!find(mas, t1).equals("z")) {
                i = Character.getNumericValue(find(mas, t1).charAt(0));
                j = Character.getNumericValue(find(mas, t1).charAt(1));
                b = a[k % 4];
                if (b == 0) {
                    if (i != 4)
                        this.textArea1.append(mas[i + 1][j]);
                    else
                        this.textArea1.append(mas[0][j]);
                }
                if (b == 1) {
                    if (j != 0)
                        this.textArea1.append(mas[i][j - 1]);
                    else
                        this.textArea1.append(mas[i][4]);
                }
                if (b == 2) {
                    if (i != 0)
                        this.textArea1.append(mas[i - 1][j]);
                    else
                        this.textArea1.append(mas[4][j]);
                }
                if (b == 3) {
                    if (j != 4)
                        this.textArea1.append(mas[i][j + 1]);
                    else
                        this.textArea1.append(mas[i][0]);
                }
            }
            else {
                this.textArea1.append("**");
            }
        }

    }

    public Janna() {
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
                    Janna.this.result();
                } catch (Exception var3) {
                    var3.printStackTrace();
                }

            }
        });

        this.расшифроватьТекстButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Janna.this.result1();
                } catch (Exception var3) {
                    var3.printStackTrace();
                }

            }
        });
    }

    public static void main(String[] args) throws Exception {
        Janna app = new Janna();
        app.setVisible(true);
    }
}