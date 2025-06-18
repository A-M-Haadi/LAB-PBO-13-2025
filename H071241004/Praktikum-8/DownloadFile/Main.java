package DownloadFile;

import java.util.*;
import java.util.concurrent.*;

public class Main {

    static int successfulDownloads = 0;
    static int completedDownloads = 0;
    static int totalFiles;
    static boolean allDone = false;
    static ArrayList<Result> results = new ArrayList<>();

    public static synchronized void recordResult(int fileId, int duration, String threadName) {
        completedDownloads++;
        String status = duration <= 2 ? "Success" : "Timeout";
        if (status.equals("Success")) {
            successfulDownloads++;
        }
        Result result = new Result(fileId, threadName, duration, status);
        results.add(result);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of files to download: ");
        totalFiles = scanner.nextInt();

        ExecutorService downloadExecutor = Executors.newFixedThreadPool(3);
        ExecutorService uiExecutor = Executors.newSingleThreadExecutor();

        long startTime = System.currentTimeMillis();

        uiExecutor.submit(() -> {
            while (!allDone) {
                int elapsedTime = (int) ((System.currentTimeMillis() - startTime) / 1000);
                System.out.println("Downloading files... (" + elapsedTime + "s)");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        for (int i = 1; i <= totalFiles; i++) {
            int fileId = i;
            downloadExecutor.submit(() -> {
                Random random = new Random();
                int downloadDuration = random.nextInt(3) + 1;
                try {
                    Thread.sleep(downloadDuration * 1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                recordResult(fileId, downloadDuration, Thread.currentThread().getName());
            });
        }

        downloadExecutor.shutdown();
        try {
            if (!downloadExecutor.awaitTermination(60, TimeUnit.SECONDS)) {
                downloadExecutor.shutdownNow();
            }
        } catch (InterruptedException e) {
            downloadExecutor.shutdownNow();
        }

        allDone = true;
        uiExecutor.shutdown();
        try {
            uiExecutor.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            uiExecutor.shutdownNow();
        }

        long endTime = System.currentTimeMillis();
        int totalTime = (int) ((endTime - startTime) / 1000);

        System.out.println("--------------------------------------------------");
        System.out.println("                  Detailed Report                 ");
        System.out.println("--------------------------------------------------");
        System.out.printf("%-8s | %-18s | %-8s | %-8s%n",
                "File ID", "Thread", "Duration", "Status");
        System.out.println("--------------------------------------------------");

        results.sort(Comparator.comparingInt(r -> r.fileId));

        for (Result r : results) {
            System.out.printf("%-8d | %-18s | %-8s | %-8s%n",
                    r.fileId, r.threadName, r.duration + "s", r.status);
        }

        System.out.println("--------------------------------------------------");
        System.out.println("                     Summary                      ");
        System.out.println("--------------------------------------------------");
        System.out.println("Successful downloads : " + successfulDownloads);
        System.out.println("Failed downloads     : " + (totalFiles - successfulDownloads));
        System.out.printf("Total time           : %ds%n", totalTime);
    }
}