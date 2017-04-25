package www.wtu.com.run.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import www.wtu.com.run.R;
import www.wtu.com.run.adapter.ClimbAdapter;
import www.wtu.com.run.beans.ClimbData;

public class ClimbRecordActivity extends BaseActivity {
    private ListView cardsList;
    private List<ClimbData> items = new ArrayList<>();
    @Override
    protected int returnLayoutRes() {
        return R.layout.activity_climb_record;
    }

    @Override
    protected void initComponent() {
        cardsList = (ListView) findViewById(R.id.cards_list);
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    protected void initData() {
        cardsList.setAdapter(createAdapter());
        cardsList.setOnItemClickListener(new ListItemClickListener());
    }

    private ClimbAdapter createAdapter() {
        ClimbData data = null;
        for (int i = 0; i < 100; i++) {
            data = new ClimbData(new Long((long)i),"zzx"+1,"总距离："+i,"总步数："+i,"总时间："+i,"总热量"+i,new Date().getTime());
            items.add(data);
        }
        return new ClimbAdapter(ClimbRecordActivity.this, items);
    }



    private final class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            Intent intent = new Intent(ClimbRecordActivity.this,DataShowActivity.class);
            // 新建Bundle对象
            Bundle mBundle = new Bundle();
            mBundle.putInt("type",2);
            mBundle.putSerializable("data", items.get(position));
            intent.putExtras(mBundle);

            startActivity(intent);
        }
    }


}
