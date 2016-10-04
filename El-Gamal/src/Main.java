import java.io.*;
import java.util.Random;
import java.lang.Math;
import java.util.Vector;
import java.util.Scanner;

public class Main {
    static String ab = "hhhhhhhhhhhабвгдеёжзhийклмнопрhстуфхцчшщhъыьэюя012h3456789,.h;:@-=+<>[h]{}/|!?%*h ";

    private static int gcd (int a, int b) {
        if (b == 0)
            return a;
        else
            return gcd (b, a % b);
    } //НОД

    private static int phi (int n) {
        int result = n;
        for (int i=2; i*i<=n; ++i)
            if (n % i == 0) {
                while (n % i == 0)
                    n /= i;
                result -= result / i;
            }
        if (n > 1)
            result -= result / n;
        return result;
    } //Ф(i)

    private static int pkoren(int p){
        if (p == 2) return 1;
        Vector fact = new Vector();
        int ph = phi(p), n = ph;
        for (int i = 2; i * i <= n; ++i)
            while (n % i == 0){
                fact.add(i);
                n /= i;
            }
        if (n > 1)
            fact.add(n);
        for (int res = 2; res <= p; ++res){
            boolean ok = true;
            if (gcd(res, p) != 1)
                continue;
            for (int i = 0; i < fact.size() && ok; ++i)
                ok &= binpow(res, ph / (Integer)fact.get(i)) % p != 1;
            if (ok) return res;
        }
        return -1;
    } //первообразный корень

    private static boolean prime(int n){
        for(int i=2;i<=Math.sqrt(n);i++)
            if(n % i==0)
                return false;
        return true;
    } //проверка на простоту

//----------------------------------------------------------------------------------------------------------------------

    private static int binpow(int a, int n){
        if (n == 0)
            return 1;
        if (n % 2 == 1)
            return binpow(a, n - 1) * a;
        else{
            int b = binpow(a, n / 2);
            return b * b;
        }
    } //быстрое возведение в степень

    private static Vector longpow(int a, int b){
        int i = 1, ram = 0, x, m = 0;
        Vector A = new Vector();
        while (a / binpow(10, i) != 0){
            A.add((a % binpow(10, i) - m) / binpow(10, i - 1));
            m = a % binpow(10, i);
            i++;
        }
        A.add(a / binpow(10, i - 1));
        Vector C1 = new Vector();
        Vector C = new Vector();
        if (b == 0) {
            C1.add(1);
        }
        else
            C1 = A;
        for (i = 0; i < b - 1; i++){
            for (int j = 0; j < A.size(); j++){
                for (int l = 0; l < C1.size(); l++){
                    if (j + l >= C.size())
                        C.add(0);
                    x = (Integer)C1.elementAt(l) * (Integer)A.elementAt(j) + ram + (Integer)C.elementAt(j + l);
                    C.remove(j + l);
                    C.add(j + l, x % 10);
                    ram = x / 10;
                    if (l == C1.size() - 1 && ram != 0){
                        C.add(j + l + 1, ram);
                        ram = 0;
                    }
                }
                ram = 0;
            }
            ram = 0;
            C1 = C;
            C = new Vector();
        }
        return C1;
} //возведение в степень

    private static Vector longmulti(Vector A, int b){
        int i = 1, ram = 0, x, m = 0;
        Vector B = new Vector();
        while (b / binpow(10, i) != 0){
            B.add((b % binpow(10, i) - m) / binpow(10, i - 1));
            m = b % binpow(10, i);
            i++;
        }
        B.add(b / binpow(10, i - 1));
        Vector C = new Vector();
            for (int j = 0; j < B.size(); j++){
                for (int l = 0; l < A.size(); l++){
                    if (j + l >= C.size())
                        C.add(0);
                    x = (Integer)A.elementAt(l) * (Integer)B.elementAt(j) + ram + (Integer)C.elementAt(j + l);
                    C.remove(j + l);
                    C.add(j + l, x % 10);
                    ram = x / 10;
                    if (l == A.size() - 1 && ram != 0){
                        C.add(j + l + 1, ram);
                        ram = 0;
                    }
                }
                ram = 0;
            }
        return C;
    } //умножение

    private static int longmod(Vector A, int b){
        int i = 1, j, k = 0, x, y = 0, z = 0, m = 0;
        Vector B = new Vector();
        while (b / binpow(10, i) != 0){
            B.add((b % binpow(10, i) - m) / binpow(10, i - 1));
            m = b % binpow(10, i);
            i++;
        }
        B.add(b / binpow(10, i - 1));
        if (A.size() < B.size()){
           for (i = 0; i < A.size(); i++) {
               y += (Integer)A.elementAt(i) * binpow(10, i);
           }
            return y;
        }
        i = 0;
        while (A.size() >= B.size()){
            for (j = 0; j < B.size(); j++){
                if ((Integer)B.elementAt(B.size() - j - 1) > (Integer)A.elementAt(A.size() - j - 1) && A.size() > B.size()){
                    k++;
                    break;
                }
            }
            x = 0;
            for (j = 0; j < B.size() + k; j++)
                x += (Integer)A.elementAt(A.size() - j - 1) * binpow(10, B.size() + k - j - 1);// неправильно вроде
            z = x % b;
            if (A.size() == B.size() + k)
                break;
            if (A.size() > B.size()) {
                int l = 1;
                while (l <= B.size() + k){
                    A.remove(A.size() - 1);
                    l++;
                }
                l = 1;
                m = 0;
                while (z / binpow(10, l) != 0) {
                    A.add(A.size(), (z % binpow(10, l) - m) / binpow(10, l - 1));
                    m = z % binpow(10, l);
                    l++;
                }
                A.add(A.size(), z / binpow(10, l - 1));
                if (A.size() < B.size()){
                    for (i = 0; i < A.size(); i++) {
                        y += (Integer)A.elementAt(i) * binpow(10, i);
                    }
                    return y;
                }
            }
            k = 0;
            i++;
        }
        return z;
    } //деление по модулю

//----------------------------------------------------------------------------------------------------------------------

