/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.inks.inkslibrary.PageTransformer;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.graphics.Camera;
import android.graphics.Matrix;

/**
 * 译: 一个在指定了两个角度的在X/Y轴旋转的动画类，这个类也添加了一个z轴的属性用来提高效果 
 */  
public class Rotate3dAnimation extends Animation {  
    private final float mFromDegrees;  
    private final float mToDegrees;  
    private final float mCenterX;  
    private final float mCenterY;  
    private final int mDepthZ;  
    private final boolean mReverse;  
    private final boolean mRotateX;  
    private Camera mCamera;  
  
    /** 
     * 在Y轴创建了一个新的3D的旋转动画,这个旋转动画定义了它的开始角度和结束角度,两个角度的单位都是度数, 
     * 这个旋转动画围绕在2D空间的中心点执行.你可以用X轴坐标(叫做centerX)和Y轴(叫做centerY)坐标来定义 
     * 这个中心点.当动画开始时,对于z轴(深度)的转换就会被执行.转换的长度和转换正向反向都可以指定. 
     * 
     * @param fromDegrees 开始的角度 
     * @param toDegrees 结束的角度 
     * @param centerX 中心点X轴坐标 
     * @param centerY 中心点Y轴坐标 
     * @param depthZ 控制镜头景深，不需要的话给0值即可 
     * @param reverse true表示反向,false表示正向 
     * @param rotateX true 绕x轴转， false 绕y轴转 
     */  
    protected Rotate3dAnimation(float fromDegrees, float toDegrees, float centerX,   
            float centerY, int depthZ, boolean reverse, boolean rotateX) {  
          
        mFromDegrees = fromDegrees;  
        mToDegrees = toDegrees;  
        mCenterX = centerX;  
        mCenterY = centerY;  
        mDepthZ = depthZ;  
        mReverse = reverse;  
        mRotateX = rotateX;  
    }  
  
    @Override  
    public void initialize(int width, int height, int parentWidth, int parentHeight) {  
        super.initialize(width, height, parentWidth, parentHeight);  
        mCamera = new Camera(); // 用于3D动画  
    }  
  
    /** 
     * @param interpolatedTime 动画播放的时间，用于计算出当前旋转的角度 
     * @param t 用于获得动画矩阵，用于View的动画，“转换”的意思，用于实际在View中产生动画的转换对象。 
     */  
    @Override  
    protected void applyTransformation(float interpolatedTime, Transformation t) {  
        final float fromDegrees = mFromDegrees;  
        float degrees = fromDegrees + ((mToDegrees - fromDegrees) * interpolatedTime);  
  
        final float centerX = mCenterX;  
        final float centerY = mCenterY;  
        final Camera camera = mCamera;  
  
        /* 
         * Camera中要执行的动画要首先保存在这个Matrix中，再通过参数t返回给系统使用，做动画。 
         */  
        final Matrix matrix = t.getMatrix();  
  
        camera.save(); // 保存Camera当前状态  
        if (mDepthZ != 0) { // 控制镜头景深，不需要的话，设置mDepthZ=0  
            if (mReverse) {  
                camera.translate(0.0f, 0.0f, mDepthZ * interpolatedTime);  
            } else {  
                camera.translate(0.0f, 0.0f, mDepthZ * (1.0f - interpolatedTime));  
            }  
        }  
        if (mRotateX) {  
            camera.rotateX(degrees);  
        } else {  
            camera.rotateY(degrees);  
        }  
        camera.getMatrix(matrix); // 把Camera将要做的动画保存在Matrix中  
        camera.restore(); // 恢复Camera上一个状态  
  
        /* 
         * 前面的旋转是以(0,0)为中心旋转的，因此需要在旋转前把要旋转的View的中心坐标移动到 
         * (0,0)坐标位置，旋转之后把View再移动回来。 
         */  
        matrix.preTranslate(-centerX, -centerY);  
        matrix.postTranslate(centerX, centerY);  
    }  
}  