package change.orientation.android;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import java.util.ConcurrentModificationException;

/**
 * Created by dinka on 1/20/2018.
 */

public class Util {

    public static void showAlert(Context context) {
        new AlertDialog.Builder(context)
                .setTitle("Alert")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).setNegativeButton("No", null).show();
    }
}
