import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

class Dora extends JFrame {
    Dimension size = new Dimension(1750, 50);
    private JTextArea textArea1 = new JTextArea(30, 170);
    private JLabel text1 = new JLabel("Введите Ключ:           ");
    private JTextField textField1 = new JTextField();
    private JLabel text2 = new JLabel("Введите сообщение:");
    private JTextField textField2 = new JTextField();
    private JButton зашифроватьТекстButton = new JButton("Зашифровать сообщение");
    private JButton расшифроватьТекстButton = new JButton("Расшифровать сообщение");

    public static String find(String mas[][], String x, int len) {
        String result = "";
        int k0 = 0;
        if (len == 9)
            k0 = 1;
        int k = 0;
        for (int i = 0; i < 3; i++) {
            if (i == 0) {
                for (int j = 0; j < len; j++) {
                    if (mas[i][j].equals(x)) {
                        result = Integer.toString(i);
                        result += Integer.toString(j);
                        k++;
                    }
                }
            }
            if (i == 1) {
                for (int j = 0; j < 9; j++) {
                    if (mas[i][j].equals(x)) {
                        result = Integer.toString(i);
                        result += Integer.toString(j);
                        k++;
                    }
                }
            }
            if (i == 2) {
                for (int j = 0; j < 9 - k0; j++) {
                    if (mas[i][j].equals(x)) {
                        result = Integer.toString(i);
                        result += Integer.toString(j);
                        k++;
                    }
                }
            }
        }
            if (k == 0)
                result = "z";

            return result;
    }

