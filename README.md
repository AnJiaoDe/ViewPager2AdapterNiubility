文章目录

该轮子特异功能如下：

使用方法

注意：该轮子适用于androidx中的ViewPager2和ViewPager

注意：如果轮子死活下载不下来，说明maven地址有毛病，你需要找到jitpack的官网首页，查看最新的官网地址

注意：记得去gayhub查看最新版本，最新版本最niubility

详细使用如下

ViewPager万能适配器

ViewPager2万能适配器

ViewPager无限轮播（低耦合indicator 蠕动动画，布局独立）

ViewPager2无限轮播（低耦合indicator 蠕动动画，布局独立）

indicator 自定义动画

AndroidX ViewPager2滑动冲突问题

AndroidX ViewPager2滑动冲突问题真的能完美解决吗？

所以，小编还是强烈建议复杂情况下使用Androidx 的ViewPager

相关API

ViewPager、ViewPager2无限轮播原理剖析

源码如下

面向接口编程（面向多态编程）的思想

欢迎联系、指正、批评

[GitHub:https://github.com/AnJiaoDe/ViewPager2AdapterNiubility](https://github.com/AnJiaoDe/ViewPager2AdapterNiubility)

[CSDN：https://blog.csdn.net/confusing_awakening/article/details/112649313](https://blog.csdn.net/confusing_awakening/article/details/112649313)

## 该轮子特异功能如下：

ViewPager、ViewPager2万能适配器

ViewPager、ViewPager2无限轮播

低耦合indicator蠕动动画、自定义indicator，布局独立
## 使用方法

## 注意：该轮子适用于androidx中的ViewPager2和ViewPager

1.工程目录下的`build.gradle`中添加代码：

## 注意：如果轮子死活下载不下来，说明maven地址有毛病，你需要找到jitpack的官网首页，查看最新的官网地址

```java
allprojects {
		repositories {
			        maven { url 'https://www.jitpack.io' }
		}
	}
```
2.直接在需要使用的模块的`build.gradle`中添加代码：

```java
dependencies {
api 'com.github.AnJiaoDe:ViewPager2AdapterNiubility:V2.3.9'
}
```

## 注意：记得去gayhub查看最新版本，最新版本最niubility

3.如果你想使用`ViewPager2`,那么添加代码：

```java
api 'androidx.viewpager2:viewpager2:1.0.0'//版本必须>=1.0.0
```
4.混淆已配置到库内部，无需做混淆配置

## 详细使用如下
## ViewPager万能适配器

![在这里插入图片描述](https://img-blog.csdnimg.cn/20210115105001285.gif)


```xml
 <androidx.viewpager.widget.ViewPager
        android:id="@+id/vp"
        android:layout_width="match_parent"
        android:layout_height="match_parent"></androidx.viewpager.widget.ViewPager>
```

```java
public class ViewPagerAdapterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_adapter);

        ViewPager viewPager=findViewById(R.id.vp);
        ViewPagerAdapter<PageBean> viewPagerAdapter =new ViewPagerAdapter<PageBean>(viewPager) {
            @Override
            public void bindDataToView(ViewPagerHolder holder, int position, PageBean bean) {
                ImageView imageView=holder.itemView.findViewById(R.id.iv);
                imageView.setImageResource(bean.getResID());
            }

            @Override
            public int getItemLayoutID(int position, PageBean bean) {
                return R.layout.item_page_pic;
            }

            @Override
            public void onItemClick(ViewPagerHolder holder, int position, PageBean bean) {
                Toast.makeText(ViewPagerAdapterActivity.this, "点击" + position, Toast.LENGTH_SHORT).show();
            }
        };
        viewPager.setAdapter(viewPagerAdapter);

        viewPagerAdapter.add(new PageBean(R.drawable.pic1));
        viewPagerAdapter.add(new PageBean(R.drawable.pic2));

        viewPager.setCurrentItem(1,false);
    }
}
```
## ViewPager2万能适配器
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210115105109916.gif)

```xml
    <androidx.viewpager2.widget.ViewPager2
        android:id="@+id/vp2"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
```

```java
public class ViewPager2AdapterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager2_adapter2);

        ViewPager2 viewPager2=findViewById(R.id.vp2);
        ViewPager2Adapter<PageBean> viewPager2Adapter =new ViewPager2Adapter<PageBean>(viewPager2) {
            @Override
            public void bindDataToView(ViewPager2Holder holder, int position, PageBean bean) {
                ImageView imageView=holder.itemView.findViewById(R.id.iv);
                imageView.setImageResource(bean.getResID());
            }

            @Override
            public int getItemLayoutID(int position, PageBean bean) {
                return R.layout.item_page_pic;
            }

            @Override
            public void onItemClick(ViewPager2Holder holder, int position, PageBean bean) {
                Toast.makeText(ViewPager2AdapterActivity.this, "点击" + position, Toast.LENGTH_SHORT).show();
            }
        };
        viewPager2.setAdapter(viewPager2Adapter);

        viewPager2Adapter.add(new PageBean(R.drawable.pic1));
        viewPager2Adapter.add(new PageBean(R.drawable.pic2));

        viewPager2.setCurrentItem(1,false);

    }
}

```
## ViewPager无限轮播（低耦合indicator 蠕动动画，布局独立）
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210115110325842.gif)

```xml
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <androidx.viewpager.widget.ViewPager
        android:id="@+id/vp"
        android:layout_width="match_parent"
        android:layout_height="match_parent"></androidx.viewpager.widget.ViewPager>

    <com.cy.viewpager2adapterniubility.SimpleIndicatorView
        android:id="@+id/indicator"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="bottom"
        android:layout_marginBottom="30dp" />
</FrameLayout>

```
```java
public class ViewPagerLoopAdapterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_loop_adapter);

        ViewPager viewPager = findViewById(R.id.vp);
        SimpleIndicatorView simpleIndicatorView = findViewById(R.id.indicator);
        final ViewPagerLoopAdapter<PageBean> viewPageAdapter = new ViewPagerLoopAdapter<PageBean>(viewPager, simpleIndicatorView) {
            @Override
            public void bindDataToView(ViewPagerHolder holder, int position, PageBean bean) {
                ImageView imageView = holder.itemView.findViewById(R.id.iv);
                imageView.setImageResource(bean.getResID());
            }

            @Override
            public int getItemLayoutID(int position, PageBean bean) {
                return R.layout.item_page_pic;
            }

            @Override
            public void onItemClick(ViewPagerHolder holder, int position, PageBean bean) {
                Toast.makeText(ViewPagerLoopAdapterActivity.this, "点击" + position, Toast.LENGTH_SHORT).show();
            }
        };

        viewPager.setAdapter(viewPageAdapter);

        viewPageAdapter.addNoNotify(new PageBean(R.drawable.pic1));
        viewPageAdapter.addNoNotify(new PageBean(R.drawable.pic2));
        viewPageAdapter.addNoNotify(new PageBean(R.drawable.pic3));
        viewPageAdapter.add(new PageBean(R.drawable.pic4));

    }

}

```


## ViewPager2无限轮播（低耦合indicator 蠕动动画，布局独立）
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210115110129917.gif)

