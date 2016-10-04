import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Vijer extends JFrame {
    Dimension size = new Dimension(1750, 50);
    private JTextArea textArea1 = new JTextArea(30, 170);
    private JLabel text1 = new JLabel("Введите Ключ:           ");
    private JTextField textField1 = new JTextField();
    private JLabel text2 = new JLabel("Введите сообщение:");
    private JTextField textField2 = new JTextField();
    private JButton зашифроватьТекстButton = new JButton("Зашифровать сообщение");
    private JButton расшифроватьТекстButton = new JButton("Расшифровать сообщение");

    public static String findStr(String mas[][], String x, int n) {
        String result = "";
        int k = 0;
        for (int i = 0; i < 33; i++) {
            if (mas[i][n].equals(x)) {
                result = Integer.toString(i);
                k++;
            }
        }

        if (k == 0)
            result = "z";

        return result;
    }

    public static String findColumn(String mas[][], String x) {
        String result = "";
        int k = 0;
        for (int i = 0; i < 33; i++) {
            if (mas[0][i].equals(x)) {
                result = Integer.toString(i);
                k++;
            }
        }

        if (k == 0)
            result = "z";

        return result;
    }

    public void result() throws Exception {
        this.textArea1.setText(null);
        String message1 = this.textField2.getText().toLowerCase(), message = "",
                ab = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя",
                Key1 = this.textField1.getText().toLowerCase(), Key = "";
        int k = 0, i, j, x = 0;
        String table[][] = new String[33][33];
        char x1, x2;
        String t1, t2;

        for (i = 0; i < Key1.length(); i++){
            t1 = "";
            x1 = Key1.charAt(i);
            t1 += x1;
            if (!t1.equals(" ")){
                Key += t1;
            }
        }

        for (i = 0; i < message1.length(); i++){
            t1 = "";
            x1 = message1.charAt(i);
            t1 += x1;
            if (!t1.equals(" ")){
                message += t1;
            }
        }


        for (i = 0; i < 33; i++)
            for (j = 0; j < 33; j++){
                table[i][j] = String.valueOf((ab.charAt((j + i) % 33)));
            }

        this.textArea1.append("Таблица Виженера:");
        this.textArea1.append("\n");
        this.textArea1.append("\n");
        for (i = 0; i < 33; i++) {
            for (j = 0; j < 33; j++) {
                this.textArea1.append(String.valueOf(table[i][j]) + "   ");
            }
            this.textArea1.append("\n");
        }

        this.textArea1.append("\n");
        this.textArea1.append("Криптограмма:");
        this.textArea1.append("\n");
        this.textArea1.append("\n");
        int i1, j1;
        for (i = 0; i < message.length(); i++) {
            t1 = "";
            t2 = "";
            x1 = message.charAt(i);
            x2 = Key.charAt(i % Key.length());
            t1 += x1;
            t2 += x2;
            i1 = Integer.valueOf(findStr(table, t1, 0));
            j1 = Integer.valueOf(findColumn(table, t2));
            this.textArea1.append(String.valueOf(table[i1][j1]));
        }
        this.textArea1.append("\n");
        this.textArea1.append("\n");
    }

    public void result1() throws Exception {
        this.textArea1.setText(null);
        String message = this.textField2.getText().toLowerCase(),
                ab = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя",
                Key1 = this.textField1.getText().toLowerCase(), Key = "";
        int i, j;
        String table[][] = new String[33][33];
        char x1, x2;
        String t1, t2;

        for (i = 0; i < Key1.length(); i++){
            t1 = "";
            x1 = Key1.charAt(i);
            t1 += x1;
            if (!t1.equals(" ")){
                Key += t1;
            }
        }

        for (i = 0; i < 33; i++)
            for (j = 0; j < 33; j++){
                table[i][j] = String.valueOf((ab.charAt((j + i) % 33)));
            }

        this.textArea1.append("Таблица Виженера:");
        this.textArea1.append("\n");
        this.textArea1.append("\n");
        for (i = 0; i < 33; i++) {
            for (j = 0; j < 33; j++) {
                this.textArea1.append(String.valueOf(table[i][j]) + "   ");
            }
            this.textArea1.append("\n");
        }

        this.textArea1.append("\n");
        this.textArea1.append("Исходное сообщение:");
        this.textArea1.append("\n");
        this.textArea1.append("\n");
        int i1, j1;
        for (i = 0; i < message.length(); i++) {
            t1 = "";
            t2 = "";
            x1 = message.charAt(i);
            x2 = Key.charAt(i % Key.length());
            t1 += x1;
            t2 += x2;
            j1 = Integer.valueOf(findColumn(table, t2));
            i1 = Integer.valueOf(findStr(table, t1, j1));
            this.textArea1.append(String.valueOf(table[i1][0]));
        }
        this.textArea1.append("\n");
        this.textArea1.append("\n");
    }

    public Vijer() {
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
                    Vijer.this.result();
                } catch (Exception var3) {
                    var3.printStackTrace();
                }

            }
        });

        this.расшифроватьТекстButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Vijer.this.result1();
                } catch (Exception var3) {
                    var3.printStackTrace();
                }

            }
        });
    }

    public static void main(String[] args) throws Exception {
        Vijer app = new Vijer();
        app.setVisible(true);
    }
}