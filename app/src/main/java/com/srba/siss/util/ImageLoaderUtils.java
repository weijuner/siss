package com.srba.siss.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.srba.siss.R;

/**
 * Author:   zengwj
 * Version   V1.0
 * Date:     2016/7/14 11:10
 * Description: 图片加载工具类
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2016/7/14          zengwj              1.0                  1.0
 * Why & What is modified:
 */
public class ImageLoaderUtils {

    public static void display(Context context, ImageView imageView, String url, int placeholder, int error) {
        if(imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).placeholder(placeholder)
                .error(error).crossFade().into(imageView);
    }

    public static void display(Context context, ImageView imageView, String url) {
        if(imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).placeholder(R.drawable.ic_image_loading)
                .error(R.drawable.ic_image_loadfail).crossFade().into(imageView);
    }
}
