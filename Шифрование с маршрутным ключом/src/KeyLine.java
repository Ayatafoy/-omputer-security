import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

class KeyLine extends JFrame {
    Dimension size = new Dimension(1750, 50);
    private JTextArea textArea1 = new JTextArea(22, 170);
    private JTextArea textArea2 = new JTextArea(22, 170);
    private JLabel text1 = new JLabel("Введите Ключ:           ");
    private JTextField textField1 = new JTextField();
    private JLabel text2 = new JLabel("Введите сообщение:");
    private JTextField textField2 = new JTextField();
    private JLabel text3 = new JLabel("Введите К:                   ");
    private JLabel text4 = new JLabel("Промежуточные результаты:                                                    " +
            "                           " +
            "                                                                                                       " +
            "                                                                                                       " +
            "                                                                                                       " +
            "                                                                                                       " +
            "                                                                             ");
    private JLabel text5 = new JLabel("Результат:                                                                   " +
            "                                                                                                       " +
            "                                                                                                       " +
            "                                                                                                       " +
            "                                                                                                       " +
            "                                                                                                       " +
            "                      ");
    private JTextField textField3 = new JTextField();
    private JButton зашифроватьТекстButton = new JButton("Зашифровать сообщение");
    private JButton зашифроватьТекстButton1 = new JButton("Расшифровать сообщение");
    static String Key;

    public static int find(Integer[] a, int x) {
        for (int i = 0; i < Key.length(); i++) {
            if (a[i] == x) {
                x = i;
                break;
            }
            if (i == Key.length() - 1 && a[i] == x)
                x = -1;
        }
        return x;
    }

    public void result1() throws Exception {
        this.textArea1.setText(null);
        this.textArea2.setText(null);
        String message = this.textField2.getText().toLowerCase(),
                ab = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя0123456789,.;:@-=+<>[]{}/|!?%* ";
        Key = this.textField1.getText().toLowerCase();
        Integer k = new Integer(this.textField3.getText()), l = Key.length(), a[] = new Integer[Key.length()],
                b[] = new Integer[Key.length()],  c[] = new Integer[Key.length()];
        int x1, x2, x3, x4, x5, x6, n, j = 0, m, endY;
        x1 = message.length();
        x2 = k * l;
        x3 = x1 / x2;
        x4 = x3 * x2;
        if (x4 != 0)
        x5 = x1 % x4;
        else
        x5 = x1;
        x6 = x5 / l;
        if (x6 != 0)
        endY = x6;
        else
            endY = k;
        if (message.length() % (k * l) == 0)
            n = message.length() / (k * l);
        else
            n = message.length() / (k * l) + 1;

        for (int i = 0; i < Key.length(); i++) {
            a[i] = ab.indexOf(Key.charAt(i));
            b[i] = ab.indexOf(Key.charAt(i));

        }
        Arrays.sort(b);
        for (int i = 0; i < Key.length(); i++) {
            c[i] = find(a, b[i]);
        }
        this.textArea1.append("Число блоков: " + n + "\n");
        this.textArea1.append("\n" + "Массив номеров элементов из алфавита соотвтствующих ключу:" + "\n");
        while(j != Key.length()) {
            this.textArea1.append(a[j] + " ");
            j++;
        }
        j =0 ;
        this.textArea1.append("\n"+ "Отсортированный массив ключа:" + "\n");
        while(j != Key.length()) {
            this.textArea1.append(b[j] + " ");
            j++;
        }
        j = 0;
        this.textArea1.append("\n" + "Полученный порядок:" + "\n");
        while(j != Key.length()) {
            this.textArea1.append(c[j] + " ");
            j++;
        }

        for (int i = 0; i < n; i++) {
            this.textArea1.append("\n" + "\n" + i + " матрица" + "\n");
            char d[][] = new char[k][l];
            for (j = 0; j < l; j++) {
                if (i != n - 1) {
                    for (m = 0; m < k; m++) {
                        d[m][c[j]] = message.charAt(m + (j * k) + (i * k * l));
                    }
                }
                else {
                    for (m = 0; m < endY; m++) {
                        d[m][c[j]] = message.charAt(m + (j * endY) + (i * k * l));
                    }
                }
            }
            if (i != n - 1) {
                    for (m = 0; m < k; m++) {
                        for (j = 0; j < l; j++) {
                            this.textArea1.append(String.valueOf(d[m][j]) + " ");
                            this.textArea2.append(String.valueOf(d[m][j]));
                        }
                        this.textArea1.append("\n");
                    }
            }
            else{
                for (m = 0; m < endY; m++) {
                    for (j = 0; j < l; j++) {
                        this.textArea1.append(String.valueOf(d[m][j]) + " ");
                        this.textArea2.append(String.valueOf(d[m][j]));
                    }
                    this.textArea1.append("\n");
                }
            }
        }
    }

