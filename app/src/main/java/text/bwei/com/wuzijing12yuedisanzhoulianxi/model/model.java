package text.bwei.com.wuzijing12yuedisanzhoulianxi.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import text.bwei.com.wuzijing12yuedisanzhoulianxi.api.ApiService;
import text.bwei.com.wuzijing12yuedisanzhoulianxi.bean.Bean;

/**
 * Created by dell on 2017/12/9.
 */

public class model implements Imodel {


    @Override
    public void RequestSuccess(String url, String catalogId, int pnum, final Onselect onselect) {

        final Map<String, String> map = new HashMap<>();
        map.put("catalogId", catalogId);
        map.put("pnum", pnum + "");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);

        Observable<Bean> dates = apiService.getDates("columns/getVideoList.do", map);
        dates.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Bean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Bean bean) {
                        List<Bean.RetBean.ListBean> list = bean.getRet().getList();
                        onselect.dataSuccess(list);
                    }
                });


    }
}
