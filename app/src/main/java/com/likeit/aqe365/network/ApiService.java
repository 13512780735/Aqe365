package com.likeit.aqe365.network;

import com.likeit.aqe365.network.model.BaseResponse;
import com.likeit.aqe365.network.model.EmptyEntity;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by admin on 2018/5/11.
 */

public interface ApiService {
    //用户注册接口
    @FormUrlEncoded
    @POST("?service=User.Register")
    Observable<BaseResponse<EmptyEntity>> User_Register(@Field("mobile") String mobile,
                                                        @Field("password") String password,
                                                        @Field("code") String code,
                                                        @Field("clientid") String clientid,
                                                        @Field("invite") String invite
    );
}
