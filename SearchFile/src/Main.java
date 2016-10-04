import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Main {
    public static int k = 0;
    public static void main(String args[]) throws IOException {
        File f = new File("C:\\Users\\INTELLY_POWER\\IdeaProjects\\Search\\Презентация Microsoft PowerPoint.pptx");
        //файл, который мы будем искать
        File dir = new File("C:\\Users\\INTELLY_POWER\\IdeaProjects\\Search\\Directory");
        //папка, в которой мы будем искать файл
        manage(512, 32, f, dir);//вызов функции, которая выделяет сигнатуру, начиная со смещения 512 длиной 32
    }

    private static void manage(int smesh, int len, File f, File dir) throws IOException {
        String sign = "";
        RandomAccessFile in = new RandomAccessFile
                (f, "r");//инициируем объект произвольного доступа к файлу
        in.seek(smesh);//ставим указатель на смещение 512
        for (int i = 0; i < len; i++) {
            sign += in.readChar();//считываем сигнатуру заданной длины
        }
        search(sign, smesh, len, dir);//запускаем поиск
        if (k == 0)
            System.out.print("Файл не найден!");
    }

    private static void search(String sign, int smesh, int len, File dir) throws IOException {
        String s[] = dir.list();//получаем то, что находится в папке
        for (int i = 0; i < s.length; i++){//
            File f = new File(dir + "\\" + s[i]);//последовательно считываем файлы из папки
            if(!f.isDirectory()){//если это не папка
                String str = "";
                RandomAccessFile in = new RandomAccessFile
                        (f, "r");//инициируем объект произвольного доступа
                in.seek(smesh);//ставим указатель на смещение 512
                for (int j = 0; j < len; j++) {
                    str += in.readChar();//считываем сигнатуру заданной длины
                }
                if (str.equals(sign)) {//сравниваем сигнатуру с эталонной
                    System.out.print(f);//если они равны, выводим путь
                    System.out.print("\n");
                    k++;
                }
            }
            else
                search(sign, smesh, len, f);//если это папка, рекурсивно переходим к поиску файла в ней
        }
    }
}