    private static int[] crypto(int M, int[] keyMas){
        Random rand = new Random();
        int k = 1, a, b;
        System.out.print("\n");
        System.out.print(M);
        System.out.print("\n");
        while (k == 1)
            k = rand.nextInt(keyMas[0] - 1);
        a = longmod(longpow(keyMas[1], k), keyMas[0]);// проверить!!!
        b = longmod(longmulti(longpow(keyMas[2], k), M), keyMas[0]);//проверить!!!
        return new int[] {a, b};
    } //шифрование

    private static int[] key() throws IOException{
        int p, x, g, y;
        Random rand = new Random();
        Scanner in = new Scanner(System.in);
        System.out.print("Введите P:   ");
        p = in.nextInt();
        while (!prime(p) || p <= 10){
            System.out.print("Введите P:   ");
            p = in.nextInt();
        }
        x = rand.nextInt(p);
        while (x == 1)
            x = rand.nextInt(p);
        g = pkoren(p);//нужно проверить!!!
        y = longmod(longpow(g, x), p); //нужно проверить!!!
        System.out.print(p);
        System.out.print("\n");
        System.out.print(x);
        System.out.print("\n");
        System.out.print(g);
        System.out.print("\n");
        System.out.print(y);
        return new int[] {p, g, y}; //y
    } //генерания ключа

    public static void elgamal_cr() throws IOException{ //управляющая функция при шифровании
        String str = "", str1 = "";
        int M, M1 = 0, a[], i;
        int keyMas[] = new int[0];
        try {
            keyMas = key();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStreamReader in = new InputStreamReader(new FileInputStream
                ("C:\\Users\\INTELLY_POWER\\IdeaProjects\\El-Gamal\\files\\input.txt"), "Cp1251");
        File f = new File("C:\\Users\\INTELLY_POWER\\IdeaProjects\\El-Gamal\\files\\input.txt");
        char[] r = new char[(int)f.length()];
        in.read(r);
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream
                ("C:\\Users\\INTELLY_POWER\\IdeaProjects\\El-Gamal\\files\\input - 1.txt"));
        for (i = 0; i < (int)f.length(); i++){
            M = ab.indexOf(r[i]);
            if (M != -1 && M != 0)
                str += Integer.toString(M);
        }
        while (str.length() != 0){
            M = 0;
            while (M < keyMas[0] && str.length() != 0) {
                str1 += str.charAt(0);
                M = Integer.valueOf(str1);
                if (M < keyMas[0]) {
                    M1 = M;
                    str = str.substring(1, str.length());
                }
            }
            str1 = "";
            a = crypto(M1, keyMas);
            out.write(Integer.toString(a[0]));
            out.write(" ");
            out.write(Integer.toString(a[1]));
            out.write(" ");
        }
        out.close();
    } //управляющая функция при шифровании

//----------------------------------------------------------------------------------------------------------------------

    public static void elgamal_descr() throws IOException{
        int i, k = 0, x1 = 0, y, p, x, M;
        Scanner in1 = new Scanner(System.in);
        System.out.print("Введите P:   ");
        p = in1.nextInt();
        System.out.print("Введите X:   ");
        x = in1.nextInt();
        String str = "", t = "", m = "";
        InputStreamReader in = new InputStreamReader(new FileInputStream
                ("C:\\Users\\INTELLY_POWER\\IdeaProjects\\El-Gamal\\files\\input - 1.txt"), "Cp1251");
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream
                ("C:\\Users\\INTELLY_POWER\\IdeaProjects\\El-Gamal\\files\\input - 2.txt"));
        File f = new File("C:\\Users\\INTELLY_POWER\\IdeaProjects\\El-Gamal\\files\\input - 1.txt");
        char[] r = new char[(int)f.length()];
        in.read(r);

        Vector q1, q2;
        int q3;
        for (i = 0; i < (int)f.length(); i++){
            if (k == 0){
                t += r[i];
                if (!t.equals(" ")){
                    str += t;
                }
                    else{
                    x1 = Integer.valueOf(str);
                    str = "";
                    k = 1;
                }
                t = "";
            }
            else{
                t += r[i];
                if (!t.equals(" ")){
                    str += t;
                }
                else{
                    y = Integer.valueOf(str);
                    str = "";
                    k = 0;
                    q1 = longpow(x1, p - x - 1);
                    q2 = longmulti(q1, y);
                    q3 = longmod(q2, p);
                    M = q3;
                    m += Integer.toString(M);
                }
                t = "";
            }
        }
        String sub;
        char a;
        int h;
        for (i = 0; i < m.length() - 1; i += 2){
            sub = m.substring(i, i + 2);
            h = Integer.valueOf(sub);
            a = ab.charAt(h % ab.length());
            out.write(a);
        }
        out.close();
    } //управляющая функция при расшифровании

//----------------------------------------------------------------------------------------------------------------------

    public static void main(String args[]) throws Exception{
        elgamal_cr();
        //elgamal_descr();
    }
}
