package nickrout.lenslauncher.util;

/**
 * Created by arelin on 10/8/16.
 */


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import android.os.AsyncTask;


    public class SaveAsyncTask extends AsyncTask<Emoji, Void, Boolean> {

        QueryBuilder _queryBuilder;

        void setQueryBuilder(QueryBuilder qb) {
            _queryBuilder = qb;
        }

        @Override
        protected Boolean doInBackground(Emoji... arg0) {
            try
            {
                Emoji contact = arg0[0];

                QueryBuilder qb = _queryBuilder;

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost request = new HttpPost(qb.buildContactsSaveURL());

                StringEntity params =new StringEntity(qb.createEvent(contact));
                request.addHeader("content-type", "application/json");
                request.setEntity(params);

                HttpResponse response = httpClient.execute(request);

                if(response.getStatusLine().getStatusCode()<205)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            } catch (Exception e) {
                //e.getCause();
                String val = e.getMessage();
                String val2 = val;
                return false;
            }

        }

    }

