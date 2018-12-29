# WatchLibrary

WatchLibrary Is a tool library for watch development

# Quick Setup
#### Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:
``` groovy
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

#### Step 2. Add the dependency
``` groovy
	dependencies {
		...
	        implementation 'com.github.xzp-github:WatchLibrary:v1.0.0'
	}
```

#### Step 3. Add permission

add Read and write SDcard permissions


#### Step 4. Just call WatchUtils.init(context) in your own Application:
``` groovy
public class MyOwnApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        WatchUtils.init(this);
    }
    ...
}
```

## APIs

* ### Log相关  ->[Logger](./library/src/main/java/com/android/mltcode/watchlib/config/Logger.java)

  ```
  init 			:初始化
  d
  i
  e
  w
  ```

* ### 圆形CircleImageView -> [参考](https://github.com/hdodenhof/CircleImageView)

* ### 圆形RelativeLayout -> [CircularRelativeLayout](./library/src/main/java/com/android/mltcode/watchlib/view/CircularRelativeLayout.java)

  ```
  getPrimaryDimension		
  setPrimaryDimension		:根据高度或者宽度设置成正方形
  ```

  

* ## 月牙按钮 -> [LeftRightButton](./library/src/main/java/com/android/mltcode/watchlib/view/LeftRightButton.java)

  | 属性                | 值说明                                                       |
  | ------------------- | ------------------------------------------------------------ |
  | app:leftBackground  | 左边按钮背景                                                 |
  | app:leftImgSrc      | 左边按钮图片模式下的icon , 'app:leftRightType="img"'设置有效 |
  | app:leftText        | 左边按钮文字模式下String, 'app:leftRightType="text"'设置有效 |
  | app:rightBackground | 右边按钮背景                                                 |
  | app:rightImgSrc     | 右边按钮图片模式下的icon, 'app:leftRightType="img"'设置有效  |
  | app:rightText       | 右边按钮文字模式下String, 'app:leftRightType="text"'设置有效 |
  | app:leftRightType   | 定义按钮当前使用何种模式 图片或者文字 "img" or "text"        |

  #### layout xml中使用

  ```
  <com.android.mltcode.watchlib.view.LeftRightButton
          android:id="@+id/leftrightbutton"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:layout_centerHorizontal="true"
          android:layout_alignParentBottom="true"
          app:leftBackground="@drawable/selector_cancel_bg"
          app:leftRightType="text"
          app:leftText="@string/cancel"
          app:rightText="@string/config"
          app:rightBackground="@drawable/selector_config_bg">
   </com.android.mltcode.watchlib.view.LeftRightButton>
   
  ```

  #### MainAcitvity中

  ```
   mLeftRightButton = findViewById(R.id.leftrightbutton);
   mLeftRightButton.setLeftRightListener(this);
   
   
    @Override
      public void onLeftClick(View view) {
          if(mTextView != null){
              mTextView.setText("onLeftClick");
          }
      }
  
      @Override
      public void onRightClick(View view) {
          if(mTextView != null){
              mTextView.setText("onRightClick");
          }
      }
  ```

  

  ![如图所示](Simple/leftRightbtn.png)