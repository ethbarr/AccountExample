package account;

import java.util.Calendar;

public class User {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getLastLoginTime() {
        return null; //not implemented
    }

    public int getLoginCount() {
        return 0; //not implemented
    }
}
