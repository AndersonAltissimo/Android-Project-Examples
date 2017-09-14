package com.devmasterteam.photicker.utils;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.devmasterteam.photicker.R;
import com.devmasterteam.photicker.views.MainActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by anderson on 13/09/17.
 */

public class SocialUtil {

    private static final String HASHTAG = "#PhotickerApp";

    public static void shareImageOnFacebook(MainActivity mainActivity, RelativeLayout relativePhotoContent, View v) {
    }

    public static void shareImageOnInstagram(MainActivity mainActivity, RelativeLayout relativePhotoContent, View v) {
        PackageManager pkManager = mainActivity.getPackageManager();

        try {
            pkManager.getPackageInfo("com.instagram.android", 0);

            try {
                Bitmap image = ImageUtil.drawBitmap(relativePhotoContent);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

                image.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

                File file = new File(Environment.getExternalStorageDirectory()+ File.separator + "temp_file.jpg");

                try {
                    file.createNewFile();
                    FileOutputStream fo = new FileOutputStream(file);
                    fo.write(outputStream.toByteArray());

                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///sdcard/temp_fiel.jpg"));
                    sendIntent.setType("image/*");
                    sendIntent.setPackage("com.instragram.android");

                    v.getContext().startActivity(Intent.createChooser(sendIntent, mainActivity.getString(R.string.share_image)));

                } catch (FileNotFoundException e) {
                    Toast.makeText(mainActivity, R.string.unexpected_error, Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(mainActivity, R.string.unexpected_error, Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e){
                Toast.makeText(mainActivity, R.string.instagram_not_installed, Toast.LENGTH_SHORT).show();
            }
        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(mainActivity, R.string.instagram_not_installed, Toast.LENGTH_SHORT).show();
        }
    }

    public static void shareImageOnTwitter(MainActivity mainActivity, RelativeLayout relativePhotoContent, View v) {
        PackageManager pkManager = mainActivity.getPackageManager();

        try {
            pkManager.getPackageInfo("com.twitter.android", 0);

            try{
                Intent tweetIntent = new Intent(Intent.ACTION_SEND);
                Bitmap image = ImageUtil.drawBitmap(relativePhotoContent);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();

                image.compress(Bitmap.CompressFormat.JPEG, 100, stream);

                File file = new File(Environment.getExternalStorageDirectory() + File.separator + "temp_file.jpg");
                file.createNewFile();
                FileOutputStream fo = new FileOutputStream(file);
                fo.write(stream.toByteArray());

                tweetIntent.putExtra(Intent.EXTRA_TEXT, HASHTAG);
                tweetIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///sdcard/temp_file.jpg"));
                tweetIntent.setType("image/jpeg");

                PackageManager pk = mainActivity.getPackageManager();
                List<ResolveInfo> resolve = pk.queryIntentActivities(tweetIntent, PackageManager.MATCH_DEFAULT_ONLY);
                boolean resolved = false;
                for (ResolveInfo resolveInfo : resolve) {
                    if (resolveInfo.activityInfo.name.contains("twitter")) {
                        tweetIntent.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
                        resolved = true;
                        break;
                    }
                }

                v.getContext().startActivity(resolved ? tweetIntent : Intent.createChooser(tweetIntent, mainActivity.getString(R.string.share_image)));

            } catch (IOException e) {
                Toast.makeText(mainActivity, R.string.unexpected_error, Toast.LENGTH_SHORT).show();
            }


        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(mainActivity, R.string.twitter_not_installed, Toast.LENGTH_SHORT).show();
        }

    }

    public static void shareImageOnWhatsapp(MainActivity mainActivity, RelativeLayout relativePhotoContent, View v) {
        PackageManager pkManager = mainActivity.getPackageManager();

        try {
            pkManager.getPackageInfo("com.whatsapp", 0);
            String fileName = "temp_file" + System.currentTimeMillis() + ".jpg";

            try{
                relativePhotoContent.setDrawingCacheEnabled(true);
                relativePhotoContent.buildDrawingCache(true);

                File imgFile = new File(Environment.getExternalStorageDirectory(),fileName);
                FileOutputStream fileOutputStream = new FileOutputStream(imgFile);
                relativePhotoContent.getDrawingCache(true)
                        .compress(Bitmap.CompressFormat.JPEG,100, fileOutputStream);
                fileOutputStream.close();

                relativePhotoContent.setDrawingCacheEnabled(false);
                relativePhotoContent.destroyDrawingCache();


                try{
                    Intent sendIntent = new Intent();

                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, HASHTAG);
                    sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///sdcard/"+fileName));
                    sendIntent.setType("image/jpeg");
                    sendIntent.setPackage("com.whatsapp");

                    v.getContext().startActivity(Intent.createChooser(sendIntent, mainActivity.getString(R.string.share_image)));
                } catch (Exception e){
                    Toast.makeText(mainActivity, R.string.unexpected_error, Toast.LENGTH_SHORT).show();
                }


            } catch (FileNotFoundException e) {
                Toast.makeText(mainActivity, R.string.unexpected_error, Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Toast.makeText(mainActivity, R.string.unexpected_error, Toast.LENGTH_SHORT).show();
            }

        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(mainActivity, R.string.whatsapp_not_installed, Toast.LENGTH_SHORT).show();
        }
    }
}
