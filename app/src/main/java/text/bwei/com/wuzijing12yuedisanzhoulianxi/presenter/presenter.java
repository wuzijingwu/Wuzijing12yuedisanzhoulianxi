package text.bwei.com.wuzijing12yuedisanzhoulianxi.presenter;

import java.util.List;

import text.bwei.com.wuzijing12yuedisanzhoulianxi.bean.Bean;
import text.bwei.com.wuzijing12yuedisanzhoulianxi.model.Imodel;
import text.bwei.com.wuzijing12yuedisanzhoulianxi.model.Onselect;
import text.bwei.com.wuzijing12yuedisanzhoulianxi.model.model;
import text.bwei.com.wuzijing12yuedisanzhoulianxi.view.Iview;

/**
 * Created by dell on 2017/12/9.
 */

public class presenter {
    Imodel imodel;
    Iview iview;

    public presenter(Iview iview) {
        this.iview = iview;
        imodel = new model();
    }

    public void getOk(String url, String catalogId, int pnum) {
        imodel.RequestSuccess(url, catalogId, pnum, new Onselect() {
            @Override
            public void dataSuccess(List<Bean.RetBean.ListBean> list) {
                iview.showSuccess(list);
            }
        });


    }

}
