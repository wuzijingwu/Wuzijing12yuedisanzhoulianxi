package text.bwei.com.wuzijing12yuedisanzhoulianxi.api;

import java.util.Map;

import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;
import text.bwei.com.wuzijing12yuedisanzhoulianxi.bean.Bean;

/**
 * Created by dell on 2017/12/9.
 */

public interface ApiService {
    @POST
    Observable<Bean> getDates(@Url String url, @QueryMap Map<String,String> map);
}
