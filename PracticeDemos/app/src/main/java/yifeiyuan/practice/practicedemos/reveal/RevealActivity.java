package yifeiyuan.practice.practicedemos.reveal;

import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import yifeiyuan.practice.practicedemos.R;
import yifeiyuan.practice.practicedemos.base.BaseActivity;

public class RevealActivity extends BaseActivity {

    @InjectView(R.id.bg_reval)
    RevealBackgroundView mBgReval;
    @InjectView(R.id.tv_reval)
    TextView mTvReval;
    @InjectView(R.id.v_reveal)
    RevealView mVReveal;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reval;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        final int[] location = getIntent().getExtras().getIntArray("location");

        mBgReval.setFillPaintColor(getResources().getColor(R.color.primary));
        mBgReval.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                //必须remove掉 不然会重复调用
                mBgReval.getViewTreeObserver().removeOnPreDrawListener(this);
                mBgReval.startFromLocation(location);
                return false;
            }
        });

        mBgReval.setOnStateChangeListener(new RevealBackgroundView.OnStateChangeListener() {
            @Override
            public void onStateChange(int state) {
                if (state == RevealBackgroundView.STATE_FINISHED) {
                    mBgReval.setVisibility(View.GONE);
                    mTvReval.setVisibility(View.VISIBLE);
                }
            }
        });

//        mVReveal.setCallback(new RevealView.Callback() {
//            @Override
//            public void onRevealEnd() {
//                mVReveal.setVisibility(View.GONE);
//
//                mTvReval.setVisibility(View.VISIBLE);
//            }
//        });
//
//        mVReveal.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//            @Override
//            public boolean onPreDraw() {
//                //必须remove掉 不然会重复调用
//                mVReveal.getViewTreeObserver().removeOnPreDrawListener(this);
//                mVReveal.startReveal();
//                return false;
//            }
//        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }
}
