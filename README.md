# HenCoderCollections

# 绘制篇阅读笔记

[TOC]

## 绘制基础Canvas.drawXXX 

- drawArc(float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean useCenter, Paint paint) 绘制弧形或扇形。 
**useCenter 表示是否连接到圆心，如果不连接到圆心，就是弧形，如果连接到圆心，就是扇形。**


## Paint 设置


Canvas 绘制的内容，有三层对颜色的处理：

![](https://ws3.sinaimg.cn/large/52eb2279ly1fig6dcywn2j20j909yabu.jpg)

### 1. 基本颜色

Paint 设置颜色的方法有两种：一种是直接用 Paint.setColor/ARGB() 来设置颜色，另一种是使用 Shader 来指定着色方案。

Shader 这个英文单词很多人没有见过，它的中文叫做「着色器」，也是用于设置绘制颜色的。「着色器」不是 Android 独有的，它是图形领域里一个通用的概念，它和直接设置颜色的区别是，着色器设置的是一个颜色方案，或者说是一套着色规则。当设置了 Shader 之后，Paint 在绘制图形和文字时就不使用  setColor/ARGB() 设置的颜色了，而是使用 Shader 的方案中的颜色。

在 Android 的绘制里使用 Shader ，并不直接用 Shader 这个类，而是用它的几个子类。具体来讲有  LinearGradient RadialGradient SweepGradient BitmapShader ComposeShader 这么几个：

- LinearGradient 线性渐变

设置两个点和两种颜色，以这两个点作为端点，使用两种颜色的渐变来绘制颜色


```
Shader shader = new LinearGradient(100, 100, 500, 500, Color.parseColor("#E91E63"),  
        Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);
paint.setShader(shader);

...

canvas.drawCircle(300, 300, 200, paint);  

```

![shader](https://ws3.sinaimg.cn/large/52eb2279ly1fig6dq7wudj206l06875e.jpg)



构造方法： 
LinearGradient(float x0, float y0, float x1, float y1, int color0, int color1, Shader.TileMode tile) 。

参数： 
x0 y0 x1 y1：渐变的两个端点的位置 
color0 color1 是端点的颜色 
tile：端点范围之外的着色规则，类型是 TileMode。TileMode 一共有 3 个值可选： CLAMP, MIRROR 和  REPEAT。CLAMP （夹子模式？？？算了这个词我不会翻）会在端点之外延续端点处的颜色；MIRROR 是镜像模式；REPEAT 是重复模式。具体的看一下例子就明白。

CLAMP:

![](https://ws3.sinaimg.cn/large/52eb2279ly1fig6e7vbemj20cj090goh.jpg)

MIRROR:

![](https://ws3.sinaimg.cn/large/52eb2279ly1fig6egtxw5j20ck08xjv6.jpg)


REPEAT:

![](https://ws3.sinaimg.cn/large/52eb2279ly1fig6em2wabj20ck08xjvo.jpg)


- RadialGradient 辐射渐变
- SweepGradient 扫描渐变

![](https://ws3.sinaimg.cn/large/52eb2279ly1fig6fmbemdj206u061my4.jpg)

构造方法和线性渐变都是一致的，只是不同的效果。

- BitmapShader

就是用 Bitmap 的像素来作为图形或文字的填充。大概像这样：

```
Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.batman);  
Shader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);  
paint.setShader(shader);

...

canvas.drawCircle(300, 300, 200, paint);  
```

<img src="https://ws3.sinaimg.cn/large/52eb2279ly1fig6fragq2j20lc089djv.jpg">


构造方法： 
BitmapShader(Bitmap bitmap, Shader.TileMode tileX, Shader.TileMode tileY)

参数： 
bitmap：用来做模板的 Bitmap 对象 
tileX：横向的 TileMode 
tileY：纵向的 TileMode。

看一个 MIRROR 模式的就明白啥意思了。

![](https://ws3.sinaimg.cn/large/52eb2279ly1fig6g8moalj20kw0cjduk.jpg)


- ComposeShader 混合着色器

```java
Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.batman);  
Shader shader1 = new BitmapShader(bitmap1, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

// 第二个 Shader：从上到下的线性渐变（由透明到黑色）
Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.batman_logo);  
Shader shader2 = new BitmapShader(bitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

// ComposeShader：结合两个 Shader
Shader shader = new ComposeShader(shader1, shader2, PorterDuff.Mode.SRC_OVER);  
paint.setShader(shader);

...

canvas.drawCircle(300, 300, 300, paint);  
```

**ComposeShader() 在硬件加速下是不支持两个相同类型的 Shader 的，所以这里也需要关闭硬件加速才能看到效果**


![](https://ws3.sinaimg.cn/large/52eb2279ly1fig6hbeg7gj20qy08cafn.jpg)

构造方法：ComposeShader(Shader shaderA, Shader shaderB, PorterDuff.Mode mode)

参数： 
shaderA, shaderB：两个相继使用的 Shader 
mode: 两个 Shader 的叠加模式，即 shaderA 和 shaderB 应该怎样共同绘制。它的类型是 PorterDuff.Mode 。

>PorterDuff.Mode 一共有 17 个，可以分为两类：
>1. Alpha 合成 (Alpha Compositing)
>2. 混合 (Blending)

源图像和目标图像

<img src="https://ws3.sinaimg.cn/large/52eb2279ly1fig6ia1twgj20ds07tdgs.jpg" width=300 height=300>

Alpha 效果

<img src="https://ws3.sinaimg.cn/large/52eb2279ly1fig6im3hhcj20o50zt7bj.jpg" width=400 height=400>


### 2. setColorFilter 

ColorFilter 这个类，它的名字已经足够解释它的作用：为绘制设置颜色过滤。颜色过滤的意思，就是为绘制的内容设置一个统一的过滤策略，然后 Canvas.drawXXX() 方法会对每个像素都进行过滤后再绘制出来。举几个现实中比较常见的颜色过滤的例子：

- 有色光照射

<img src="https://ws3.sinaimg.cn/large/52eb2279ly1fig6j51ronj20rs0kv1kx.jpg" width=400>

- 有色透视玻璃

<img src="https://ws3.sinaimg.cn/large/52eb2279ly1fig6jizpvnj20iw0db4ey.jpg" width=400>

在 Paint 里设置 ColorFilter ，使用的是 Paint.setColorFilter(ColorFilter filter) 方法。 ColorFilter 并不直接使用，而是使用它的子类。它共有三个子类：LightingColorFilter PorterDuffColorFilter 和  ColorMatrixColorFilter


#### LightingColorFilter
LightingColorFilter 是用来模拟简单的光照效果的。可以实现增强或去除RGB颜色色值的功能。

```
// 移除红色
ColorFilter lightingColorFilter = new LightingColorFilter(0x00ffff, 0x000000);  
paint.setColorFilter(lightingColorFilter);  

// 增强绿色
ColorFilter lightingColorFilter = new LightingColorFilter(0xffffff, 0x003000);  
paint.setColorFilter(lightingColorFilter);
```

#### PorterDuffColorFilter
这个 PorterDuffColorFilter 的作用是使用一个指定的颜色和一种指定的 PorterDuff.Mode 来与绘制对象进行合成。它的构造方法是 PorterDuffColorFilter(int color, PorterDuff.Mode mode) 其中的 color 参数是指定的颜色， mode 参数是指定的 Mode。同样也是 PorterDuff.Mode ，不过和 ComposeShader 不同的是，PorterDuffColorFilter 作为一个 ColorFilter，只能指定一种颜色作为源，而不是一个 Bitmap。

#### ColorMatrixColorFilter
ColorMatrixColorFilter 使用一个 ColorMatrix 来对颜色进行处理


### 3. setXfermode(Xfermode xfermode)

Paint 最后一层处理颜色的方法是 setXfermode(Xfermode xfermode) ，它处理的是「当颜色遇上 View」的问题



严谨地讲， Xfermode 指的是你要绘制的内容和 Canvas 的目标位置的内容应该怎样结合计算出最终的颜色。但通俗地说，其实就是要你以绘制的内容作为源图像，以 View 中已有的内容作为目标图像，选取一个  PorterDuff.Mode 作为绘制内容的颜色处理方案。就像这样：

```java

Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);

...

canvas.drawBitmap(rectBitmap, 0, 0, paint); // 画方  
paint.setXfermode(xfermode); // 设置 Xfermode  
canvas.drawBitmap(circleBitmap, 0, 0, paint); // 画圆  
paint.setXfermode(null); // 用完及时清除 Xfermode  
```

<img src="https://ws1.sinaimg.cn/large/006tNc79ly1fig6x8dhk6j30nl0pswff.jpg" width=400>


#### 效果

1. paint.setStrokeCap

设置线头的形状。线头形状有三种：BUTT 平头、ROUND 圆头、SQUARE 方头。默认为 BUTT

<img src="https://user-gold-cdn.xitu.io/2017/7/17/8fa8c7cfb47fa0f6d5d3c8c18635ae82?imageView2/0/w/1280/h/960/ignore-error/1" width=400>

2. paint.setStrokeJoin

设置拐角的形状。有三个值可以选择：MITER 尖角、 BEVEL 平角和 ROUND 圆角。默认为 MITER

<img src="https://user-gold-cdn.xitu.io/2017/7/17/6a5f1de602276ce42cc5a7313a59fc08?imageView2/0/w/1280/h/960/ignore-error/1" width=400>


3. setShadowLayer(float radius, float dx, float dy, int shadowColor)

在之后的绘制内容下面加一层阴影。

<img src="https://user-gold-cdn.xitu.io/2017/7/17/02dfb576b4fbf35a1b1f5b46c34ba2df?imageView2/0/w/1280/h/960/ignore-error/1" widht=400>

4. setMaskFilter(MaskFilter maskfilter)
为之后的绘制设置 MaskFilter。上一个方法 setShadowLayer() 是设置的在绘制层下方的附加效果；而这个 MaskFilter 和它相反，设置的是在绘制层上方的附加效果。

MaskFilter 有两种： BlurMaskFilter 和 EmbossMaskFilter。

- BlurMaskFilter


# 文字的绘制

### StaticLayout

StaticLayout 并不是一个 View 或者 ViewGroup ，而是 android.text.Layout 的子类，它是纯粹用来绘制文字的。 StaticLayout 支持换行，它既可以为文字设置宽度上限来让文字自动换行，也会在 \n 处主动换行。






## Hencoder 练习集

PracticeDraw1-PracticeDraw7 

## [HenCoder「仿写酷界面」活动 仿写内容]


**[Flipboard 翻页效果(加强版)](https://github.com/REBOOTERS/HenCoderCollections/tree/master/HenCoderExtraActivity)**

![Flipboard](https://raw.githubusercontent.com/REBOOTERS/HenCoderCollections/master/HenCoderExtraActivity/screens/flipboard.gif)


[快速查看FlipboardView.java](https://github.com/REBOOTERS/HenCoderCollections/blob/9a885d3298238d3903e94507dad4473dff63b04b/HenCoderExtraActivity/app/src/main/java/hencoder/com/hencoderextraactivity/FlipboardView.java)
