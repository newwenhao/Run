package www.wtu.com.run.utils;

/**
 * Created by Administrator on 2017/4/20.
 */

public class Formulary {

    public static int getBuChang(String height,String weight){
        int h = Integer.parseInt(height);
        int w = Integer.parseInt(weight);

        int bc = (int)(h * 0.35 + w * 0.2); //自己随便的出来的步长公式

        return bc;
    }


    public static int getBuChang(int height,int weight){
        int bc = (int)(height * 0.35 + weight * 0.2); //自己随便的出来的步长公式

        return bc;
    }
}
