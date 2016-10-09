package nickrout.lenslauncher.util;

import nickrout.lenslauncher.R;

/**
 * Created by arelin on 10/8/16.
 */


public class QueryBuilder {

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
        return R.s
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
    public String createContact(Emoji emoji)
    {
        return String
                .format("{\"document\" : {\"emoji_name\": \"%s\", "
                                + "\"time\": \"%s\"}, \"safe\" : true}",
                        emoji.getName(), emoji.getDate());
    }

}