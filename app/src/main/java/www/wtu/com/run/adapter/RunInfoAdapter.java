package www.wtu.com.run.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import www.wtu.com.run.R;
import www.wtu.com.run.beans.RunInfo;

/**
 * Created by Administrator on 2017/4/20.
 */

public class RunInfoAdapter extends BaseAdapter {
    private List<RunInfo> items;
    private final Context context;

    public RunInfoAdapter(Context context, List<RunInfo> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public String getItem(int position) {
        return items.get(position).getTilte();
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
                    R.layout.item_run_info, null);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.list_item_title);
            holder.content = (TextView) convertView.findViewById(R.id.list_item_content);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title.setText(items.get(position).getTilte());
        holder.content.setText(items.get(position).getContent());
        return convertView;
    }

    private static class ViewHolder {
        private TextView title;
        private TextView content;
    }
}
