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

* ### Log相关  ->[Logger][Logger.java]

  ```
  init 			:初始化
  d
  i
  e
  w
  ```

* ### 圆形CircleImageView -> [参考][https://github.com/hdodenhof/CircleImageView]

* ### 圆形RelativeLayout -> [CircularRelativeLayout](./library/src/main/java/com/android/mltcode/watchlib/view/CircularRelativeLayout.java)

  ```
  getPrimaryDimension		
  setPrimaryDimension		:根据高度或者宽度设置成正方形
  ```

  

[./library/src/main/java/com/android/mltcode/watchlib/view/CircularRelativeLayout.java]: 