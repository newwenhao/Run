package www.wtu.com.run.activities;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import www.wtu.com.run.R;
import www.wtu.com.run.app.AppContext;
import www.wtu.com.run.utils.ToastUtils;

public class PersonInfoActivity extends BaseActivity {

    private EditText etHeight,etWeight;
    private Button btnDone;

    @Override
    protected int returnLayoutRes() {
        return R.layout.activity_person_info;
    }

    @Override
    protected void initComponent() {
        etHeight = (EditText) findViewById(R.id.et_height);
        etWeight = (EditText) findViewById(R.id.et_weight);
        btnDone = (Button) findViewById(R.id.btn_done);
    }

    @Override
    protected void bindEvent() {
        btnDone.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String height = etHeight.getText().toString().trim();
                String weight = etWeight.getText().toString().trim();

                if(TextUtils.isEmpty(height)){
                    ToastUtils.ToastShort("身高不能为空");
                    return ;
                }

                if(TextUtils.isEmpty(weight)){
                    ToastUtils.ToastShort("体重不能为空");
                    return ;
                }



                AppContext.getApp().getSharePreferenceInstance().setHeight(Integer.parseInt(height));
                AppContext.getApp().getSharePreferenceInstance().setWeight(Integer.parseInt(weight));
                ToastUtils.ToastShort("设置成功");
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        int height = AppContext.getApp().getSharePreferenceInstance().getHeight();
        int weight = AppContext.getApp().getSharePreferenceInstance().getWeight();
        etHeight.setText((height != 0 ? String.valueOf(height) : ""));
        etWeight.setText((weight != 0 ? String.valueOf(weight) : ""));
    }
}
