package nickrout.lenslauncher.util;

import java.util.Date;

/**
 * Created by arelin on 10/8/16.
 */

public class Emoji {

    private String _name;
    private String _date;

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