```xml
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <androidx.viewpager2.widget.ViewPager2
        android:id="@+id/vp2"
        android:layout_width="match_parent"
        android:layout_height="match_parent"></androidx.viewpager2.widget.ViewPager2>

    <com.cy.viewpager2adapterniubility.SimpleIndicatorView
        android:id="@+id/indicator"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="bottom"
        android:layout_marginBottom="30dp" />
</FrameLayout>

```

```java
public class ViewPager2LoopAdapterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager2_loop_adapter);

        ViewPager2 viewPager2=findViewById(R.id.vp2);
        SimpleIndicatorView simpleIndicatorView=findViewById(R.id.indicator);
        final ViewPager2LoopAdapter<PageBean> viewPage2Adapter=new ViewPager2LoopAdapter<PageBean>(viewPager2,simpleIndicatorView) {
            @Override
            public void bindDataToView(ViewPager2Holder holder, int position, PageBean bean) {
                ImageView imageView=holder.itemView.findViewById(R.id.iv);
                imageView.setImageResource(bean.getResID());
            }

            @Override
            public int getItemLayoutID(int position, PageBean bean) {
                return R.layout.item_page_pic;
            }

            @Override
            public void onItemClick(ViewPager2Holder holder, int position, PageBean bean) {
                Toast.makeText(ViewPager2LoopAdapterActivity.this, "点击" + position, Toast.LENGTH_SHORT).show();
            }
        };

        viewPager2.setAdapter(viewPage2Adapter);

        viewPage2Adapter.addNoNotify(new PageBean(R.drawable.pic1));
        viewPage2Adapter.addNoNotify(new PageBean(R.drawable.pic2));
        viewPage2Adapter.addNoNotify(new PageBean(R.drawable.pic3));
        viewPage2Adapter.add(new PageBean(R.drawable.pic4));

        viewPage2Adapter.setStartItem();

    }
}

```
## indicator 自定义动画
继承 `IIndicatorView`接口，实现3个方法即可(面向接口编程的厉害所在，多态的一把利器，能兼容后来写的代码，一种调用，多种表现形式，强扩展）
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210115112744202.jpg)


