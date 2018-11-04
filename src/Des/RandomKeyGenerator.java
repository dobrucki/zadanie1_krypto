package Des;

import java.security.SecureRandom;

public class RandomKeyGenerator {

    public String generateKey(int len){

        SecureRandom random = new SecureRandom();
        String gKey = "";

        char[] options = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] key = new char[len];
        for (int i = 0; i < len; i++) {
            gKey += options[random.nextInt(options.length)];//generuje int z zakresu od zera options.length
        }
        return gKey;
    }

}
