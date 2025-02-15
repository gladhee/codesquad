import java.util.Scanner;

public class Input {

    private static final Scanner scanner = new Scanner(System.in);

    public static String prompt() {
        System.out.println("SQL> ");
        return scanner.nextLine();
    }

}

/*
*
*
        //        String input = """
//            CREATE TABLE billboard (singer String, year Numeric, song String);
//            INSERT INTO billboard (singer, year, song) VALUES ("BTS", 2020, "Dynamite");
//            INSERT INTO billboard (singer) VALUES ("BTS");
//            UPDATE billboard SET singer = "BTS", year = 2021 WHERE year = 2020;
//            DELETE FROM billboard WHERE singer = "BTS";
//            DROP TABLE billboard;
//            """;
        String input = "CREATE TABLE billboard (singer String, year Numeric, song String);";
//        String input = "INSERT INTO billboard (singer, year, song) VALUES (\"BTS\", 2020, \"Dynamite\");";
* */