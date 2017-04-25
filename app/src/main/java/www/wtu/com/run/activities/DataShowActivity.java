package www.wtu.com.run.activities;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;
import www.wtu.com.run.R;
import www.wtu.com.run.beans.ClimbData;
import www.wtu.com.run.beans.RunData;
import www.wtu.com.run.utils.DateUtils;

public class DataShowActivity extends BaseActivity {

    private TextView info;
    private TextView distance;
    private TextView bushu;
    private TextView time;
    private TextView kll;
    private TextView date;
    
    public final static String[] months = new String[]{"距离", "步数", "时间", "消耗"};

    private float dataArr [] = new float[4];

    private ColumnChartView chartBottom;
    private ColumnChartData columnData;

    @Override
    protected int returnLayoutRes() {
        return R.layout.activity_data_show;
    }

    @Override
    protected void initComponent() {
        chartBottom = (ColumnChartView) findViewById(R.id.chart_bottom);

        info = (TextView) findViewById(R.id.et_info);
        distance = (TextView) findViewById(R.id.list_item_distance);
        bushu = (TextView) findViewById(R.id.list_item_bs);
        time = (TextView) findViewById(R.id.list_item_time);
        kll = (TextView) findViewById(R.id.list_item_kll);
        date = (TextView) findViewById(R.id.list_item_date);
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    protected void initData() {
        generateColumnData();
        int type = getIntent().getIntExtra("type",0);
        if(1 == type){
            info.setText("跑步统计图");
            RunData data = (RunData) getIntent().getSerializableExtra("data");

            dataArr[0] = Float.parseFloat(data.getDistance());
            dataArr[1] = Float.parseFloat(data.getBushu());
            dataArr[2] = Float.parseFloat(data.getTime());
            dataArr[3] = Float.parseFloat(data.getKll());

            distance.setText(String.valueOf("总距离："+data.getDistance())+"米");
            bushu.setText(String.valueOf("总步数："+data.getBushu())+"步");
            time.setText(String.valueOf("总时间："+data.getTime())+"秒");
            kll.setText(String.valueOf("总消耗："+data.getKll())+"焦耳");
            date.setText(String.valueOf("时间："+ DateUtils.getDate(data.getDate())));
        }else if(2 == type){
            info.setText("爬山统计图");
            ClimbData data = (ClimbData) getIntent().getSerializableExtra("data");
            distance.setText(String.valueOf("总距离："+data.getDistance()));
            bushu.setText(String.valueOf("总步数："+data.getBushu()));
//            time.setText(String.valueOf("总时间："+data.getTime()));
//            kll.setText(String.valueOf("总消耗："+data.getKll()));
//            date.setText(String.valueOf("时间："+ DateUtils.getDate(data.getDate())));
        }else {

        }

    }


    private void generateColumnData() {

        int numSubcolumns = 1;
        int numColumns = months.length;

        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        for (int i = 0; i < numColumns; ++i) {
            values = new ArrayList<SubcolumnValue>();
//            for (int j = 0; j < numSubcolumns; ++j) {
//                values.add(new SubcolumnValue((float) Math.random() * 50f + 5, ChartUtils.pickColor()));
//            }
            values.add(new SubcolumnValue((float) Math.random() * 50f + 5, ChartUtils.pickColor()));
            axisValues.add(new AxisValue(i).setLabel(months[i]));
            columns.add(new Column(values).setHasLabelsOnlyForSelected(true));
        }

        columnData = new ColumnChartData(columns);
        columnData.setAxisXBottom(new Axis(axisValues).setHasLines(true));
        columnData.setAxisYLeft(new Axis().setHasLines(true).setMaxLabelChars(2));
        chartBottom.setColumnChartData(columnData);
        chartBottom.setValueSelectionEnabled(true);
        chartBottom.setZoomType(ZoomType.HORIZONTAL);
    }



}
