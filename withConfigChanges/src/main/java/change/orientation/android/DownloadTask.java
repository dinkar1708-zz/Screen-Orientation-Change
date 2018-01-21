package change.orientation.android;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class DownloadTask extends AsyncTask<String, String, String> {
    private DownloadCompletable downloadCompletable;
    private static final String TAG = DownloadTask.class.getSimpleName();

    private Context context;

    public DownloadTask(Context context, DownloadCompletable downloadCompletable) {
        this.context = context;
        this.downloadCompletable = downloadCompletable;
    }

    @Override
    protected String doInBackground(String... urls) {
        // we use the OkHttp library from https://github.com/square/okhttp
        Log.i(TAG, "doInBackground().....");
        if (isCancelled()) {
            Log.i(TAG, "doInBackground() is cancel is true");
            return "";
        }
        int i = 0;
        for (i = 0; i < 5; i++) {
            try {
                Thread.sleep(2 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress("data -" + i);
            Log.i(TAG, "downloading()....." + i);
        }
        return "Downloaded" + i;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        if (downloadCompletable != null) {
            downloadCompletable.downloadProgress(values[0]);
        }
    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
        Log.i(TAG, "onCancelled...string " + s);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        Log.i(TAG, "onCancelled...");
    }

    @Override
    protected void onPostExecute(String result) {
        Log.i(TAG, "onPostExecute()....." + result + " \n this is the activity object - " + context);
//        Toast.makeText(context, "onPostExecute " + result, Toast.LENGTH_SHORT).show();
        if (downloadCompletable != null) {
            downloadCompletable.completed(result);
        }


    }

    public interface DownloadCompletable {
        void completed(String msg);

        void downloadProgress(String data);
    }
}
