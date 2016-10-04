import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Main {
    public static int k = 0;
    public static void main(String args[]) throws IOException {
        File f = new File("C:\\Users\\INTELLY_POWER\\IdeaProjects\\Search\\����������� Microsoft PowerPoint.pptx");
        //����, ������� �� ����� ������
        File dir = new File("C:\\Users\\INTELLY_POWER\\IdeaProjects\\Search\\Directory");
        //�����, � ������� �� ����� ������ ����
        manage(512, 32, f, dir);//����� �������, ������� �������� ���������, ������� �� �������� 512 ������ 32
    }

    private static void manage(int smesh, int len, File f, File dir) throws IOException {
        String sign = "";
        RandomAccessFile in = new RandomAccessFile
                (f, "r");//���������� ������ ������������� ������� � �����
        in.seek(smesh);//������ ��������� �� �������� 512
        for (int i = 0; i < len; i++) {
            sign += in.readChar();//��������� ��������� �������� �����
        }
        search(sign, smesh, len, dir);//��������� �����
        if (k == 0)
            System.out.print("���� �� ������!");
    }

    private static void search(String sign, int smesh, int len, File dir) throws IOException {
        String s[] = dir.list();//�������� ��, ��� ��������� � �����
        for (int i = 0; i < s.length; i++){//
            File f = new File(dir + "\\" + s[i]);//��������������� ��������� ����� �� �����
            if(!f.isDirectory()){//���� ��� �� �����
                String str = "";
                RandomAccessFile in = new RandomAccessFile
                        (f, "r");//���������� ������ ������������� �������
                in.seek(smesh);//������ ��������� �� �������� 512
                for (int j = 0; j < len; j++) {
                    str += in.readChar();//��������� ��������� �������� �����
                }
                if (str.equals(sign)) {//���������� ��������� � ���������
                    System.out.print(f);//���� ��� �����, ������� ����
                    System.out.print("\n");
                    k++;
                }
            }
            else
                search(sign, smesh, len, f);//���� ��� �����, ���������� ��������� � ������ ����� � ���
        }
    }
}