```java
public interface IIndicatorView {
    public <R extends View > R getView();
    public <R extends IIndicatorView > R setCount(int count);
    public <R extends IIndicatorView> R scroll(int position_scroll, float positionOffset);
}
```
如`SimpleIndicatorView` 实现方式，在onDraw方法中绘制自己想要的样式即可
```java
public class SimpleIndicatorView extends View implements IIndicatorView {
    private int position_selected = 0;
    private int position_scroll = 0;
    private int width = 0;
    private int height = 0;
    private int space = 0;
    private int radius_normal = 0;
    private int radius_selected = 0;
    private Paint paint_normal;
    private Paint paint_selected;
    private float positionOffset;
    private int count = 0;

    public SimpleIndicatorView(Context context) {
        this(context, null);
    }

    public SimpleIndicatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.SimpleIndicatorView);

        if (typedArray != null) {
            setSpace(typedArray.getDimensionPixelSize(R.styleable.SimpleIndicatorView_cy_indicator_space, ScreenUtils.dpAdapt(context, 8)));
            setRadius_normal(typedArray.getDimensionPixelSize(R.styleable.SimpleIndicatorView_cy_indicator_radius_normal, ScreenUtils.dpAdapt(context, 2)));
            setRadius_selected(typedArray.getDimensionPixelSize(R.styleable.SimpleIndicatorView_cy_indicator_radius_selected, ScreenUtils.dpAdapt(context, 3)));
            typedArray.recycle();
        } else {
            setSpace(ScreenUtils.dpAdapt(context, 8));
            setRadius_normal(ScreenUtils.dpAdapt(context, 2));
            setRadius_selected(ScreenUtils.dpAdapt(context, 3));
        }

        paint_normal = new Paint();
        setColor_normal(0x668c8c8c);
        paint_normal.setAntiAlias(true);
        paint_normal.setStyle(Paint.Style.FILL);

        paint_selected = new Paint();
        setColor_selected(0xff00a2fd);
        paint_selected.setAntiAlias(true);
        paint_selected.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.AT_MOST ?
                        MeasureSpec.makeMeasureSpec(ScreenUtils.getScreenWidth(getContext()), MeasureSpec.EXACTLY) : widthMeasureSpec,
                MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST ?
                        MeasureSpec.makeMeasureSpec(ScreenUtils.dpAdapt(getContext(), 20), MeasureSpec.EXACTLY) : heightMeasureSpec);
    }

    public int getCount() {
        return count;
    }


    @Override
    public <R extends IIndicatorView> R setCount(int count) {
        this.count = count;
        return (R) this;
    }


    public int getPosition_selected() {
        return position_selected;
    }

    public int getSpace() {
        return space;
    }

    public SimpleIndicatorView setSpace(int space) {
        this.space = space;
        return this;
    }

    @Override
    public <R extends View> R getView() {
        return (R) this;
    }

    public int getRadius_normal() {
        return radius_normal;
    }

    public SimpleIndicatorView setRadius_normal(int radius_normal) {
        this.radius_normal = radius_normal;
        return this;
    }

    public int getRadius_selected() {
        return radius_selected;
    }

    public SimpleIndicatorView setRadius_selected(int radius_selected) {
        this.radius_selected = radius_selected;
        return this;
    }

    public Paint getPaint_normal() {
        return paint_normal;
    }


    public SimpleIndicatorView setColor_normal(int color_normal) {
        paint_normal.setColor(color_normal);
        return this;
    }

    public SimpleIndicatorView setColor_selected(int color_selected) {
        paint_selected.setColor(color_selected);
        return this;
    }

    public Paint getPaint_selected() {
        return paint_selected;
    }

    @Override
    public <R extends IIndicatorView> R scroll(int position_scroll, float positionOffset) {
        this.position_scroll = position_scroll;
        this.positionOffset = positionOffset;
        return (R) this;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        width = getWidth();
        height = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(count<=1)return;
        float cx = width * 1f / 2;
        float cy = height * 1f / 2;
        float x_left_0 = cx - (2 * radius_normal * count + space * (count - 1)) * 1f / 2;
        float left_min=0;
        float left_max=0;
        for (int i = 0; i < count; i++) {
            float c_x_ = x_left_0 + (2 * radius_normal + space) * i + radius_normal;
            canvas.drawCircle(c_x_, cy, radius_normal, paint_normal);
            if(i==0)left_min=c_x_-radius_selected;
            if(i==count-1)left_max=c_x_-radius_selected;
        }

        float c_x_ = x_left_0+ (2 * radius_normal + space) * position_scroll + radius_normal;
        float left = c_x_ - radius_normal + (2 * radius_normal + space) * positionOffset-(radius_selected-radius_normal);

        left=Math.max(left_min,Math.min(left_max,left));
        canvas.drawRoundRect(left, cy - radius_selected, left + 2 * radius_selected, cy + radius_selected,
                radius_selected, radius_selected, paint_selected);
    }
}

```