    public void result() throws Exception {
        this.textArea1.setText(null);
        String message = this.textField2.getText().toLowerCase(),
                ab = "abcdefghijklmnopqrstuvwxyz", ab1, ab2, t1, t2,
                Key = this.textField1.getText().toLowerCase(), del = "", ij;
        int k, i, j;
        char x;
        for (i = 0; i < Key.length(); i++) {
            ij = "";
            x = Key.charAt(i);
            ij += x;
            if (del.indexOf(x) == -1) {
                if (!ij.equals(" "))
                    del += x;
            }
        }
        if (del.length() > 9)
            del = del.substring(0, 9);
        if (del.length() > 7) {
            for (i = 0; i < del.length(); i++) {
                k = ab.indexOf(del.charAt(i));
                if (k != -1)
                    if (k != 0 && k != ab.length() - 1) {
                        ab1 = ab.substring(0, k);
                        ab2 = ab.substring(k + 1, ab.length());
                        ab = ab1 + ab2;
                    } else {
                        if (k == 0)
                            ab = ab.substring(1, ab.length());
                        else
                            ab = ab.substring(0, ab.length() - 1);
                    }
            }
            String str, temp;
            this.textArea1.append("Таблица: ");
            this.textArea1.append("\n");
            this.textArea1.append("\n");
            this.textArea1.append("                                |   1   2   3   4   5   6   7   8   9");
            this.textArea1.append("\n");
            this.textArea1.append("_________________________________________");
            this.textArea1.append("\n");
            this.textArea1.append("4   5   6   7   8   9   |   ");
            for (i = 0; i < del.length(); i++){
                temp = "";
                temp += del.charAt(i);
                this.textArea1.append(temp + "   ");
            }
            this.textArea1.append("\n");
            this.textArea1.append("                     2   3   |   ");
            str = ab.substring(0, 9);
            for (i = 0; i < 9; i++){
                temp = "";
                temp += str.charAt(i);
                this.textArea1.append(temp + "   ");
            }
            this.textArea1.append("\n");
            this.textArea1.append("                           1   |   ");
            str = ab.substring(9, ab.length());
            for (i = 0; i < str.length(); i++){
                temp = "";
                temp += str.charAt(i);
                this.textArea1.append(temp + "   ");
            }
            this.textArea1.append("\n");
            this.textArea1.append("\n");
            char x1;
            String m[][] = new String[3][9];
            for (i = 0; i < 3; i++) {
                if (i == 0)
                    for (j = 0; j < del.length(); j++) {
                        m[i][j] = String.valueOf(del.charAt(j));
                    }
                if (i == 1)
                    for (j = 0; j < 9; j++) {
                        m[i][j] = String.valueOf(ab.charAt(j));
                    }
                if (i == 2)
                    for (j = 9; j < ab.length(); j++) {
                        m[i][j - 9] = String.valueOf(ab.charAt(j));
                    }
            }
            this.textArea1.append("\n");
            this.textArea1.append("\n");
            this.textArea1.append("Криптограмма:");
            this.textArea1.append("\n");
            this.textArea1.append("\n");
            int ran[] = {4, 5, 6, 7, 8, 9}, ran1[] = {2, 3};
            Random rand = new Random();
            rand.nextInt(5);
            String x3;
            for (i = 0; i < message.length(); i++) {
                x3 = "";
                x1 = message.charAt(i);
                x3 += x1;
                if (x3.equals(" ")) {
                    message = message.substring(0, i) + message.substring(i + 1, message.length());
                    i--;
                }
            }
            int z = 0;
            for (k = 0; k < message.length(); k++) {
                x3 = "";
                x1 = message.charAt(k);
                x3 += x1;
                i = Character.getNumericValue(find(m, x3, del.length()).charAt(0));
                j = Character.getNumericValue(find(m, x3, del.length()).charAt(1));
                if (i == 0){
                    if (z % 5 == 0 && k != 0)
                        this.textArea1.append("   ");
                    if (rand.nextInt(8) == 5) {
                        this.textArea1.append("0");
                        z++;
                    }
                    if (z % 5 == 0 && k != 0)
                        this.textArea1.append("   ");
                    this.textArea1.append(String.valueOf(ran[rand.nextInt(6)]));
                    z++;
                    if (z % 5 == 0 && k != 0)
                        this.textArea1.append("   ");

                    if (rand.nextInt(8) == 5) {
                        this.textArea1.append("0");
                        z++;
                    }
                    if (z % 5 == 0 && k != 0)
                        this.textArea1.append("   ");

                    this.textArea1.append(String.valueOf(j + 1));
                    z++;
                }
                if (i == 1){
                    if (z % 5 == 0 && k != 0)
                        this.textArea1.append("   ");
                    if (rand.nextInt(8) == 5) {
                        this.textArea1.append("0");
                        z++;
                    }
                    if (z % 5 == 0 && k != 0)
                        this.textArea1.append("   ");
                    this.textArea1.append(String.valueOf(ran1[rand.nextInt(2)]));
                    z++;
                    if (z % 5 == 0 && k != 0)
                        this.textArea1.append("   ");
                    if (rand.nextInt(8) == 5) {
                        this.textArea1.append("0");
                        z++;
                    }
                    if (z % 5 == 0 && k != 0)
                        this.textArea1.append("   ");

                    this.textArea1.append(String.valueOf(j + 1));
                    z++;
                }
                if (i == 2){
                    if (z % 5 == 0 && k != 0)
                        this.textArea1.append("   ");
                    if (rand.nextInt(8) == 5) {
                        this.textArea1.append("0");
                        z++;
                    }
                    if (z % 5 == 0 && k != 0)
                        this.textArea1.append("   ");
                    this.textArea1.append(String.valueOf(1));
                    z++;
                    if (z % 5 == 0 && k != 0)
                        this.textArea1.append("   ");
                    if (rand.nextInt(8) == 5) {
                        this.textArea1.append("0");
                        z++;
                    }
                    if (z % 5 == 0 && k != 0)
                        this.textArea1.append("   ");
                    this.textArea1.append(String.valueOf(j + 1));
                    z++;
                }
            }
        }
        else
            this.textArea1.append("Введите корректный ключ!");
    }

