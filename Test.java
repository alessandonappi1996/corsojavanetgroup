import java.util.*;

class test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Quanti numeri vuoi insrire? ");
        int n = sc.nextInt();
        List<Integer> listanumeri = new ArrayList<>();
        int i = 0;

        while (i < n) {
            System.out.print("Inserisci il " + i+ " numero: ");
            int numero = sc.nextInt();
            listanumeri.add(numero);
            i++;
        }

        System.out.print("Ecco la lista di numeri inseriti : " + listanumeri);

    }
}