## AndroidX ViewPager2滑动冲突问题
`ViewPager2`本来香得很，但是有一个`巨坑`：上下滑动能触发左右滑动
瞬间感受不到ViewPager2存在的价值了

如何解决 `ViewPager2`滑动冲突问题？

有2种方法：

**1.从ViewPager2的itemView下手(轮子提供了一个类ViewPager2Item )**

上下滑动时，让`ViewPager2`不消费，这样就解决了冲突问题
```java
public class ViewPager2Item extends FrameLayout {
    private float downX, downY;
    private float moveX;
    private float moveY;

    public ViewPager2Item(@NonNull Context context) {
        this(context, null);
    }

    public ViewPager2Item(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = ev.getX();
                downY = ev.getY();
                disallowInterceptTouchEvent(true);
                break;

            case MotionEvent.ACTION_MOVE:
                moveX = ev.getX();
                moveY = ev.getY();

                float dx = moveX - downX;
                float dy = moveY - downY;

                downX = moveX;
                downY = moveY;

                if (Math.abs(dx) > Math.abs(dy)) {
                    disallowInterceptTouchEvent(false);
                    //左右滑动,不消费
                    /**
                     * Create a new MotionEvent, filling in a subset of the basic motion
                     * values.  Those not specified here are: device id (always 0), pressure
                     * and size (always 1), x and y precision (always 1), and edgeFlags (always 0).
                     *
                     * @param downTime The time (in ms) when the user originally pressed down to start
                     * a stream of position events.  This must be obtained from {@link SystemClock#uptimeMillis()}.
                     * @param eventTime  The the time (in ms) when this specific event was generated.  This
                     * must be obtained from {@link SystemClock#uptimeMillis()}.
                     * @param action The kind of action being performed, such as {@link #ACTION_DOWN}.
                     * @param x The X coordinate of this event.
                     * @param y The Y coordinate of this event.
                     * @param metaState The state of any meta / modifier keys that were in effect when
                     * the event was generated.
                     */
//                    static public MotionEvent obtain(long downTime, long eventTime, int action,
//                    float x, float y, int metaState)
                    final MotionEvent last = ev;
                    MotionEvent motionEvent = MotionEvent.obtain(SystemClock.uptimeMillis(), last.getEventTime(),
                            MotionEvent.ACTION_DOWN, last.getX(), last.getY(), last.getMetaState());
                    super.dispatchTouchEvent(motionEvent);
                    motionEvent.recycle();
                    return false;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                disallowInterceptTouchEvent(true);
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    private void disallowInterceptTouchEvent(boolean disallow) {
        final ViewParent parent = getParent();
        if (parent != null)
            parent.requestDisallowInterceptTouchEvent(disallow);
    }
}

```

