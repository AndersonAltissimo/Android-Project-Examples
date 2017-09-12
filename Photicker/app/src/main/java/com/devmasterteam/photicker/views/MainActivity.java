package com.devmasterteam.photicker.views;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.devmasterteam.photicker.R;
import com.devmasterteam.phototicker.utils.ImageUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final ViewHolder vh = new ViewHolder();
    private ImageView imageSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        List<Integer> lstImages = new ArrayList<>();
        lstImages = ImageUtil.getImageList();

        this.vh.relativePhotoContent  = (RelativeLayout) findViewById(R.id.relativePhotoContentDraw);
        final LinearLayout linearContent = (LinearLayout) findViewById(R.id.linearScrollContent);

        for (Integer imageId: lstImages) {
            ImageView image = new ImageView(this);
            image.setImageBitmap(ImageUtil.decodeSampledBitmapFromResource(getResources(), imageId, 70,70));
            image.setPadding(20,10,20,10);

            BitmapFactory.Options dimensions = new BitmapFactory.Options();
            dimensions.inJustDecodeBounds = true;

            BitmapFactory.decodeResource(getResources(), imageId, dimensions);

            final int width = dimensions.outWidth;
            final int heigth = dimensions.outHeight;

            image.setOnClickListener(onClickImageOption(this.vh.relativePhotoContent, imageId, width, heigth));
            linearContent.addView(image);
        }

        this.vh.linearControlPanel = (LinearLayout) findViewById(R.id.LinearControlPanel);
        this.vh.linearSharePanel = (LinearLayout) findViewById(R.id.LinearSharePanel);
        this.vh.btnZoomIn = (ImageView) findViewById(R.id.btnZoomIn);
        this.vh.btnZoomOut = (ImageView) findViewById(R.id.btnZoomOut);
        this.vh.btnRotateLeft = (ImageView) findViewById(R.id.btnRotateLeft);
        this.vh.btnRotateRight = (ImageView) findViewById(R.id.btnRotateRight);
        this.vh.btnFinish = (ImageView) findViewById(R.id.btnFinish);
        this.vh.btnRemove = (ImageView) findViewById(R.id.btnRemove);
        this.vh.relativePhotoContent = (RelativeLayout) findViewById(R.id.relativePhotoContentDraw);

        this.setListeners();

    }

    private void setListeners() {
        findViewById(R.id.btnZoomIn).setOnClickListener(this);
        findViewById(R.id.btnZoomOut).setOnClickListener(this);
        findViewById(R.id.btnRotateLeft).setOnClickListener(this);
        findViewById(R.id.btnRotateRight).setOnClickListener(this);
        findViewById(R.id.btnFinish).setOnClickListener(this);
        findViewById(R.id.btnRemove).setOnClickListener(this);
    }

    private View.OnClickListener onClickImageOption(final RelativeLayout relativeLayout, final Integer imageId, int width, int heigth) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ImageView image = new ImageView(MainActivity.this);
                image.setBackgroundResource(imageId);
                relativeLayout.addView(image);

                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) image.getLayoutParams();
                layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
                layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);

                imageSelected = image;

                toggleControlPainel(true);

                image.setOnTouchListener(new View.OnTouchListener(){
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {

                        float x, y;

                        switch (event.getAction()){
                            case MotionEvent.ACTION_DOWN:
                                imageSelected = image;
                                toggleControlPainel(true);
                                break;

                            case MotionEvent.ACTION_MOVE:
                                int coords[] = {0,0};
                                relativeLayout.getLocationOnScreen(coords);

                                x = (event.getRawX() - (image.getWidth()/2));
                                y = (event.getRawY() - (coords[1] + 100) + (image.getHeight() /2));
                                image.setX(x);
                                image.setY(y);
                                break;

                            case  MotionEvent.ACTION_UP :
                                break;
                        }
                        return true;
                    }
                });
            }
        };
    }

    private void toggleControlPainel(boolean showControls) {
        if (showControls){
            this.vh.linearControlPanel.setVisibility(View.VISIBLE);
            this.vh.linearSharePanel.setVisibility(View.GONE);
        } else {
            this.vh.linearControlPanel.setVisibility(View.GONE);
            this.vh.linearSharePanel.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnZoomIn:
                ImageUtil.handleZoomIn(this.imageSelected);
                break;

            case R.id.btnZoomOut:
                ImageUtil.handleZoomOut(this.imageSelected);
                break;

            case R.id.btnRotateLeft:
                ImageUtil.handleRotateLeft(this.imageSelected);
                break;

            case R.id.btnRotateRight:
                ImageUtil.handleRotateRight(this.imageSelected);
                break;

            case R.id.btnFinish:
                this.toggleControlPainel(false);
                break;

            case R.id.btnRemove:
                this.vh.relativePhotoContent.removeView(this.imageSelected);
                break;
        }

    }

    private static class ViewHolder {
        LinearLayout linearSharePanel;
        LinearLayout linearControlPanel;

        ImageView btnZoomIn;
        ImageView btnZoomOut;
        ImageView btnRotateLeft;
        ImageView btnRotateRight;
        ImageView btnFinish;
        ImageView btnRemove;

        RelativeLayout relativePhotoContent;
    }
}
