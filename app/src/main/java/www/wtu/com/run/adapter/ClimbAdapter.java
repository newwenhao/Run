package www.wtu.com.run.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import www.wtu.com.run.beans.ClimbData;
import www.wtu.com.run.R;
import www.wtu.com.run.utils.DateUtils;

/**
 * Created by Administrator on 2017/4/20.
 */

public class ClimbAdapter extends BaseAdapter {
    private List<ClimbData> items;
    private final Context context;

    public ClimbAdapter(Context context, List<ClimbData> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public String getItem(int position) {
        return items.get(position).getUsername();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.list_item_card, null);

            holder = new ViewHolder();
            holder.distance = (TextView) convertView.findViewById(R.id.list_item_distance);
            holder.bushu = (TextView) convertView.findViewById(R.id.list_item_bs);
            holder.time = (TextView) convertView.findViewById(R.id.list_item_time);
            holder.kll = (TextView) convertView.findViewById(R.id.list_item_kll);
            holder.date = (TextView) convertView.findViewById(R.id.list_item_date);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.distance.setText(items.get(position).getDistance());
        holder.bushu.setText(items.get(position).getBushu());
        holder.time.setText(items.get(position).getTime());
        holder.kll.setText(items.get(position).getKll());
        holder.date.setText(DateUtils.getDate(items.get(position).getDate()));

        return convertView;
    }

    private static class ViewHolder {
        private TextView distance;
        private TextView bushu;
        private TextView time;
        private TextView kll;
        private TextView date;
    }
}