**2.从ViewPager2的外层布局下手(轮子提供了一个类ViewPager2NoConflict )**

上下滑动时，外层布局拦截，让`ViewPager2`不消费，这样就解决了冲突问题

```java
public class ViewPager2NoConflict extends FrameLayout {
    private float downX, downY;
    private float moveX;
    private float moveY;
    private ViewPager2 viewPager2;
    //多指触摸、比如图片缩放操作
    private int pointer_others_count = 0;

    public ViewPager2NoConflict(@NonNull Context context) {
        this(context, null);
    }

    public ViewPager2NoConflict(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.viewPager2 = new ViewPager2(context);
        addView(viewPager2, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public ViewPager2 getViewPager2() {
        return viewPager2;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                downX = ev.getX();
                downY = ev.getY();
                pointer_others_count = 0;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                pointer_others_count++;
                break;
            case MotionEvent.ACTION_MOVE:
                moveX = ev.getX();
                moveY = ev.getY();
                float dx = moveX - downX;
                float dy = moveY - downY;

                downX = moveX;
                downY = moveY;

                if (pointer_others_count <= 0 && Math.abs(dx) < Math.abs(dy)) return true;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                pointer_others_count--;
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

}

```

## AndroidX ViewPager2滑动冲突问题真的能完美解决吗？
当使用场景简单，布局嵌套简单时，是可以完美解决滑动冲突的

然而，当使用场景复杂，布局嵌套复杂时，比如ViewPager2里嵌套RecyclerView，冲突虽然可以解决，但是很麻烦，如果用一个控件，还要自己去做一堆适配工作，这种控件不用也罢

## 所以，小编还是强烈建议复杂情况下使用Androidx 的ViewPager


## 相关API
ViewPagerAdapter

ViewPagerLoopAdapter

ViewPager2Adapter

ViewPager2LoopAdapter

拥有一系列`add`、`remove`函数

## ViewPager、ViewPager2无限轮播原理剖析
ViewPager、ViewPager2无限轮播原理一致：

getCount返回数量为真实数量`+2`，
当滑动到第`0张`时，立马切换到第`getCount() - 2`张
当滑动到`getCount() - 1`张时，立马切换到第1张

 **ViewPager 无限轮播原理**

```java
  @Override
    public final int getCount() {
        if (list_bean.size() <= 1) return list_bean.size();
        return list_bean.size() + 2;
    }
    private int getPosition(int position) {
        return position == 0 ? list_bean.size() - 1 : position == getCount() - 1 ? 0 : position - 1;
    }

    @Override
    public final Object instantiateItem(@NonNull ViewGroup container, int position) {
       return super.instantiateItem(container,getPosition(position));
    }

    @Override
    public final void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, getPosition(position), object);
    }
```

