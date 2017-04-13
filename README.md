# Provider
常用工具封装aar
> 声明 本moudle不是什么原创，而是集成多家三方形成的工具库，主要提供给自己日常开发

    compile 'com.squareup.retrofit2:retrofit:2.2.0'
    compile 'com.squareup.retrofit2:converter-scalars:2.2.0'
    compile 'com.squareup.retrofit2:converter-gson:2.2.0'
    compile 'com.squareup.retrofit2:adapter-rxjava2:2.2.0'
    compile 'io.reactivex.rxjava2:rxjava:2.0.8'
    compile 'io.reactivex.rxjava2:rxandroid:2.0.1'
    compile 'com.jakewharton.rxbinding2:rxbinding:2.0.0'
    compile 'com.google.code.gson:gson:2.8.0'
    compile 'com.squareup.okhttp3:okhttp:3.6.0'
    compile 'com.squareup.picasso:picasso:2.5.2'
    compile 'com.tbruyelle.rxpermissions2:rxpermissions:0.9.3@aar'
    compile 'com.android.support:recyclerview-v7:25.3.1'
    compile 'com.android.support:design:25.3.1'
### Encryption类用于urlcode加密解密 md5加密
### Compressor类用于压缩图片文件
### FloatHelper类用于帮助实现recyclerview头部悬浮停靠效果
### NetProvider类用于监听当前网络状态
### PartProvider类用于提供图片文件上传的MultipartBody.Part
### Permission类 6.0动态获取权限
### RetrofitProvider类 提供Retrofit单例对象  基于ScalarsConverter
### RxBus 数据传递
### RxCountDown 倒计时
### RxListener  OnClickListener点击事件，防抖动时间为1秒
### RxToast 在主线程中弹Toast
### RxTransform rxjava线程转换
### Resolution dp和px像素转换
### StringFormat 填充占位符的正确方式  整型 浮点  字符串
### StringUtil  字符串对比校验工具类
### Creator 反射泛型对象
### RxPicasso  统一加载中和加载失败时替代的图片，返回RequestCreator对象
### ParamsProvider 将map集合转换成&key=value形式字符串。用于webview的url拼接
### Screen 获取屏幕宽和高
### OnRecycerPagerListener 做recyclerView横向滑动的时候当前显示的item的position 继承于addOnScrollListener
> RecyclerPagerHelper 或者你也可以直接使用帮助类进行直接设置。

### DownLoadFile  基于retrofit下载图片文件的封装类

### 加入了一些三方控件 badge  banner circle  flowLayout  xrecyclerview



