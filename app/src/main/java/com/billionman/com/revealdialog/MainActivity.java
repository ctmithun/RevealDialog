package com.billionman.com.revealdialog;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button buttonClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonClick = (Button) findViewById(R.id.button);
        buttonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogBox();
            }
        });
    }

    private void showDialogBox() {
        final View dialogView = View.inflate(this,R.layout.dialog,null);
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(dialogView);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        //animation
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialog) {
                showAnim(dialogView, (Dialog) dialog, true);
            }
        });

        dialog.show();
        ImageButton okButton = (ImageButton) dialog.findViewById(R.id.buttonDialog);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View dv) {
                if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    dialog.dismiss();
                } else {
                    showAnim(dialogView, dialog, false);
                }
                Toast.makeText(getApplicationContext(),"Clicked OK",Toast.LENGTH_LONG).show();
            }
        });

    }



    private void showAnim(View dialogView, Dialog dialog, boolean b) {
        final View view = dialogView.findViewById(R.id.dialogLayout);

        int cx = (view.getWidth()/2);
        int cy = (view.getHeight());

        float maxRadius = (float) Math.sqrt(cx * cx / 4 + cy * cy / 4);

        if(b) {
            Animator anim = ViewAnimationUtils.createCircularReveal(view, cx / 2, cy / 2, 0, maxRadius);
            view.setVisibility(View.VISIBLE);
            anim.start();
        } else {
            Animator anm = ViewAnimationUtils.createCircularReveal(view, cx / 2, cy / 2, maxRadius, 0);
            dialog.dismiss();
            view.setVisibility(View.INVISIBLE);
        }
    }

}