```java
 @Override
    public final void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
        if (isLoopAuto() && !isLoopStarted && list_bean.size() > 1) {
            startLoop();
            isLoopStarted = true;
        }
        indicatorView.setCount(list_bean.size())
                .scroll(position - 1, positionOffset)
                .getView()
                .invalidate();
    }

 @Override
    public final void onPageScrollStateChanged(int state) {
        super.onPageScrollStateChanged(state);
        switch (state) {
            case ViewPager.SCROLL_STATE_IDLE:
                //验证当前的滑动是否结束
                if (viewPager.getCurrentItem() == 0) {
                    viewPager.setCurrentItem(getCount() - 2, false);
                    return;
                }
                if (viewPager.getCurrentItem() == getCount() - 1) {
                    viewPager.setCurrentItem(1, false);
                    return;
                }
        }
    }
```

## 源码如下

```java
public abstract class ViewPagerLoopAdapter<T> extends ViewPagerAdapter<T> {
    private boolean loopAuto = true;
    private ViewPager viewPager;
    private IIndicatorView indicatorView;
    private Timer timer;
    private TimerTask timerTask;
    private android.os.Handler handler;
    private long periodLoop = 3000;
    private boolean isLoopStarted = false;
//    private int position_selected_last = -1;

    public ViewPagerLoopAdapter(final ViewPager viewPager, final IIndicatorView indicatorView) {
        super(viewPager);
        this.viewPager = viewPager;
        this.indicatorView = indicatorView;
        handler = new android.os.Handler(Looper.getMainLooper());
    }

    @Override
    public final void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
        if (isLoopAuto() && !isLoopStarted && list_bean.size() > 1) {
            startLoop();
            isLoopStarted = true;
        }
        indicatorView.setCount(list_bean.size())
                .scroll(position - 1, positionOffset)
                .getView()
                .invalidate();
    }

    @Override
    public final void onPageScrollStateChanged(int state) {
        super.onPageScrollStateChanged(state);
        switch (state) {
            case ViewPager.SCROLL_STATE_IDLE:
                //验证当前的滑动是否结束
                if (viewPager.getCurrentItem() == 0) {
                    viewPager.setCurrentItem(getCount() - 2, false);
                    return;
                }
                if (viewPager.getCurrentItem() == getCount() - 1) {
                    viewPager.setCurrentItem(1, false);
                    return;
                }
        }
    }

    @Override
    public final void onPageSelected(int position) {
//        LogUtils.log("onPageSelected", position);
//        int p=position-1;
//        ViewPagerHolder viewPagerHolder = getViewPagerHolderFromPosition(p);
//        if (viewPagerHolder != null && p >=0 && p < list_bean.size())
//            onPageSelected(viewPagerHolder, p, list_bean.get(p));
//        super.onPageSelected(position - 1);
    }

    @Override
    public final void onPageSelected(ViewPagerHolder viewPagerHolder, int position, @NonNull T bean) {
//        LogUtils.log("onPageSelected000", position);
    }

    @Override
    public final void onViewDetachedFromWindow(View v) {
        super.onViewDetachedFromWindow(v);
        stopLoop();
    }

    private int getPosition(int position) {
        return position == 0 ? list_bean.size() - 1 : position == getCount() - 1 ? 0 : position - 1;
    }

    @Override
    public final Object instantiateItem(@NonNull ViewGroup container, int position) {
        return super.instantiateItem(container, getPosition(position));
//        final int p = getPosition(position);
//        View view = LayoutInflater.from(container.getContext()).inflate(getItemLayoutID(p, list_bean.get(p)), container, false);
//        container.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        final ViewPagerHolder viewPagerHolder = new ViewPagerHolder(view);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onItemClick(viewPagerHolder, p, list_bean.get(p));
//            }
//        });
//        bindDataToView(viewPagerHolder, p, list_bean.get(p));
//        LogUtils.log("onPageSelectedhavesparseArrayViewPagerHolder.put", p);
//        sparseArrayViewPagerHolder.put(p, viewPagerHolder);
//        if (position_selected_last == -1) {
//            position_selected_last = p;
//            onPageSelected(position);
//        }
//        return viewPagerHolder;
    }

    @Override
    public final void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, getPosition(position), object);
    }

    @Override
    public final int getCount() {
        if (list_bean.size() <= 1) return list_bean.size();
        return list_bean.size() + 2;
    }

    public long getPeriodLoop() {
        return periodLoop;
    }

    public void setPeriodLoop(long periodLoop) {
        this.periodLoop = periodLoop;
    }

    public boolean isLoopAuto() {
        return loopAuto;
    }

    public void setLoopAuto(boolean loopAuto) {
        this.loopAuto = loopAuto;
    }

    public void startLoop() {
        if (!loopAuto) return;
        stopLoop();
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    }
                });
            }
        };
        try {
            timer.scheduleAtFixedRate(timerTask, periodLoop, periodLoop);
        } catch (Exception e) {

        }
    }

    public void stopLoop() {
        if (timer != null) timer.cancel();
        if (timerTask != null) timerTask.cancel();
        timer = null;
        timerTask = null;
    }

    public void setStartItem() {
        viewPager.setCurrentItem(1, false);
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        setStartItem();
        indicatorView.setCount(list_bean.size())
                .getView()
                .invalidate();
    }
}

```

