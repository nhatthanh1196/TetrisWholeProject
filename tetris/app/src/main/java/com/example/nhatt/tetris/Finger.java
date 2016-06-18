package com.example.nhatt.tetris;

import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;
import android.view.GestureDetector;
import android.support.v4.view.GestureDetectorCompat;


import java.util.Calendar;

class Finger {
    float x;
    float y;
    boolean active;


    static final float SCROLL_THRESHOLD = 10;

    static public Finger finger;

    static boolean move=false;

    static boolean horizon=false;

    static boolean vertical=false;

    static void setup() {
            finger = new Finger();
    }



  static public void onTouchEvent(TetrisView mother, MotionEvent event) {

        final int action = event.getAction();

        switch (action & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN: {
                /* Only one touch event is stored in the MotionEvent. Extract
                 * the pointer identifier of this touch from the first index
                 * within the MotionEvent object. */
                //int id = event.getPointerId(0);
                finger.x = event.getX();
                finger.y = event.getY();
                finger.active = true;

                //mother.touch(id, event.getX(0), event.getY(0));
              //mother.touch()

                break;
            }

            case MotionEvent.ACTION_UP: {
                /* Final pointer has gone up and has ended the last pressed
                 * gesture.
                 * Extract the pointer identifier for the only event stored in
                 * the MotionEvent object and remove it from the list of active
                 * touches. */
                //int id = event.getPointerId(0);
                finger.active = false;

                if (event.getY() <= mother.di){
                  if (event.getX()>=mother.di*3 && event.getX() < mother.di*7){
                    mother.start = true;
                  }
                  else {
                    if (event.getX()>= mother.di*7 && event.getX() < mother.di*11){
                      if (mother.start) {
                        if (mother.pause) {
                          mother.resume = true;
                          mother.pause = false;
                        } else if (mother.resume) {
                          mother.pause = true;
                          mother.resume = false;
                        }
                      }
                    }
                    else {
                      if (event.getX()>=mother.di*11){
                        mother.reset= true;
                      }
                    }
                  }
                }
                else {

                  if (!move) {
                    mother.Rotation = true;
                  }
                  else {
                      if (horizon){
                          mother.Horizontal=true;
                          mother.ecartX = (int) (event.getX() - finger.x);
                          horizon=false;
                      }
//                      if (vertical){
//                          mother.vertical = true;
//                          mother.ecartY = (int) (event.getY() - finger.y);
//                         // Log.d("verticalEcartYInFinger", mother.ecartY + "");
//                          vertical=false;
//                      }
                    move = false;
                  }
                  finger.active = false;
                }

                break;
            }

            case MotionEvent.ACTION_MOVE: {
                /* A change event happened during a pressed gesture. (Between
                 * ACTION_DOWN and ACTION_UP or ACTION_POINTER_DOWN and
                 * ACTION_POINTER_UP) */
                if (event.getY() > mother.di) {
                    if (finger.active && (Math.abs(finger.x - event.getX()) > SCROLL_THRESHOLD)) {
                        move = true;
                        horizon=true;
                    }
//                    if (finger.active && (event.getY() - finger.y) > SCROLL_THRESHOLD) {
//                        move = true;
//                        vertical=true;
//                    }
                }

                break;
            }


        }
    }

    public Finger() {
        x = 0;
        y = 0;
        active = false;
    }
}

