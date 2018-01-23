package com.example.withconfigchanges;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class WithoutHandleAsyncTaskExampleActivity extends AppCompatActivity {
    private static final String TAG = WithoutHandleAsyncTaskExampleActivity.class.getSimpleName();
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_example);

        progressDialog = ProgressDialog.show(this, "Async task", "Downloading.....");
        DownloadTask.DownloadCompletable downloadCompletable = new DownloadTask.DownloadCompletable() {
            @Override
            public void completed(String msg) {
                Log.i(TAG, "onCreate......main activity download completed callback......");
                // activity will not crash due to window changes - reason config is ture in manifies file
                if (progressDialog != null) {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void downloadProgress(String data) {
                Log.i(TAG, " downloadProgress " + data);
            }
        };

        DownloadTask downloadTask = new DownloadTask(this, downloadCompletable);

        downloadTask.execute(new String[]{"URL"});
    }

}
