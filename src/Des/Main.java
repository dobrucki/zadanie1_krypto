package Des;

import java.io.IOException;

public class Main {
    public static void main(String args[]) throws IOException{


        //5 publicznych metod:
        // encrypt - szyfruje podany string UTF-8 przyjmuje text do zaszyfrowania i klucz , zwraca zaszyfrowany text w postaci szesnastkowej zapisanej w stringu (dla łatwiejszego wyświetlenia)
        // decript - odszyfrowuje text UTF-8 przyjmuje zaszyfrowany text w postaci szesnastkowej zapisanej w stringu i klucz, zwraca odszyfrowany text
        // Klucz używany do szyfrowania i odszyfrowywania może zawierać dowolne znaki i być dowolnej długość ale wykorzystane zostaną tylko pierwsze 64 bity reszta zostanie ucięta
        // generateKey - przyjmuje długość klucza powinna być 8 zwraca randomowy klucz alfanumeryczny o wybranej długości
        // readFile - odczytuje text zapisany w pliku !!! MUSI BUĆ UTF-8 (np: notepad)
        // writeFile - zapisuje do pliku w formacie URF-8
        // potrzebne 3 pliki na oryginalny text , zaszyfrowany i odszyfrowany

        Des cipher = new Des();
        ReadWriteFileToString file = new ReadWriteFileToString();
        RandomKeyGenerator keyGen = new RandomKeyGenerator();

        String plainText = "Wół go pyta: Panie chrząszczu, po co pan tak brzęczy w gąszczu?";
        String key = "ąbwc";
        String cipherText = cipher.encrypt(plainText, key);
        String decryptedText = cipher.decrypt(cipherText, key);
        System.out.println("Orginalnt text: " + plainText);
        System.out.println("Klucz: " + key);
        System.out.println("Zaszyfrowany text szesnastkowo: " + cipherText);
        System.out.println("Odszyfrowany text: " + decryptedText);


        String fileText = file.readFile("d:/Projekty/DES/DES/plikiTextowe/plainText.txt");
        String cipherTextFile = cipher.encrypt(fileText, key);
        file.writeFile(cipherTextFile,"d:/Projekty/DES/DES/plikiTextowe/cipherText.txt");
        String fileCipherText = file.readFile("d:/Projekty/DES/DES/plikiTextowe/cipherText.txt");
        String fileDecryptedText = cipher.decrypt(fileCipherText, key);
        file.writeFile(fileDecryptedText,"d:/Projekty/DES/DES/plikiTextowe/encryptedText.txt");

        String genKey = keyGen.generateKey(8);
        System.out.println("Randomowy klucz: " + genKey);
    }
}

