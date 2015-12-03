package com.example;

import android.support.annotation.MainThread;
import android.support.annotation.WorkerThread;

/**
 * Created by rui.alonso on 3/12/15.
 */
public interface InterfacePrivada {
  @MainThread
  void executeMain();

  @WorkerThread
  void executeAsync();
}
