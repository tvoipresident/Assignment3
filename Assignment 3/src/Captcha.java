import java.util.Random;

class Captcha {
    private static final String characters = "abcdefghijklmnopqrstuvwxyz0123456789";

    public static String generate() {
        java.util.Random random = new java.util.Random();
        StringBuilder captcha = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(characters.length());
            captcha.append(characters.charAt(index));
        }
        return captcha.toString();
    }
}