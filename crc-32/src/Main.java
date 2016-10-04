import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {
    private static FileInputStream inputStream;

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
    }// ������� ��������� ���� � �������� ������

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
    }//�������� �� ������ 2

    public static void crc32() throws IOException {
        inputStream = new FileInputStream
                ("C:\\Users\\INTELLY_POWER\\IdeaProjects\\crc-32\\files\\Mathcad Document.xmct");
        int data = inputStream.available();
        InputStream in = new BufferedInputStream(new FileInputStream
                ("C:\\Users\\INTELLY_POWER\\IdeaProjects\\crc-32\\files\\Mathcad Document.xmct"), data);
        int val;
        String str = "";
        while((val = in.read()) != -1){
            str += binarhyCoding(val);//��������� ��������� ���� ����� � �������� ������
        }
        String Key = "10000100110000010001110110110111";//����������� ������� �� ���������
        int k = 0, n = str.length();
        str = str + "00000000000000000000000000000000";
        while(k < n) {
            char x = str.charAt(0);
            if (x =='1') {//���� ��������� �������,
                String kod = str.substring(1,33);
                kod = additionTwo(kod, Key);//�� ��������� �������� �� ������ ����� ����� 2 � ���������
                if (str.length() >= 33)
                    str = kod + str.substring(33, str.length());
                else
                    str = kod;
                k++;
            }
            else{//����� ��������� ����� ����
                str = str.substring(1, str.length());
                k++;
            }
        }
        System.out.print(str);
    }

    public static void main(String args[]) throws Exception {
        crc32();
    }
}
