package www.wtu.com.run.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import www.wtu.com.run.R;
import www.wtu.com.run.adapter.RunAdapter;
import www.wtu.com.run.app.AppContext;
import www.wtu.com.run.beans.RunData;
import www.wtu.com.run.beans.dao.RunDataDao;

public class RunRecordActivity extends BaseActivity {
    private ListView cardsList;
    private List<RunData> items = new ArrayList<>();

    @Override
    protected int returnLayoutRes() {
        return R.layout.activity_run_record;
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

    private RunAdapter createAdapter() {
//        RunData data ;
//        for (int i = 0; i < 100; i++) {
//            data = new RunData(new Long((long)i),"zzx"+1,"总距离："+i,"总步数："+i,"总时间："+i,"总热量"+i,new Date().getTime());
//            items.add(data);
//        }
//        items = AppContext.getApp().getDaoSession().getRunDataDao().loadAll();

        items = AppContext.getApp().getDaoSession().getRunDataDao().queryBuilder()
                .orderDesc(RunDataDao.Properties.Date)
                .list();
        return new RunAdapter(RunRecordActivity.this, items);

    }



    private final class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            Intent intent = new Intent(RunRecordActivity.this,DataShowActivity.class);
            // 新建Bundle对象
            Bundle mBundle = new Bundle();
            // 放入account对象
            mBundle.putInt("type",1);
            mBundle.putSerializable("data", items.get(position));
            intent.putExtras(mBundle);

            startActivity(intent);
        }
    }


}
