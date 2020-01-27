import org.junit.Test;

public class TransactionParserTest {
    String merchant = "Kwik-E-Mart";
    String fromDate = "20/08/2018 12:00:00";
    String toDate = "20/08/2018 14:00:00";

    @Test
    public void analyse() {
        Parse.parserCSV(merchant, fromDate, toDate);
    }

}
