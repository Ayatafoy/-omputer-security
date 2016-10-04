import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Main extends JFrame {
    Dimension size = new Dimension(1750, 50);
    private JTextArea textArea1 = new JTextArea(50, 170);
    private JLabel text1 = new JLabel("������� ����:           ");
    private JTextField textField1 = new JTextField();
    private JLabel text2 = new JLabel("������� ���������:");
    private JTextField textField2 = new JTextField();
    private JButton ����������������Button = new JButton("����������� ���������");
    private JButton �����������������Button = new JButton("������������ ���������");

    public void result() throws Exception {
        this.textArea1.setText(null);
        String result = "", message = this.textField2.getText().toLowerCase(),
                ab = "��������������������������������0123456789,.;:@-=+<>[]{}/|!?%* ",
                Key = this.textField1.getText().toLowerCase();
        int keyValue, k = 0;
        while(k != message.length()) {
            char c1 = message.charAt(k);
            this.textArea1.append("������ �� ��������� ���������:   " + c1 + "\n");
            int i = ab.indexOf(c1);
            if(i == -1) {
                result = result + c1;
                k++;
            } else {
                this.textArea1.append("������� ����� ������� � ��������:   " + i + "\n");
                this.textArea1.append("������� ������ �����:   " + Key.charAt(k % Key.length()) + "\n");
                keyValue = ab.indexOf(Key.charAt(k % Key.length()));
                this.textArea1.append("������� �������� ������� ����� � ��������:   " + keyValue + "\n");
                this.textArea1.append("����� �� ������ �������� ������� ����� � ���������:   " +
                        "" + (i + keyValue) % 64 + "\n");
                result = result + ab.charAt((i + keyValue) % 64);
                this.textArea1.append("����������� ����������:   " + result + "\n");
                k++;
            }
        }

        this.textArea1.append("\n" + "\n" + "���������: " + result);
    }

    public void result1() throws Exception {
        this.textArea1.setText(null);
        String result = "", message = this.textField2.getText().toLowerCase(),
                ab = "��������������������������������0123456789,.;:@-=+<>[]{}/|!?%* ",
                Key = this.textField1.getText().toLowerCase();
        int keyValue, k = 0, x;
        while(k != message.length()) {
            char c1 = message.charAt(k);
            this.textArea1.append("������ ������������:   " + c1 + "\n");
            int i = ab.indexOf(c1);
            if(i == -1) {
                result = result + c1;
                k++;
            } else {
                this.textArea1.append("������� ����� ������� � ��������:   " + i + "\n");
                this.textArea1.append("������� ������ �����:   " + Key.charAt(k % Key.length()) + "\n");
                keyValue = ab.indexOf(Key.charAt(k % Key.length()));
                this.textArea1.append("������� �������� ������� ����� � ��������:   " + keyValue + "\n");
                x = (i - keyValue);
                if (x < 0)
                    x += 64;
                this.textArea1.append("������� ������� ��������� ���������:   " +
                        "" + x + "\n");
                result = result + ab.charAt(x);
                this.textArea1.append("����������� ����������:   " + result + "\n");
                k++;
            }
        }

        this.textArea1.append("\n" + "\n" + "���������: " + result);
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
        p.add(����������������Button);
        p.add(�����������������Button);
        p.add(new JScrollPane(textArea1));

        this.����������������Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.this.result();
                } catch (Exception var3) {
                    var3.printStackTrace();
                }

            }
        });

        this.�����������������Button.addActionListener(new ActionListener() {
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