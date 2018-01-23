package change.orientation.android;

import android.app.Fragment;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private static final String TAG = MainActivityFragment.class.getSimpleName();
//    private static final String EDIT_TEXT_VAL = "edit_text_save" ;

    private EditText editText;
    private MyDataObject data;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "onCreate......main fragment");
        // keep fragment live
        setRetainInstance(true);

        Log.i(TAG, "onCreate......main fragment starting asynch task");

        DownloadTask task = new DownloadTask(getActivity(), new DownloadTask.DownloadCompletable() {
            @Override
            public void completed(String msg) {
                Log.i(TAG, "onCreate......main fragment download completed callback......it was not interrupted," +
                        " i handled my all downloadable files");
                editText.setText("Data download from fragment async task - " + msg);
            }

            @Override
            public void downloadProgress(String data) {

            }
        });
        task.execute(new String[]{"URL"});
    }

    public void setData(MyDataObject data) {
        this.data = data;
    }

    public MyDataObject getData() {
        return data;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView......main fragment");
        // does not destroy this fragment in screen rotation
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        editText = view.findViewById(R.id.helloEditText);
        Log.i(TAG, "onCreateview...fragment object- " + this);

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putString(EDIT_TEXT_VAL, editText.getText().toString());
        Log.i(TAG, "onSaveInstanceState......fragment");

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated......fragment");
    }

}
