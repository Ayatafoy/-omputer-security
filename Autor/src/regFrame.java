import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashSet;



class regFrame extends JFrame {
    //������������� �������� ���������� �����
    Dimension size = new Dimension(500, 50);
    private JLabel text1 = new JLabel("                           ������� �����:");
    private JTextField textField1 = new JTextField();
    private JLabel text2 = new JLabel("                        ������� ������:");
    private JTextField textField2 = new JTextField();
    private JButton regButton = new JButton("������������������");

    //�����, ����������� ��������� ����������� � �������
    public void result() throws Exception {
        String login = this.textField1.getText();
        String password = this.textField2.getText();
        if (login.equals("")){
            textField1.replaceSelection("������� �����!");
        }
        if (password.equals("")){
            textField2.replaceSelection("������� ������!");
        }
        if (!password.equals("") && !login.equals("")) {
            BufferedWriter out = new BufferedWriter(new FileWriter("LP.txt", true));
            BufferedReader in = new BufferedReader(new FileReader("LP.txt"));
            //��������� ������ ��������������� ������ ���� ������
            HashSet set = new HashSet();
            String str;
            while ((str = in.readLine()) != null) {
                str = str.substring(0, str.indexOf(' '));
                set.add(str);
            }
            login = PassSystem.binaryPosl(login);
            password = PassSystem.binaryPosl(password);

            login = PassSystem.crc32(login);
            password = PassSystem.crc32(password);

            if (!set.contains(login)) {
                out.append(login).append("   ").append(password).append("\r\n");
                textField1.setText("");
                textField1.replaceSelection("�� ������� ����������������!");
                textField2.setText("");
            } else {
                textField1.setText("");
                textField1.replaceSelection("������������ � ����� ������ ��� ����������!");
                textField2.setText("");
            }
            out.close();
        }
    }

    public regFrame() {
        //�������� ��������� ���������� � ���� ����������
        setDefaultCloseOperation(regFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocation(580, 250);
        setVisible(true);
        JPanel p = new JPanel();
        add(p);
        p.setLayout(new FlowLayout());
        p.add(text1);
        p.add(textField1);
        p.add(text2);
        p.add(textField2);
        this.textField1.setPreferredSize(size);
        this.textField2.setPreferredSize(size);
        p.add(regButton);

        //��������� ������� ������� ������� �����������
        this.regButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    regFrame.this.result();
                } catch (Exception var3) {
                    var3.printStackTrace();
                }

            }
        });
    }
}