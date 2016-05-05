package dockusan.com.androidbaseframework.asyntasks;

import android.os.AsyncTask;

/**
 * Created by SF on 05/05/2016.
 */
public class ExampleAsyntask extends AsyncTask<Void, Void, Void> {
    //Pass your parameter through constructor
    public ExampleAsyntask() {
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected Void doInBackground(Void... params) {
        return null;
    }
}
