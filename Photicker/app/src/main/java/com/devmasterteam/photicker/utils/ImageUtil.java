package com.devmasterteam.photicker.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.devmasterteam.photicker.R;
import com.devmasterteam.photicker.views.MainActivity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anderson on 10/08/17.
 */

public class ImageUtil {

    public static List<Integer> getImageList(){

        List<Integer> lstImage = new ArrayList<>();

        lstImage.add(R.drawable.st_animal_0);
        lstImage.add(R.drawable.st_animal_10);
        lstImage.add(R.drawable.st_animal_11);
        lstImage.add(R.drawable.st_animal_12);
        lstImage.add(R.drawable.st_animal_13);
        lstImage.add(R.drawable.st_animal_14);
        lstImage.add(R.drawable.st_animal_16);
        lstImage.add(R.drawable.st_animal_17);
        lstImage.add(R.drawable.st_animal_18);
        lstImage.add(R.drawable.st_animal_19);
        lstImage.add(R.drawable.st_animal_2);
        lstImage.add(R.drawable.st_animal_20);
        lstImage.add(R.drawable.st_animal_21);
        lstImage.add(R.drawable.st_animal_22);
        lstImage.add(R.drawable.st_animal_23);
        lstImage.add(R.drawable.st_animal_24);
        lstImage.add(R.drawable.st_animal_25);
        lstImage.add(R.drawable.st_animal_26);
        lstImage.add(R.drawable.st_animal_27);
        lstImage.add(R.drawable.st_animal_28);
        lstImage.add(R.drawable.st_animal_29);
        lstImage.add(R.drawable.st_animal_3);
        lstImage.add(R.drawable.st_animal_4);
        lstImage.add(R.drawable.st_animal_5);
        lstImage.add(R.drawable.st_animal_6);
        lstImage.add(R.drawable.st_animal_7);
        lstImage.add(R.drawable.st_animal_8);
        lstImage.add(R.drawable.st_comida_1);
        lstImage.add(R.drawable.st_comida_2);
        lstImage.add(R.drawable.st_comida_3);
        lstImage.add(R.drawable.st_comida_5);
        lstImage.add(R.drawable.st_comida_6);
        lstImage.add(R.drawable.st_coracao_1);
        lstImage.add(R.drawable.st_coracao_2);
        lstImage.add(R.drawable.st_coracao_3);
        lstImage.add(R.drawable.st_coracao_4);
        lstImage.add(R.drawable.st_coracao_5);
        lstImage.add(R.drawable.st_coracao_6);
        lstImage.add(R.drawable.st_drink_1);
        lstImage.add(R.drawable.st_drink_10);
        lstImage.add(R.drawable.st_drink_2);
        lstImage.add(R.drawable.st_drink_3);
        lstImage.add(R.drawable.st_drink_4);
        lstImage.add(R.drawable.st_drink_5);
        lstImage.add(R.drawable.st_drink_6);
        lstImage.add(R.drawable.st_drink_7);
        lstImage.add(R.drawable.st_drink_8);
        lstImage.add(R.drawable.st_drink_9);
        lstImage.add(R.drawable.st_facial_0);
        lstImage.add(R.drawable.st_facial_1);
        lstImage.add(R.drawable.st_facial_10);
        lstImage.add(R.drawable.st_facial_11);
        lstImage.add(R.drawable.st_facial_12);
        lstImage.add(R.drawable.st_facial_13);
        lstImage.add(R.drawable.st_facial_14);
        lstImage.add(R.drawable.st_facial_3);
        lstImage.add(R.drawable.st_facial_4);
        lstImage.add(R.drawable.st_facial_5);
        lstImage.add(R.drawable.st_facial_6);
        lstImage.add(R.drawable.st_facial_7);
        lstImage.add(R.drawable.st_facial_8);
        lstImage.add(R.drawable.st_facil_13);
        lstImage.add(R.drawable.st_misc_1);
        lstImage.add(R.drawable.st_objeto_2);
        lstImage.add(R.drawable.st_objeto_3);
        lstImage.add(R.drawable.st_objeto_4);
        lstImage.add(R.drawable.st_objeto_5);
        lstImage.add(R.drawable.st_objeto_6);
        lstImage.add(R.drawable.st_sticker_11);
        lstImage.add(R.drawable.st_sticker_2);
        lstImage.add(R.drawable.st_sticker_5);
        lstImage.add(R.drawable.st_sticker_6);
        lstImage.add(R.drawable.st_sticker_7);
        lstImage.add(R.drawable.st_sticker_8);
        lstImage.add(R.drawable.st_sticker_9);
        lstImage.add(R.drawable.st_tatto_1);
        lstImage.add(R.drawable.st_tatto_2);
        lstImage.add(R.drawable.st_tatto_3);
        lstImage.add(R.drawable.st_tatto_4);
        lstImage.add(R.drawable.st_tatto_5);
        lstImage.add(R.drawable.st_tatto_6);

        return lstImage;
    }


    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight){

        final int width = options.outWidth;
        final int height = options.outHeight;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth){
            final int halfHeight =  height / 2;
            final int halfWidth =  width / 2;

            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth){
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static void handleZoomIn(ImageView imageSelected) {
        if (imageSelected.getWidth() > 800){
            return;
        }

        ViewGroup.LayoutParams params = imageSelected.getLayoutParams();
        params.width = (int) (imageSelected.getWidth() + (imageSelected.getWidth() * 0.1));
        params.height = (int) (imageSelected.getHeight() + (imageSelected.getHeight() * 0.1));

        imageSelected.setLayoutParams(params);
    }

    public static void handleZoomOut(ImageView imageSelected) {
        if (imageSelected.getWidth() > 50){ return; }

        ViewGroup.LayoutParams params = imageSelected.getLayoutParams();
        params.width = (int) (imageSelected.getWidth() - (imageSelected.getWidth() * 0.1));
        params.height = (int) (imageSelected.getHeight() - (imageSelected.getHeight() * 0.1));

        imageSelected.setLayoutParams(params);
    }

    public static void handleRotateLeft(ImageView imageSelected) {
        imageSelected.setRotation(imageSelected.getRotation() - 5);
    }

    public static void handleRotateRight(ImageView imageSelected) {
        imageSelected.setRotation(imageSelected.getRotation() + 5);
    }

    public static File createImageFile(Context context) throws IOException {
        String imageFileName = "photicker";
        File storeDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File image = File.createTempFile(imageFileName, ".jpg", storeDir);

        return image;
    }

    public static Bitmap rotateImageIfRequired(Bitmap img, Uri selectedImage) {
        ExifInterface exifInterface;

        try {
            exifInterface = new ExifInterface(selectedImage.getPath());
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            switch (orientation){
                case ExifInterface.ORIENTATION_ROTATE_90:
                    return rotateImage(img, 90);
                case ExifInterface.ORIENTATION_ROTATE_180:
                    return rotateImage(img, 180);
                case ExifInterface.ORIENTATION_ROTATE_270:
                    return rotateImage(img, 270);
                default:
                    return img;
            }
        } catch (IOException e) {
            return img;
        }
    }

    private static Bitmap rotateImage(Bitmap img, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);

        Bitmap rotatedImg = Bitmap.createBitmap(img,0,0,img.getWidth(),img.getHeight(),matrix,false);
        img.recycle();

        return rotatedImg;
    }

    public static Bitmap drawBitmap(RelativeLayout relativePhotoContent) {

    
    }
}
