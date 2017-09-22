package org.hunter.irregularshapeviewlib.clippath;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.hunter.irregularshapeviewlib.R;

/**
 * Glide图片加载
 */

public class GlideImageLoadUtils {


    public static void displayImage(Context context, String url, ImageView imageView, int defaultRes) {
        Glide.with(context)
                .load(url)
                .asBitmap()//会调用ImageView 的 setImageBitmap（）；
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .placeholder(defaultRes)
                .error(defaultRes)
                .into(imageView);
    }

    public static void displayImage(Context context, int id, ImageView imageView, int defaultRes) {
        Glide.with(context)
                .load(id)
                .asBitmap()//会调用ImageView 的 setImageBitmap（）；
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .placeholder(defaultRes)
                .error(defaultRes)
                .into(imageView);
    }


    public static  int getIconNormalOptions(){
        return R.drawable.shape_rectangular_image;
    }

}
