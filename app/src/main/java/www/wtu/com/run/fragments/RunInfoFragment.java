package www.wtu.com.run.fragments;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import www.wtu.com.run.R;
import www.wtu.com.run.adapter.RunInfoAdapter;
import www.wtu.com.run.beans.RunInfo;

/**
 * Created by Administrator on 2017/4/20.
 * 知识库主界面
 */

public class RunInfoFragment extends BaseFragment {


    private ListView lv;
    private List<RunInfo> items = new ArrayList<>();


    @Override
    protected int returnLayoutRes() {
        return R.layout.layout_fragment_run_info;
    }

    @Override
    protected void initComp(View root) {
        lv = (ListView) root.findViewById(R.id.lv);
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    protected void initData() {
        lv.setAdapter(createAdapter());
        lv.setOnItemClickListener(new ListItemClickListener());
    }

    @Override
    public void loadData(String[] params) {

    }


    private RunInfoAdapter createAdapter() {
        RunInfo data ;

        data = new RunInfo(new Long(1L),"运动后肌肉酸痛该怎么缓解 五种方法简单好用","我们于剧烈运动后，都会感到疲倦或肌肉酸痛，这现象称之为“迟发性肌肉酸痛”。它通常于剧烈运动后的一至两天内出现，而持续时间通常少于两天。这现象是由于肌肉纤维轻微撕裂而导致发炎所致。那应该怎么缓解……","http://www.cnys.com/jianshen/69440.html");
        items.add(data);

        data = new RunInfo(null,"练习瑜伽应有的好习惯 这些你有吗","瑜伽练习久了，自我的修养也会渐渐地提高，和一般的人不一样。习练瑜伽的人会将习惯融入生活，这些好习惯，将使你变成一个拥有非凡魅力……","http://www.cnys.com/yujia/69439.html");
        items.add(data);

        data = new RunInfo(null,"有氧运动的七大误区 快回头,别走错路了","有氧锻炼真的能达到燃烧脂肪的效果吗？对于不同的健身爱好者来说，有各种不同类型的有氧锻炼形式适合他们。如果你想通过有氧运动达到减轻体重的效果，你到底需要进行多长时间的锻炼才能见效呢？什么样的错误观念会削弱有氧锻炼的积极效果呢？以下是有关有氧健身运动认识的常见误区……","http://www.cnys.com/jianshen/69401.html");
        items.add(data);

        data = new RunInfo(null,"运动后怎样消除体力疲劳 从饮食习惯做起","生活中，锻炼后出现一定的疲劳，是极正常的现象。这种情况下，只要借助各种有效的手段和方法，及时消除疲劳，补充消耗的能量，就可以使体能很快地得到恢复，有助于身体健康和运动水平的不断提高。那么，如何及时消除运动后的疲劳……","http://www.cnys.com/jianshen/69388.html");
        items.add(data);

        data = new RunInfo(null,"减肥需分清体质 不然也百搭","很多胖纸每天都处在与全身肉肉的抗战中，可是明明自己已经很努力了，全身的肉肉为嘛就这么固执呢?想想减肥真的是好难额?减不下去?那是因为你还木有了解自己的肥胖类型……","http://www.cnys.com/jianshen/69367.html");
        items.add(data);

        data = new RunInfo(null,"夏天来了,如何正确有效减掉全身赘肉","肉肉的小肚腩是不是让你很苦恼呢?有它在，穿什么都不好看怎么别人的A4腰就那么美呢?小肚子不平，何以平天下!想要瘦腰瘦肚子赶紧看这里……","http://www.cnys.com/jianshen/69305.html");
        items.add(data);

        data = new RunInfo(null,"没有写服务器程序","后面的数据自己添加","http://www.voofchat.com");
        items.add(data);

        return new RunInfoAdapter(getActivity(), items);
    }



    private final class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri content_url = Uri.parse(items.get(position).getUrl());
            intent.setData(content_url);
            startActivity(intent);
        }
    }
}
