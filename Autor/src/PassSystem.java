import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashSet;
import java.io.FileInputStream;
import java.io.FileOutputStream;

class PassSystem extends JFrame {

    //инициализация объектов интерфейса формы
    Dimension size = new Dimension(1750, 50);
    private JLabel text1 = new JLabel("Введите логин:  ");
    private JTextField textField1 = new JTextField();
    private JLabel text2 = new JLabel("Введите пароль:");
    private JTextField textField2 = new JTextField();
    private JButton enterButton = new JButton("Войти в систему");
    private JButton exitButton = new JButton("Выйти из системы");
    private JButton regButton = new JButton("Регистрация");
    private FileInputStream inputStream;
    private boolean testOpen = false;//переменные фиксации состояния системы,
    private boolean testFailed = false;//чтобы был известен статус файлов, скрыты ли они уже или нет

    //запуск программы
    public static void main(String[] args) throws Exception {
        PassSystem app = new PassSystem();
        app.setVisible(true);
    }

    //метод перевода числа в двоичную последовательность
    public static String binarhyCoding(int x) {
        String bynaryCod = "";

        do {
            int x1 = x % 2;
            x /= 2;
            bynaryCod = Integer.toString(x1).concat(bynaryCod);
        } while(x != 1 && x != 0);

        bynaryCod = Integer.toString(x).concat(bynaryCod);
        while (bynaryCod.length() < 8){
            bynaryCod = "0" + bynaryCod;
        }
        return bynaryCod;
    }

