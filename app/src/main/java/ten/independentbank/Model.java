package ten.independentbank;

/**
 * Created by Shubham on 30-Oct-17.
 */

public class Model {
    private String Orderid;
    private String Mobile_Number;
    private String Amount;
    private String status;
    private String date;

    public Model(String Orderid, String Mobile_Number, String Amount, String status, String date) {
        this.Orderid = Orderid;
        this.Mobile_Number = Mobile_Number;
        this.Amount = Amount;
        this.status = status;
        this.date = date;
    }

    public String getOrderid() {
        return Orderid;
    }

    public String getMobile_Number() {
        return Mobile_Number;
    }

    public String getAmount() {
        return Amount;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }
}
