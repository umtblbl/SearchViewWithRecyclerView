package com.example.example3;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SearchView searchView;
    NestedScrollView nestedScrollView;
    boolean isUp = false;
    Handler handler;
    int mScrollY, updatedY;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint({"WrongConstant", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.searchView);
        recyclerView = findViewById(R.id.recyclerView);
        nestedScrollView = findViewById(R.id.nestedScrollView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new RecyclerViewAdapter(getApplicationContext()));
        handler = new Handler();

        // recyclerView.setNestedScrollingEnabled(false);


        nestedScrollView.smoothScrollTo(0, (searchView.getHeight() + searchView.getHeight() / 5));


        recyclerView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                MainActivity.super.onTouchEvent(event);

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    isUp = true;
                    Log.d("TEST2", "UP");
                    if (updatedY == mScrollY) {
                        ScrollTo();
                    }
                }
                return false;
            }
        });


        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, final int scrollY, int oldScrollX, int oldScrollY) {
                mScrollY = scrollY;

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        updatedY = scrollY;
                        scrollControl();
                    }
                }, 50);


            }
        });

    }


    public void scrollControl() {

        Log.d("TEST2", "mScrollY=" + mScrollY + ", updateY=" + updatedY + "isUp:" + isUp);
        if (updatedY == mScrollY && isUp) {
            Log.d("TEST2", "mScrollY=" + mScrollY + ", updateY=" + updatedY);
            ScrollTo();
        }

    }


    private void ScrollTo() {
        Log.d("TEST4", "ScrollTo");
        int scrollHeight = searchView.getHeight();//SearchView Boyutu

        if (mScrollY <= (scrollHeight / 2)) {
            //Kullanıcı, parmağını searchView boyutunun üst kısmının yarısından daha yukarda bıraktıysa.
            nestedScrollView.smoothScrollTo(0, 0);
            Log.d("TEST4", "1");

            isUp = false;

        }

        if (mScrollY > (searchView.getHeight() / 2) && mScrollY < scrollHeight) {
            //Kullanıcı, parmağını searchView yarı boyutu ile tam searchView boyutu arasında bıraktıysa
            nestedScrollView.smoothScrollTo(0, scrollHeight);
            Log.d("TEST4", "2");

            isUp = false;
        }

    }


    public static int getScreenHeightInDPs(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);

        int heightInDP = Math.round(dm.heightPixels / dm.density);
        return heightInDP;
    }

    public static int getScreenWidthInDPs(Context context) {
        android.util.DisplayMetrics dm = new android.util.DisplayMetrics();
        android.view.WindowManager windowManager = (android.view.WindowManager) context.getSystemService(WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        int widthInDP = Math.round(dm.widthPixels / dm.density);
        return widthInDP;
    }
}