```java
public abstract class ViewPager2LoopAdapter<T> extends ViewPager2Adapter<T> {
    private boolean loopAuto = true;
    private ViewPager2 viewPager2;
    private IIndicatorView indicatorView;
    private Timer timer;
    private TimerTask timerTask;
    private android.os.Handler handler;
    private long periodLoop = 3000;
    private boolean isLoopStarted = false;

    public ViewPager2LoopAdapter(final ViewPager2 viewPager2, final IIndicatorView indicatorView) {
        super(viewPager2);
        this.viewPager2 = viewPager2;
        this.indicatorView = indicatorView;
        handler = new android.os.Handler(Looper.getMainLooper());
    }

    @Override
    public final void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
        if (isLoopAuto() && !isLoopStarted && list_bean.size() > 1) {
            startLoop();
            isLoopStarted = true;
        }
        indicatorView.setCount(list_bean.size())
                .scroll(position - 1, positionOffset)
                .getView()
                .invalidate();
    }

    @Override
    public final void onPageScrollStateChanged(int state) {
        super.onPageScrollStateChanged(state);
        //验证当前的滑动是否结束
        if (state == ViewPager2.SCROLL_STATE_IDLE) {
            if (viewPager2.getCurrentItem() == 0) {
                viewPager2.setCurrentItem(getItemCount() - 2, false);
                return;
            }
            if (viewPager2.getCurrentItem() == getItemCount() - 1) {
                viewPager2.setCurrentItem(1, false);
                return;
            }
        }
    }

    /**
     * ViewPager2,因为在从最后一页切换到第一页的时候，onPageSelected会回调3次，所以，废弃
     */
    @Override
    public final void onPageSelected(int position) {
//        com.cy.loopviewpageradapter.LogUtils.log("onPageSelected", position);
//        final int p = position - 1;
//        ViewPager2Holder viewPager2Holder = getViewPagerHolderFromPosition(p);
//        if (viewPager2Holder == null) {
//            sparseArrayCallbackViewPager2Holder.put(p, new CallbackViewPager2Holder() {
//                @Override
//                public void onBindViewHolder(int position, ViewPager2Holder viewPager2Holder) {
//                    if (p >= 0 && p < list_bean.size())
//                        onPageSelected(viewPager2Holder, p, list_bean.get(p));
//                    sparseArrayCallbackViewPager2Holder.remove(p);
//                }
//            });
//            return;
//        }
//        com.cy.loopviewpageradapter.LogUtils.log("onPageSelectedviewPager2Holder==null", viewPager2Holder == null);
//        if (p >= 0 && p < list_bean.size())
//            onPageSelected(viewPager2Holder, p, list_bean.get(p));
    }

    @Override
    public final void onViewDetachedFromWindow(View v) {
        super.onViewDetachedFromWindow(v);
        stopLoop();
    }

    @Override
    public final void onPageSelected(ViewPager2Holder viewPager2Holder, int position, @NonNull T bean) {
    }

    private int getPosition(int position) {
        return position == 0 ? list_bean.size() - 1 : position == getItemCount() - 1 ? 0 : position - 1;
    }

    @Override
    public final void onBindViewHolder(@NonNull ViewPager2Holder holder, int position) {
        super.onBindViewHolder(holder, getPosition(position));
    }

    @Override
    public final void onViewRecycled(@NonNull ViewPager2Holder holder) {
        int position = holder.getAdapterPosition();
        int p=getPosition(position);
        sparseArrayViewPager2Holder.remove(p);
        if (p < 0 || p >= list_bean.size()) return;
        onViewRecycled(p, list_bean.get(p));
    }

    @Override
    public int getItemViewType(int position) {
        int p = getPosition(position);
        return getItemLayoutID(p, list_bean.get(p));
    }

    @Override
    protected void handleClick(final ViewPager2Holder holder) {
        //添加Item的点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition() - 1;
                onItemClick(holder, position, list_bean.get(position));
            }
        });
    }

    @Override
    public final int getItemCount() {
        if (list_bean.size() <= 1) return list_bean.size();
        return list_bean.size() + 2;
    }

    public long getPeriodLoop() {
        return periodLoop;
    }

    public void setPeriodLoop(long periodLoop) {
        this.periodLoop = periodLoop;
    }

    public boolean isLoopAuto() {
        return loopAuto;
    }

    public void setLoopAuto(boolean loopAuto) {
        this.loopAuto = loopAuto;
    }

    public void startLoop() {
        if (!loopAuto) return;
        stopLoop();
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
                    }
                });
            }
        };
        try {
            timer.scheduleAtFixedRate(timerTask, periodLoop, periodLoop);
        } catch (Exception e) {

        }
    }

    public void stopLoop() {
        if (timer != null) timer.cancel();
        if (timerTask != null) timerTask.cancel();
        timer = null;
        timerTask = null;
    }

    public void setStartItem() {
        viewPager2.setCurrentItem(1, false);
    }

}

```

