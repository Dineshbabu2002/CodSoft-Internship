import java.util.*;

public class QuizApplication {
    static class Question {
        String question;
        String[] options;
        int correctAnswer; // index: 0, 1, 2, or 3

        public Question(String question, String[] options, int correctAnswer) {
            this.question = question;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }
    }

    private static final int TIME_LIMIT = 15; // seconds per question
    private static int score = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<Question> questions = List.of(
            new Question("Which keyword is used to create a class in Java?",
                new String[]{"define", "struct", "class", "new"}, 2),
            new Question("Which method is the entry point of a Java program?",
                new String[]{"start()", "run()", "main()", "init()"}, 2),
            new Question("Which symbol is used to end a statement in Java?",
                new String[]{"#", ":", ";", "."}, 2),
            new Question("Which of these is not a primitive data type in Java?",
                new String[]{"int", "boolean", "String", "double"}, 2),
            new Question("Which keyword is used to inherit a class in Java?",
                new String[]{"inherits", "extends", "implements", "instanceof"}, 1)
        );

        System.out.println("=== Welcome to the Java Quiz ===");
        System.out.println("You have " + TIME_LIMIT + " seconds to answer each question.\n");

        int questionNumber = 1;

        for (Question q : questions) {
            System.out.println("Q" + questionNumber++ + ": " + q.question);
            for (int i = 0; i < q.options.length; i++) {
                System.out.println((i + 1) + ". " + q.options[i]);
            }

            long startTime = System.currentTimeMillis();
            System.out.print("Your answer (1-4): ");

            int userAnswer = -1;
            boolean timeout = false;

            while (System.currentTimeMillis() - startTime < TIME_LIMIT * 1000) {
                if (scanner.hasNextInt()) {
                    userAnswer = scanner.nextInt() - 1;
                    break;
                }
            }

            if (userAnswer == -1) {
                System.out.println("⏰ Time's up! No answer recorded.");
            } else if (userAnswer == q.correctAnswer) {
                System.out.println("✅ Correct!");
                score++;
            } else {
                System.out.println("❌ Incorrect! The correct answer was: " +
                        q.options[q.correctAnswer]);
            }

            System.out.println("--------------------------------------\n");
        }

        // Final Results
        System.out.println("🎉 Quiz Finished!");
        System.out.println("Your Score: " + score + "/" + questions.size());
    }
}
