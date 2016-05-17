package dockusan.com.androidbaseframework.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.effect.EffectFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import dockusan.com.androidbaseframework.helpers.EventBusHelper;

/**
 * Created by SF on 11/05/2016.
 */
public abstract class BaseActivity extends AppCompatActivity {

    ProgressDialog dialogLoading;

    boolean isUnregistEventBus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        onPreSetContentView(savedInstanceState);
        super.onCreate(savedInstanceState);
//        Intent intent = getIntent();
//        String action = intent.getAction();
//        if (Intent.ACTION_VIEW.equals(action)) {
//            handleDeepLinkData(intent.getData());
//        }
        setContentView(setContentViewId());
        // init actionbar
        ButterKnife.bind(this);
        EventBusHelper.register(this);
        isUnregistEventBus = false;
        initView();
        initDialogApi();
        initData();
    }

    @Subscribe
    public void sampleMethodPreventEventbusCrash(EffectFactory event) {

    }

    private void initDialogApi() {
        dialogLoading = new ProgressDialog(this);
    }

    protected boolean checkApiDialogIsShow() {
        return dialogLoading.isShowing();
    }

    @Override
    protected void onDestroy() {
        if (!isUnregistEventBus) {
            EventBusHelper.unregister(this);
        }
        super.onDestroy();
    }


    private void showApiDialog(ProgressDialog dialogLoading) {
        if (dialogLoading != null && !checkApiDialogIsShow()) {
            dialogLoading.show();
        }
    }

    /**
     * Handle data before setContentView call
     *
     * @param savedInstanceState
     */
    protected void onPreSetContentView(Bundle savedInstanceState) {

    }

    /**
     * @return layout of activity
     */
    public abstract int setContentViewId();

    /**
     * Define your view
     */
    public abstract void initView();

    /**
     * Setup your data
     */
    public abstract void initData();


    @Override
    public void startActivity(Intent intent) {
        EventBusHelper.unregister(this);
        isUnregistEventBus = true;
        super.startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Toast.makeText(this,String.valueOf(SharedPrefUtils.getBoolean("isForcedLogout",false)), Toast.LENGTH_LONG).show();
        if (isUnregistEventBus) {
            EventBusHelper.register(this);
            isUnregistEventBus = false;
        }
    }

}


