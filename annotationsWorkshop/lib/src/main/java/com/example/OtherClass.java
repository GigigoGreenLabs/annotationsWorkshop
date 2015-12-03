package com.example;

import android.support.annotation.WorkerThread;

/**
 * Created by rui.alonso on 3/12/15.
 */
@WorkerThread
public class OtherClass {

  MyClass m;

  void p() {
    m = new MyClass();
    m.executeMain();
  }
}
