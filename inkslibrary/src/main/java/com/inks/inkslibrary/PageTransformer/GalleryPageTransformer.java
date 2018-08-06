package com.inks.inkslibrary.PageTransformer;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

//3D效果

//用法  ：          mViewPager.setPageTransformer(true, new GalleryPageTransformer());

public class GalleryPageTransformer implements ViewPager.PageTransformer {
    private static final float MAX_ROTATION=45.0f;
    private static final float MIN_SCALE=0.0f;
    private static  float MAX_TRANSLATE=0.0f;

    @Override
    public void transformPage(View page, float position) {
        int pageWidth = page.getWidth();
        int pageHeight = page.getHeight();
     //   MAX_TRANSLATE = (float) (pageWidth/64.0);

        if(position<-1) {//A
         //   page.setTranslationX(-MAX_TRANSLATE);
//            page.setScaleX(MIN_SCALE);
//            page.setScaleY(MIN_SCALE);
         //   page.setRotationY(-MAX_ROTATION);
        }
        else if(position<=0) {//a页
          //  page.setTranslationX(MAX_TRANSLATE*position);
//            float scale=MIN_SCALE+(1-MIN_SCALE)*(1.0f+position);
//            page.setScaleX(scale);
//            page.setScaleY(scale);
            page.setPivotX(pageWidth);
            page.setPivotY(pageHeight/2);
            page.setRotationY(MAX_ROTATION*position);
            Log.e("页面A","旋转角度："+MAX_ROTATION*position);
            Log.e("页面A","position："+position);
        }
        else if(position<=1) {//b页
          //  page.setTranslationX(-MAX_TRANSLATE*(1-position));
//            float scale=MIN_SCALE+(1-MIN_SCALE)*(1.0f-position);
//            page.setScaleX(scale);
//            page.setScaleY(scale);
            page.setPivotX(0);
            page.setPivotY(pageHeight/2);
            page.setRotationY(MAX_ROTATION*position);
            Log.e("页面B","旋转角度："+MAX_ROTATION*position);
            Log.e("页面B","position："+position);

        }
        else {//B
//            page.setScaleX(MIN_SCALE);
//            page.setScaleY(MIN_SCALE);
          //
        }
    }
}