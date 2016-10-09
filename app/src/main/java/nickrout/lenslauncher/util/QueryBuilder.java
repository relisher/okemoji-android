package nickrout.lenslauncher.util;

import android.content.Context;

import nickrout.lenslauncher.R;

/**
 * Created by arelin on 10/8/16.
 */


public class QueryBuilder {

    private Context _context;

    QueryBuilder(Context ctx) {
        _context = ctx;
    }
    /**
     * Specify your database name here
     * @return
     */
    public String getDatabaseName() {
        return "emojimeter";
    }

    /**
     * Specify your MongoLab API here
     * @return
     */
    public String getApiKey() {
        return _context.getString(R.string.mongo_key);
    }

    /**
     * This constructs the URL that allows you to manage your database,
     * collections and documents
     * @return
     */
    public String getBaseUrl()
    {
        return "https://api.mongolab.com/api/1/databases/"+getDatabaseName()+"/collections/";
    }

    /**
     * Completes the formating of your URL and adds your API key at the end
     * @return
     */
    public String docApiKeyUrl()
    {
        return "?apiKey="+getApiKey();
    }

    /**
     * Returns the docs101 collection
     * @return
     */
    public String documentRequest()
    {
        return "emoji";
    }

    /**
     * Builds a complete URL using the methods specified above
     * @return
     */
    public String buildContactsSaveURL()
    {
        return getBaseUrl()+documentRequest()+docApiKeyUrl();
    }

    /**
     * Formats the contact details for MongoHQ Posting
     * @param emoji: Details of the person
     * @return
     */
    public String createEvent(Emoji emoji)
    {
        return String
                .format("{\"document\" : {\"emoji_name\": \"%s\", "
                                + "\"time\": \"%s\"}, \"safe\" : true}",
                        emoji.getName(), emoji.getDate());
    }

}