package change.orientation.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView textView;

    private String holdingAsyncTaskData = "";
    private static final String DOWNLOAD_DATA_KEY_SAVE = "save_data_key";
    private DownloadTask downloadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.i(TAG, "onCreate......");

        textView = findViewById(R.id.activityTextView);

        downloadTask = new DownloadTask(this, downloadCompletable);

        downloadTask.execute(new String[]{"URL"});

        View btn = findViewById(R.id.btn);
        if (btn != null)
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, AsyncTaskExampleActivity.class));
                }
            });
    }


    DownloadTask.DownloadCompletable downloadCompletable = new DownloadTask.DownloadCompletable() {
        @Override
        public void completed(String msg) {
            Log.i(TAG, "onCreate......main activity download completed callback......");
            textView.setText("Data download from activity async downloadTask - " + msg);

        }

        @Override
        public void downloadProgress(String data) {
            Log.i(TAG, " downloadProgress " + data);
            holdingAsyncTaskData = data;
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        //does not destroy if below is true in manifiest
//        android:configChanges="orientation|screenSize|keyboardHidden"
//        To use android:configChanges attribute is also not recommended by Android.
        Log.i(TAG, "onStop......now cancelling async task...");
        downloadTask.cancel(true);
        downloadTask = null;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState......activity data to save " + holdingAsyncTaskData);
        //hey i am saving the state of downloaded data
        outState.putString(DOWNLOAD_DATA_KEY_SAVE, holdingAsyncTaskData);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String savedData = savedInstanceState.get(DOWNLOAD_DATA_KEY_SAVE).toString();
        Log.i(TAG, "onRestoreInstanceState......activity savedData " + savedData);
        //hey i am again restoring the data which was downloaded and showing to user
        textView.setText(savedData);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
