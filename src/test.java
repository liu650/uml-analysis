public class test {

    public static void main(String[] args) {

        // test regex
        System.out.println("PASS~~~~~~~~~~~~");
        System.out.println("ADAD.java".matches("(?i).+\\.java$"));
        System.out.println("helo.java.java".matches("(?i).+\\.java$"));
        System.out.println("helo.JAVA".matches("(?i).+\\.java$"));
        System.out.println("helo.JAVA.JAVA".matches("(?i).+\\.java$"));
        System.out.println("..java".matches("(?i).+\\.java$"));
        System.out.println("C:/hello/ad.java".matches("(?i).+\\.java$"));

        System.out.println("FALSE~~~~~~~~~~~~");
        System.out.println("hellojava".matches("(?i).+\\.java$"));
        System.out.println("Hello.java.".matches("(?i).+\\.java$"));
        System.out.println(".java".matches("(?i).+\\.java$"));
        System.out.println("java".matches("(?i).+\\.java$"));
        System.out.println("java".matches("(?i).+\\.java$"));
    }
}