    public void result() throws Exception {
        this.textArea1.setText(null);
        this.textArea2.setText(null);
        Random rand = new Random();
        String message = this.textField2.getText().toLowerCase(),
                ab = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя0123456789,.;:@-=+<>[]{}/|!?%* ";
            Key = this.textField1.getText().toLowerCase();
        Integer k = new Integer(this.textField3.getText()), l = Key.length(), a[] = new Integer[Key.length()],
                b[] = new Integer[Key.length()],  c[] = new Integer[Key.length()];
        int n, j = 0, m, h = 0, endY = 999, t11;
        if (message.length() % (k * l) == 0)
        n = message.length() / (k * l);
        else
            n = message.length() / (k * l) + 1;

        for (int i = 0; i < Key.length(); i++) {
            a[i] = ab.indexOf(Key.charAt(i));
            b[i] = ab.indexOf(Key.charAt(i));

        }
        Arrays.sort(b);
        for (int i = 0; i < Key.length(); i++) {
            c[i] = find(a, b[i]);
        }
        this.textArea1.append("Число блоков: " + n + "\n");
        this.textArea1.append("\n" + "Массив номеров элементов из алфавита соотвтствующих ключу:" + "\n");
        while(j != Key.length()) {
            this.textArea1.append(a[j] + " ");
            j++;
        }
        j =0 ;
        this.textArea1.append("\n"+ "Отсортированный массив ключа:" + "\n");
        while(j != Key.length()) {
            this.textArea1.append(b[j] + " ");
            j++;
        }
        j = 0;
        this.textArea1.append("\n" + "Полученный порядок:" + "\n");
        while(j != Key.length()) {
            this.textArea1.append(c[j] + " ");
            j++;
        }
        char t1;
        for (int i = 0; i < n; i++) {
            this.textArea1.append("\n" + "\n" + i + " матрица" + "\n");
            char d[][] = new char[k][l];
            for (j = 0; j < k; j++) {
                for (m = 0; m < l; m++) {
                    d[j][m] = message.charAt(m + (j * l) + (i * k * l));
                    this.textArea1.append(d[j][m] + " ");
                    h++;
                    if (h == message.length()){
                        endY = j + 1;
                        for (int f = m + 1; f < l; f++){
                            t11 = rand.nextInt(63);
                            t1 = ab.charAt(t11);
                            d[j][f] = t1;
                            this.textArea1.append(t1 + " ");
                        }
                        break;
                    }
                }
                this.textArea1.append("\n");
                if (endY != 999)
                    break;
            }
            if (i != n - 1)
            for (m = 0; m < l; m++) {
                for (j = 0; j < k; j++) {
                    this.textArea2.append(String.valueOf(d[j][c[m]]));
                }
            }
            else {
                for (m = 0; m < l; m++) {
                    for (j = 0; j < endY; j++) {
                        this.textArea2.append(String.valueOf(d[j][c[m]]));
                    }
                }
            }

        }
    }

    public KeyLine() {
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
        p.add(text3);
        p.add(textField3);
        this.textField1.setPreferredSize(size);
        this.textField2.setPreferredSize(size);
        this.textField3.setPreferredSize(size);
        p.add(зашифроватьТекстButton);
        p.add(зашифроватьТекстButton1);
        p.add(text4);
        p.add(new JScrollPane(textArea1));
        p.add(text5);
        p.add(new JScrollPane(textArea2));

        this.зашифроватьТекстButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    KeyLine.this.result();
                } catch (Exception var3) {
                    var3.printStackTrace();
                }

            }
        });

        this.зашифроватьТекстButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    KeyLine.this.result1();
                } catch (Exception var3) {
                    var3.printStackTrace();
                }

            }
        });
    }

    public static void main(String[] args) throws Exception {
        KeyLine app = new KeyLine();
        app.setVisible(true);
    }
}