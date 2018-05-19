package com.ptit.btl.moviedb.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

/**
 * Created by admin on 25/4/18.
 */
public class ImageUtils {
    public static void loadImageFromUrl(ImageView imageView, String url, int imagePlaceHolder) {
        Glide.with(imageView.getContext())
            .load(
                String.format(
                    Constant.ApiRequestUrl.API_IMAGE_URL, url))
            .placeholder(imagePlaceHolder)
            .error(imagePlaceHolder)
            .into(imageView);
    }

    public static void loadImageFromUrlPicasso(ImageView imageView, String url, int imagePlaceHolder) {
        Picasso.get()
                .load(String.format(Constant.ApiRequestUrl.API_IMAGE_URL, url))
                .placeholder(imagePlaceHolder)
                .error(imagePlaceHolder)
                .into(imageView);
    }
}
