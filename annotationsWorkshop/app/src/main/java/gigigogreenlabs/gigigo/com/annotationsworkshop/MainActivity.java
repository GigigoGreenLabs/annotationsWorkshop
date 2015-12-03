package gigigogreenlabs.gigigo.com.annotationsworkshop;

import android.Manifest;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.support.annotation.AnyRes;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IntDef;
import android.support.annotation.IntRange;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.annotation.Size;
import android.support.annotation.StringRes;
import android.support.annotation.UiThread;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.WorkerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    createNullnessAnnotations();
    createResourceTypeAnnotations();
    createTypedefAnnotations();
    createThreadingAnnotations();
    createColorIntegersAnnotations();
    createValueConstraintsAnnotations();
    createPermissionsAnnotations();
    createOverridingMethodsAnnotations();
    createReturnValuesAnnotations();
  }

  //region NULLNESS

  TextView nullableTextView;
  TextView nullableTextView2;

  private void createNullnessAnnotations() {
    //@Nullable, @NonNull
    findViewById(R.id.resourceType_text).setBackgroundColor(1);

    getNullableTextView2().setText("ESTO ES UN NULL");
  }

  private @Nullable TextView getNullableTextView2() {
    return nullableTextView2;
  }
  //endregion

  //region RESOURCE TYPE

  TextView resourceTypeText;
  ImageView resourceTypeImage;
  TextView resourceTypeColor;

  private void createResourceTypeAnnotations() {
    //@StringRes
    setTextViewTextByRes(R.string.resourceType_annotation_title);
    //setTextViewTextByRes(0x7f060017);
    //setTextViewTextByRes(R.id.resourceType_text);

    //@StringRes: no annotation
    setTextViewText(R.string.resourceType_annotation_title);
    setTextViewText(0x7f060017);
    setTextViewText(R.id.resourceType_text);

    //@DrawableRes
    setImageViewDrawable(R.mipmap.ic_launcher);
    //setImageViewDrawable(0x7f030000);

    //@ColorRes
    setViewColor(R.color.blue);
    //setViewColor(0x7f060017);

    //@AnyRes
    getResourceName(R.string.resourceType_annotation_title);
    getResourceName(-12121);
  }

  private void setTextViewTextByRes(@StringRes int text) {
    resourceTypeText.setText(text);
  }

  private void setTextViewText(int text) {
    resourceTypeText.setText(text);
  }

  private void setImageViewDrawable(@DrawableRes int drawable) {
    resourceTypeImage.setImageResource(drawable);
  }

  private void setViewColor(@ColorRes int color) {
    //resourceTypeColor.setBackgroundColor(color);
    setViewColorByRes(color);
  }

  private void setViewColorByRes(@ColorRes int color) {
  }

  private String getResourceName(@AnyRes int resId){
    return getResources().getResourceName(resId);
  }
  //endregion

  //region TYPEDEF

  @IntDef({WIFI, MOBILE, DISCONNECT, ERROR})
  @Retention(RetentionPolicy.SOURCE)
  public @interface ConnectionStatus {}

  public static final int WIFI = 0;
  public static final int MOBILE = 1;
  public static final int DISCONNECT = 2;
  public static final int ERROR = 3;

  private int connectionStatus;

  @ConnectionStatus
  private int getConnectionStatus() {
    setConnectionStatus(0);
    return WIFI;
  }
  private void setConnectionStatus(int connStatus) {
    this.connectionStatus = connStatus;
  }

  private TextView textViewTypedef;
  private void createTypedefAnnotations() {
    //textViewTypedef.setLayoutDirection(1);
  }
  //endregion

  //region THREADING
  private TextView textViewThread;

  private void createThreadingAnnotations() {

    new AsyncTask<String, String, String>() {
      @MainThread
      @Override protected void onProgressUpdate(String... values) {
        textViewThread.setText("esto SI se puede hacer");

        super.onProgressUpdate(values);
      }

      @WorkerThread
      @Override protected String doInBackground(String... params) {
        //textViewThread.setText("esto NO se puede hacer");
        return null;
      }
    };
  }

  @MainThread
  private void executeInMainThread() {

  }

  @WorkerThread
  private void executeInWorkerThread() {
    //executeInMainThread();
  }
  //endregion

  //region COLOR INTEGERS
  private View viewColor;

  private void createColorIntegersAnnotations() {
    setColorInt(1);
    //setColorInt(android.R.color.background_dark);
  }

  private void setColorInt(@ColorInt int color) {
    viewColor.setBackgroundColor(color);
  }
  //endregion

  //region VALUE CONSTRAINTS
  private void createValueConstraintsAnnotations() {
    //setAlphaFloat((float)2.0);
    setAlpha(2);

    int[] array = new int[3];
    setSize(array);
  }

  public void setAlphaFloat(@FloatRange(from=0.0, to=1.0) float alpha) {
  }

  public void setAlpha(@IntRange(from=0,to=255) int alpha) {
  }

  public void setSize(@Size(3) int[] array) {
  }
  //endregion

  //region PERMISSIONS
  private void createPermissionsAnnotations() {
    getWifiInfo();
  }

  @RequiresPermission(Manifest.permission.ACCESS_WIFI_STATE)
  public WifiInfo getWifiInfo() {
    WifiManager wifiManager = (WifiManager) getBaseContext().getSystemService(Context.WIFI_SERVICE);
    WifiInfo wifiInfo = wifiManager.getConnectionInfo();
    return wifiInfo;
  }

  //endregion

  //region OVERRIDING METHODS
  private void createOverridingMethodsAnnotations() {
    B b = new B();
    b.methodASuper();
    b.methodA();
  }

  public class A {
    @CallSuper
    protected void methodASuper(){
    }

    protected void methodA(){
    }
  }
  public class B extends A {
    @Override protected void methodASuper() {
      super.methodASuper();
    }

    @Override protected void methodA() {
    }
  }
  //endregion

  //region RETURN VALUES
  private void createReturnValuesAnnotations() {
   checkCustomPermission(Manifest.permission.ACCESS_FINE_LOCATION);
  }

  @CheckResult(suggest="#getCustomPermission(String)")
  private int checkCustomPermission(@NonNull String permission) {
    //just check if permission is
    return 1;
  }

  private int getCustomPermission(@NonNull String permission) {
    //obtains permission and return it
    return 1;
  }
  //endregion
}
