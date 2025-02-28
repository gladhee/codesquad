public class Application {

    public static void main(String[] args) {
        System.out.println("> 지구날짜는? 2024-01-01");

        EarthDate earthDate = new EarthDate(2024, 1, 1);
        ProgressBar progressBar = new ProgressBar(5000, 50);
        AgeCalculator ageCalculator = new AgeCalculator(earthDate);

        while (!progressBar.isComplete()) {
            ageCalculator.performChunk();
            progressBar.display();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        progressBar.display();
        System.out.println(ageCalculator.getResult());
    }

}
