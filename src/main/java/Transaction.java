import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Transaction {
    private String ID;
    private LocalDateTime date;
    private BigDecimal amount;
    private String merchant;
    private TransactionType type;
    private String related;

    public Transaction(){

    }

    public Transaction(String ID, LocalDateTime date, BigDecimal amount, String merchant, TransactionType type, String related) {
        this.ID = ID;
        this.date = date;
        this.amount = amount;
        this.merchant = merchant;
        this.type = type;
        this.related = related;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public String getRelated() {
        return related;
    }

    public void setRelated(String related) {
        this.related = related;
    }

    public static Transaction createTransaction(List<String> elements) {
        Transaction transaction = new Transaction();
        transaction.setID(elements.get(0));
        transaction.setDate(dateParse(elements.get(1)));
        transaction.setAmount(new BigDecimal(elements.get(2)));
        transaction.setMerchant(elements.get(3));
        transaction.setType(TransactionType.valueOf(elements.get(4)));

        return transaction;
    }

    public static LocalDateTime dateParse(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return LocalDateTime.parse(date.trim(), formatter);
    }
}