## 面向接口编程（面向多态编程）的思想
小编特别喜欢JAVA这门语言，小编个人认为JAVA将面向对象编程的思想展现的淋漓尽致。

整个轮子用得最多的编程思想就是多态，`多态`是设计模式和框架的基础。

`接口`和`泛型`是实现多态的2把利器。

编程思想暂且稍微透露，后面小编会专门出一个SDK开发入门教程，详细讲述设计模式和多态，敬请关注。
## 欢迎联系、指正、批评



Github:[https://github.com/AnJiaoDe](https://github.com/AnJiaoDe)

简书：[https://www.jianshu.com/u/b8159d455c69](https://www.jianshu.com/u/b8159d455c69)

CSDN：[https://blog.csdn.net/confusing_awakening](https://blog.csdn.net/confusing_awakening)

ffmpeg入门教程:[https://blog.csdn.net/confusing_awakening/article/details/102007792](https://blog.csdn.net/confusing_awakening/article/details/102007792)

 微信公众号
 ![这里写图片描述](https://imgconvert.csdnimg.cn/aHR0cDovL3VwbG9hZC1pbWFnZXMuamlhbnNodS5pby91cGxvYWRfaW1hZ2VzLzExODY2MDc4LWZjZmJiNDUxNzVmOTlkZTA)

QQ群

![这里写图片描述](https://imgconvert.csdnimg.cn/aHR0cDovL3VwbG9hZC1pbWFnZXMuamlhbnNodS5pby91cGxvYWRfaW1hZ2VzLzExODY2MDc4LWEzMWZmNDBhYzY4NTBhNmQ)

