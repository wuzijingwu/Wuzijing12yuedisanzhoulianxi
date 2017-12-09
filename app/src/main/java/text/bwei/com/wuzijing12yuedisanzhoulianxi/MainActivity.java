package text.bwei.com.wuzijing12yuedisanzhoulianxi;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;
import java.util.Random;

import text.bwei.com.wuzijing12yuedisanzhoulianxi.api.Api;
import text.bwei.com.wuzijing12yuedisanzhoulianxi.bean.Bean;
import text.bwei.com.wuzijing12yuedisanzhoulianxi.presenter.presenter;
import text.bwei.com.wuzijing12yuedisanzhoulianxi.view.Iview;
import text.bwei.com.wuzijing12yuedisanzhoulianxi.view.ZoomOutPageTransformer;

public class MainActivity extends AppCompatActivity implements Iview {

    private ViewPager viewPager;
    private Button button;
    private text.bwei.com.wuzijing12yuedisanzhoulianxi.presenter.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        button = (Button) findViewById(R.id.buton);

        presenter = new presenter(this);
        presenter.getOk(Api.URL, Api.catalogId, 1);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.getOk(Api.URL, Api.catalogId, getpage());
            }
        });

    }


    @Override
    public void showSuccess(final List<Bean.RetBean.ListBean> list) {
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                Bean.RetBean.ListBean lb = list.get(position);
                View v = View.inflate(MainActivity.this, R.layout.item, null);
                ImageView img = (ImageView) v.findViewById(R.id.img);
                TextView title = (TextView) v.findViewById(R.id.tv_title);
                TextView tvt = (TextView) v.findViewById(R.id.tv_text);
                title.setText(lb.getTitle());
                tvt.setText(lb.getDescription());
                ImageLoader.getInstance().displayImage(lb.getPic(), img);
                container.addView(v);
                return v;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }


        });


    }

    private int getpage() {
        int max = 108;
        int min = 1;
        Random random = new Random();

        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }


}
