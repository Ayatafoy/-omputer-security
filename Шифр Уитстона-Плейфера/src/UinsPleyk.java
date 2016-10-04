import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class UinsPleyk extends JFrame {
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
        char x1, x2;
        int i1, j1, i2, j2;
        String s1 = "", x3, x4;
        for (i = 0; i < message.length(); i++) {
            x3 = "";
            x1 = message.charAt(i);
            x3 += x1;
            if (x3.equals(" ")) {
                message = message.substring(0, i) + message.substring(i + 1, message.length());
                i--;
            }
        }
        for (i = 0; i < message.length(); i += 2) {
            x3 = "";
            x4 = "";
            x1 = message.charAt(i);
            x3 += x1;
            if (message.length() - i == 1){
                if (!x3.equals("k"))
                    s1 += x3 + "k";
                else
                    s1 += x3 + "x";
                break;
            }
            x2 = message.charAt(i + 1);
            x4 += x2;
            if (x3.equals(x4) || x3.equals("i") && x4.equals("j") || x3.equals("j") && x4.equals("i")){
                if (!x3.equals("k"))
                    s1 += x3 + "k";
                else
                    s1 += x3 + "x";
                i--;
            }
            else
                s1 += x3 + x4;
        }
        this.textArea1.append("\n" + s1 + "\n");
        this.textArea1.append("Криптограмма: ");
        for (k = 0; k < s1.length(); k += 2) {
            x1 = s1.charAt(k);
            x2 = s1.charAt(k + 1);
            t1 = "";
            t2 = "";
            t1 += x1;
            t2 += x2;
            if (t1.equals("j"))
                t1 = "i";
            if (t2.equals("j"))
                t2 = "i";
            if (!find(mas, t1).equals("z") && !find(mas, t2).equals("z")) {
                i1 = Character.getNumericValue(find(mas, t1).charAt(0));
                j1 = Character.getNumericValue(find(mas, t1).charAt(1));
                i2 = Character.getNumericValue(find(mas, t2).charAt(0));
                j2 = Character.getNumericValue(find(mas, t2).charAt(1));
                if (i1 == i2) {
                    if (j1 != 4)
                        this.textArea1.append(mas[i1][j1 + 1]);
                    else
                        this.textArea1.append(mas[i1][0]);
                    if (j2 != 4)
                        this.textArea1.append(mas[i2][j2 + 1]);
                    else
                        this.textArea1.append(mas[i2][0]);

                } else {
                    if (j1 == j2) {
                        if (i1 != 4)
                            this.textArea1.append(mas[i1 + 1][j1]);
                        else
                            this.textArea1.append(mas[0][j1]);
                        if (i2 != 4)
                            this.textArea1.append(mas[i2 + 1][j2]);
                        else
                            this.textArea1.append(mas[0][j2]);
                    }
                    else {
                        this.textArea1.append(mas[i1][j2]);
                        this.textArea1.append(mas[i2][j1]);
                    }
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
        char x1, x2;
        int i1, j1, i2, j2;
        this.textArea1.append("Исходное сообщение: ");
        for (k = 0; k < message.length(); k += 2) {
            x1 = message.charAt(k);
            x2 = message.charAt(k + 1);
            t1 = "";
            t2 = "";
            t1 += x1;
            t2 += x2;
            if (!find(mas, t1).equals("z") && !find(mas, t2).equals("z")) {
                i1 = Character.getNumericValue(find(mas, t1).charAt(0));
                j1 = Character.getNumericValue(find(mas, t1).charAt(1));
                i2 = Character.getNumericValue(find(mas, t2).charAt(0));
                j2 = Character.getNumericValue(find(mas, t2).charAt(1));
                if (i1 == i2) {
                    if (j1 != 0)
                        this.textArea1.append(mas[i1][j1 - 1]);
                    else
                        this.textArea1.append(mas[i1][4]);
                    if (j2 != 0)
                        this.textArea1.append(mas[i2][j2 - 1]);
                    else
                        this.textArea1.append(mas[i2][4]);

                } else {
                    if (j1 == j2) {
                        if (i1 != 0)
                            this.textArea1.append(mas[i1 - 1][j1]);
                        else
                            this.textArea1.append(mas[4][j1]);
                        if (i2 != 0)
                            this.textArea1.append(mas[i2 - 1][j2]);
                        else
                            this.textArea1.append(mas[4][j2]);
                    }
                    else {
                        this.textArea1.append(mas[i1][j2]);
                        this.textArea1.append(mas[i2][j1]);
                    }
                }
            }
            else {
                this.textArea1.append("**");
            }
        }

    }

    public UinsPleyk() {
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
                    UinsPleyk.this.result();
                } catch (Exception var3) {
                    var3.printStackTrace();
                }

            }
        });

        this.расшифроватьТекстButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    UinsPleyk.this.result1();
                } catch (Exception var3) {
                    var3.printStackTrace();
                }

            }
        });
    }

    public static void main(String[] args) throws Exception {
        UinsPleyk app = new UinsPleyk();
        app.setVisible(true);
    }
}