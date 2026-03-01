public class Main {
    public static void main(String[] args) {
        System.out.println("=== Evaluation Pipeline ===");

        PlagiarismCheckable checker = new PlagiarismChecker();
        Gradable grader = new CodeGrader();
        ReportWritable writer = new ReportWriter();
        Rubric rubric = new Rubric();

        EvaluationPipeline pipeline = new EvaluationPipeline(checker, grader, writer, rubric);

        Submission sub = new Submission("23BCS1007", "public class A{}", "A.java");
        pipeline.evaluate(sub);
    }
}
