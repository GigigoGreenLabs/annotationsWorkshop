package com.example;

import android.support.annotation.CheckResult;
import android.support.annotation.MainThread;
import android.support.annotation.WorkerThread;

@MainThread
public class MyClass implements InterfacePrivada {

  @WorkerThread
  void m () {
    executeMain();
  }

  @Override public void executeMain() {

  }

  @Override public void executeAsync() {

  }
}