    public void result1() throws Exception {
        this.textArea1.setText(null);
        String message = this.textField2.getText().toLowerCase(),
                ab = "abcdefghijklmnopqrstuvwxyz", ab1, ab2, t1, t2,
                Key = this.textField1.getText().toLowerCase(), del = "", ij;
        int k, i, j;
        char x;
        for (i = 0; i < Key.length(); i++) {
            ij = "";
            x = Key.charAt(i);
            ij += x;
            if (del.indexOf(x) == -1) {
                if (!ij.equals(" "))
                    del += x;
            }
        }
        if (del.length() > 9)
            del = del.substring(0, 9);
        if (del.length() > 7) {
            for (i = 0; i < del.length(); i++) {
                k = ab.indexOf(del.charAt(i));
                if (k != -1)
                    if (k != 0 && k != ab.length() - 1) {
                        ab1 = ab.substring(0, k);
                        ab2 = ab.substring(k + 1, ab.length());
                        ab = ab1 + ab2;
                    } else {
                        if (k == 0)
                            ab = ab.substring(1, ab.length());
                        else
                            ab = ab.substring(0, ab.length() - 1);
                    }
            }
            String str, temp;
            this.textArea1.append("Таблица: ");
            this.textArea1.append("\n");
            this.textArea1.append("\n");
            this.textArea1.append("                                |   1   2   3   4   5   6   7   8   9");
            this.textArea1.append("\n");
            this.textArea1.append("_________________________________________");
            this.textArea1.append("\n");
            this.textArea1.append("4   5   6   7   8   9   |   ");
            for (i = 0; i < del.length(); i++){
                temp = "";
                temp += del.charAt(i);
                this.textArea1.append(temp + "   ");
            }
            this.textArea1.append("\n");
            this.textArea1.append("                     2   3   |   ");
            str = ab.substring(0, 9);
            for (i = 0; i < 9; i++){
                temp = "";
                temp += str.charAt(i);
                this.textArea1.append(temp + "   ");
            }
            this.textArea1.append("\n");
            this.textArea1.append("                           1   |   ");
            str = ab.substring(9, ab.length());
            for (i = 0; i < str.length(); i++){
                temp = "";
                temp += str.charAt(i);
                this.textArea1.append(temp + "   ");
            }
            this.textArea1.append("\n");
            this.textArea1.append("\n");
            char x1, x2;
            String m[][] = new String[3][9];
            for (i = 0; i < 3; i++) {
                if (i == 0)
                    for (j = 0; j < del.length(); j++) {
                        m[i][j] = String.valueOf(del.charAt(j));
                    }
                if (i == 1)
                    for (j = 0; j < 9; j++) {
                        m[i][j] = String.valueOf(ab.charAt(j));
                    }
                if (i == 2)
                    for (j = 9; j < ab.length(); j++) {
                        m[i][j - 9] = String.valueOf(ab.charAt(j));
                    }
            }
            this.textArea1.append("\n");
            this.textArea1.append("\n");
            this.textArea1.append("Исходное сообщение:");
            this.textArea1.append("\n");
            this.textArea1.append("\n");
            String x3, x4;
            for (i = 0; i < message.length(); i++) {
                x3 = "";
                x1 = message.charAt(i);
                x3 += x1;
                if (x3.equals(" ") || x3.equals("0")) {
                    message = message.substring(0, i) + message.substring(i + 1, message.length());
                    i--;
                }
            }
            for (k = 0; k < message.length(); k += 2) {
                x3 = "";
                x4 = "";
                x1 = message.charAt(k);
                x2 = message.charAt(k + 1);
                x3 += x1;
                x4 += x2;
                Integer i1 = Integer.valueOf(x3);
                if (i1 == 4 || i1 == 5 || i1 == 6 || i1 == 7 || i1 == 8 || i1 == 9)
                    i1 = 0;
                if (i1 == 2 || i1 == 3)
                    i1 = 1;
                else
                if (i1 == 1)
                    i1 = 2;
                Integer j1 = Integer.valueOf(x4) - 1;
                this.textArea1.append(m[i1][j1]);
            }
        }
        else
            this.textArea1.append("Введите корректный ключ!");
    }


    public Dora() {
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
                    Dora.this.result();
                } catch (Exception var3) {
                    var3.printStackTrace();
                }

            }
        });

        this.расшифроватьТекстButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Dora.this.result1();
                } catch (Exception var3) {
                    var3.printStackTrace();
                }

            }
        });
    }

    public static void main(String[] args) throws Exception {
        Dora app = new Dora();
        app.setVisible(true);
    }
}