    //перевод строки в двоичную последовательность
    public static String binaryPosl(String login){
        String alf = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZzАаБбВвГгДдЕеЁёЖжЗзИиЙйКкЛлМм" +
                "НнОоПпРрСсТтУуФфХхЦцЧчШшЩщЪъЫыЬьЭэЮюЯя0123456789,.;:@-=+<>[]{}/|!?%* ";
        int k = 0;
        String kod = "";

        while (k != login.length()) {
            char x = login.charAt(k);
            int i = alf.indexOf(x);
            if (i == -1) {
                k++;
            } else {
                kod += binarhyCoding(i);
                k++;
            }
        }
        return kod;
    }
    //метод сложения бинарных последоватеьностей по модулю 2(операция XOR)
    public static String additionTwo(String s1, String s2) {
        int i = 0;
        String s3 = "";
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

    //метод перевода строки в код crc32
    public static String crc32(String str) throws Exception {
        String Key = "10000100110000010001110110110111";
        int k = 0, n = str.length();
        str = str + "00000000000000000000000000000000";
        while(k < n) {
            char x = str.charAt(0);
            if (x =='1') {
                String kod = str.substring(1,33);
                kod = additionTwo(kod, Key);
                if (str.length() >= 33)
                    str = kod + str.substring(33, str.length());
                else
                    str = kod;
                k++;
            }
            else{
                str = str.substring(1, str.length());
                k++;
            }
        }
        return str;
    }

    //сдвиг каждого бита на 3, чтобы файлы не отображались при выходе из системы
    public static int[] crypto(int[] mas, int n){
        for (int i = 0; i < n; i++){
            mas[i] += 3;
        }
        return mas;
    }

    //восстановление исходной последовательности бит, для корректного отображения файлов при входе
    public static int[] desCrypto(int[] mas, int n){
        for (int i = 0; i < n; i++){
            mas[i] -= 3;
        }
        return mas;
    }

    //метод, который вызывается при выходе из системы, находит защищенные файлы и скрывает
    //их путем вызова метода crypto, в папке users хранишь файлы любого формата,
    //к ней ты получаешь доступ при авторизации
    public void failedFiles() throws IOException {
        File dir = new File("C:\\Users\\Алексей\\Desktop\\Autor\\users");
        String s[] = dir.list();
        for (int i = 0; i < s.length; i++) {
            inputStream = new FileInputStream("C:\\Users\\Алексей\\Desktop\\Autor\\users\\" + s[i]);
            int data = inputStream.available();
            InputStream in = new BufferedInputStream(new FileInputStream
                    ("C:\\Users\\Алексей\\Desktop\\Autor\\users\\" + s[i]), data);
            int val = in.read();
            OutputStream out = new BufferedOutputStream(new FileOutputStream
                    ("C:\\Users\\Алексей\\Desktop\\Autor\\users\\" + s[i]));
            int j = 0, mas[] = new int[data];
            mas[j] = val;
            while ((val = in.read()) != -1) {
                j++;
                mas[j] = val;
            }
            crypto(mas, data);
            while (j >= 0) {
                out.write(mas[j]);
                j--;
            }
            inputStream.close();
            in.close();
            out.close();
            testFailed = true;
            testOpen = false;
        }
    }

    //метод, который вызывается при входе в систему, находит защищенные файлы и открывает
    //их путем вызова метода desCrypto, в папке users хранишь файлы любого формата,
    //к ней ты получаешь доступ при авторизации
    public void openFiles() throws IOException {
        File dir = new File("C:\\Users\\Алексей\\Desktop\\Autor\\users");
        String s[] = dir.list();
        for (int i = 0; i < s.length; i++) {
            inputStream = new FileInputStream("C:\\Users\\Алексей\\Desktop\\Autor\\users\\" + s[i]);
            int data = inputStream.available();
            InputStream in = new BufferedInputStream(new FileInputStream
                    ("C:\\Users\\Алексей\\Desktop\\Autor\\users\\" + s[i]), data);
            int val = in.read();
            OutputStream out = new BufferedOutputStream(new FileOutputStream
                    ("C:\\Users\\Алексей\\Desktop\\Autor\\users\\" + s[i]));
            int j = 0, mas[] = new int[data];
            mas[j] = val;
            while ((val = in.read()) != -1) {
                j++;
                mas[j] = val;
            }
            desCrypto(mas, data);
            while (j >= 0) {
                out.write(mas[j]);
                j--;
            }
            inputStream.close();
            in.close();
            out.close();
            testOpen = true;
            testFailed = false;
        }
    }

    //создание нового окна, при нажатии на кнопку регистрации
    public void registratoin() throws Exception {
        regFrame app = new regFrame();
        app.setVisible(true);
    }

    //метод, описывающий процедуру выхода из системы
    public void exit() throws Exception {
        if (!testFailed) {
            failedFiles();
            textField1.setText("");
            textField1.replaceSelection("Вы успешно покинули систему!");
            textField2.setText("");
        }
    }

    //метод, описывающий процедуру входа в систему
    public void enter() throws Exception {
        if (!testOpen) {
            BufferedReader in = new BufferedReader(new FileReader("LP.txt"));
            //множество хранит авторизационные данные всех юзеров
            HashSet setAll = new HashSet();
            String str, login = this.textField1.getText(), password = this.textField2.getText();
            while ((str = in.readLine()) != null) {
                setAll.add(str);
            }

            //перевод логина и пароля в двоичную строку
            String log = binaryPosl(login);
            String pass = binaryPosl(password);

            login = log;
            password = pass;

            //перевод логина и пароля в хеш код алгоритмом crc32
            login = crc32(login);
            password = crc32(password);

            in.close();

            //оповещение пользователя о результатах авторазации
            if (setAll.contains(login + "   " + password)) {
                textField1.setText("");
                textField1.replaceSelection("Вы успешно авторизованы!");
                textField2.setText("");
                openFiles();
            } else {
                textField1.setText("");
                textField1.replaceSelection("Не верно введен логин или пароль!");
                textField2.setText("");
            }

        }
    }

    //конструктор приложения
    public PassSystem() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1032);
        setLocation(0, 0);
        setVisible(true);
        JPanel p = new JPanel();
        add(p);
        p.setLayout(new FlowLayout());//привязка элементов интерфейса к окну приложения
        p.add(text1);
        p.add(textField1);
        p.add(text2);
        p.add(textField2);
        this.textField1.setPreferredSize(size);
        this.textField2.setPreferredSize(size);
        p.add(regButton);
        p.add(enterButton);
        p.add(exitButton);

        //обработка события нажатия клавиши входа в систему
        this.enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    PassSystem.this.enter();
                } catch (Exception var3) {
                    var3.printStackTrace();
                }

            }
        });

        //обработка события нажатия клавиши выхода из системы
        this.exitButton.addActionListener(new ActionListener() {//обработка события нажатия клавиши выхода из системы
            public void actionPerformed(ActionEvent e) {
                try {
                    PassSystem.this.exit();
                } catch (Exception var3) {
                    var3.printStackTrace();
                }

            }
        });

        //обработка события нажатия клавиши регистрации
        this.regButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    PassSystem.this.registratoin();
                } catch (Exception var3) {
                    var3.printStackTrace();
                }
            }
        });

    }
}

