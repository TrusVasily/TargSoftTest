import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Parse {
    final static String filePath = "src\\main\\resources\\csv.csv";

    public static void parserCSV(String merchant, String fromDate, String toDate) {
        List<Transaction> transactions = new ArrayList<>();
        try (FileReader fr = new FileReader(filePath); Scanner sc = new Scanner(fr)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.contains(merchant)) {
                    List<String> elements = Arrays.stream(line.split(","))
                            .map(String::trim)
                            .collect(Collectors.toList());
                    if (elements.size() == 6) {
                        transactions = transactions.stream()
                                .filter(tra -> !tra.getID().equals(elements.get(5)))
                                .collect(Collectors.toList());
                    } else {
                        Transaction transaction = Transaction.createTransaction(elements);
                        transactions.add(transaction);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        LocalDateTime transactFromDate = Transaction.dateParse(fromDate);
        LocalDateTime transactToDate = Transaction.dateParse(toDate);

        transactions = transactions.stream()
                .filter(transaction -> transaction.getDate().isAfter(transactFromDate) || transaction.getDate().isEqual(transactFromDate))
                .filter(transaction -> transaction.getDate().isBefore(transactToDate) || transaction.getDate().isEqual(transactToDate))
                .collect(Collectors.toList());

        long transCount = transactions.size();
        System.out.println("Number of transactions = " + transCount);

        BigDecimal averageValue = transactions.stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(new BigDecimal(transCount));
        System.out.println("Average Transaction Value = " + averageValue);
    }
}
