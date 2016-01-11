package com.example.sourabh.dragdropandlongclicklistenerexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnTouchListener,View.OnDragListener {

    private Button button;
    private TextView textView;
    private LinearLayout redLayout;
    private LinearLayout blueLayout;

    //When TextView gets touched or clicked, OnTouch() will be called
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN)  //If TextView just touches
        {
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);  //For nice transition of shadow
            v.startDrag(null, shadowBuilder, v, 0);  //When Dragging Starts. First Parameter is Clip data & last must be 0 always
            v.setVisibility(View.INVISIBLE);
            return true;
        }
        else return false;
    }

    //When textView gets dropped in either linearLayout
    @Override
    public boolean onDrag(View v, DragEvent event) {
        switch(event.getAction())
        {

            case DragEvent.ACTION_DRAG_STARTED:
                Toast.makeText(MainActivity.this,"Drag started",Toast.LENGTH_SHORT).show();
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                Toast.makeText(MainActivity.this,"Drag entered into " + v.toString(),Toast.LENGTH_SHORT).show();
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                Toast.makeText(MainActivity.this,"Drag exited from " + v.toString(),Toast.LENGTH_SHORT).show();
                break;
            case DragEvent.ACTION_DROP:
                Toast.makeText(MainActivity.this,"Dropped",Toast.LENGTH_SHORT).show();
                View view = (View) event.getLocalState();  //Returns TextView in this example
                ViewGroup owner = (ViewGroup) view.getParent();  //Return the parent ViewGroup of TextView in this example
                owner.removeView(view); //Removing TextView from LinearLayout in which it was present earlier
                LinearLayout container = (LinearLayout) v;
                container.addView(view);  //Adding TextView in dropped LinearLayout
                view.setVisibility(View.VISIBLE);  //Making TextView visible back
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                Toast.makeText(MainActivity.this, "Drag ended", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button);
        textView = (TextView)findViewById(R.id.textView);
        redLayout = (LinearLayout)findViewById(R.id.redLayout);
        blueLayout = (LinearLayout)findViewById(R.id.blueLayout);

        //Setting Listeners to different Views
        textView.setOnTouchListener(this);
        redLayout.setOnDragListener(this);
        blueLayout.setOnDragListener(this);

        //OnClickListener
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "You Clicked me", Toast.LENGTH_SHORT).show();
            }
        });

        //OnLongClickListener
        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MainActivity.this, "You long clicked me", Toast.LENGTH_SHORT).show();
                return true;
            }
        });






    }
}
