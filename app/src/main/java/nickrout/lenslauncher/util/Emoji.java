package nickrout.lenslauncher.util;

import java.util.Date;

/**
 * Created by arelin on 10/8/16.
 */

public class Emoji {

    public String _name;
    public String _date;

    Emoji(String name, Date date) {
        _name = name;
        _date = date.toString();
    }

    String getName() {
        return _name;
    }

    void setName(String name) {
       _name = name;
    }

    String getDate() {
        return _date;
    }

    void setDate(Date date) {
        _date = date.toString();
    }
}
