import java.util.*;
import java.util.concurrent.*;

public class QuizApp {
    static class Question {
        String text;
        String[] options;
        char correct;
        
        Question(String text, String[] options, char correct) {
            this.text = text;
            this.options = options;
            this.correct = correct;
        }
    }

    public static void main(String[] args) {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question(
            "What is 2+2?", 
            new String[]{"A. 3", "B. 4", "C. 5", "D. 6"}, 
            'B'
        ));
        questions.add(new Question(
            "Capital of Japan?",
            new String[]{"A. Kyoto", "B. Osaka", "C. Tokyo", "D. Hiroshima"}, 
            'C'
        ));

        Scanner sc = new Scanner(System.in);
        ExecutorService executor = Executors.newCachedThreadPool();
        int score = 0;
        List<String> results = new ArrayList<>();

        System.out.println("QUIZ STARTED!\n");

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println("Q" + (i+1) + ": " + q.text);
            Arrays.stream(q.options).forEach(System.out::println);
            
            Future<String> future = executor.submit(() -> 
                sc.nextLine().trim().toUpperCase()
            );

            try {
                String ans = future.get(10, TimeUnit.SECONDS);
                if (!ans.isEmpty() && ans.charAt(0) == q.correct) {
                    score++;
                    results.add("Q" + (i+1) + ": Correct");
                } else {
                    results.add("Q" + (i+1) + ": Wrong (Correct: " + q.correct + ")");
                }
            } catch (TimeoutException e) {
                future.cancel(true);
                results.add("Q" + (i+1) + ": Timeout (Correct: " + q.correct + ")");
            } catch (Exception e) {
                results.add("Q" + (i+1) + ": Error");
            }
            System.out.println();
        }

        executor.shutdown();
        System.out.println("\nRESULTS:");
        results.forEach(System.out::println);
        System.out.println("Final Score: " + score + "/" + questions.size());
    }
}