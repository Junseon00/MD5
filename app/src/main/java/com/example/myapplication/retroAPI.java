package com.example.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import okhttp3.ResponseBody;

public interface retroAPI {


    //ngrok1
    public static final String API_URL = "http://32b7487bcaa5.ngrok.io/";
    //--------------------------------- B

    //통신 담당 메소드 구현.
    @GET("user")
    Call<List<User>> user(@Query("id") String id, @Query("pw") String pass);


    ////--------------김소현이 해 본 부분----------------------------///
    //이미지 보내기 메소드
    public interface ApiService2 {
        public static final String API_URL = "http://jsonplaceholder.typicode.com/";

        @GET("url뒷부분")
        Call<ResponseBody>getComment(@Query("postId") int postId);
        //@GET(“api주소”)
        //Call<ResponseBody>함수이름(@Query(“변수이름”), 안드로이드에서 보낼 변수)

    }

    public interface ApiService {
        public static final String API_URL = "http://jsonplaceholder.typicode.com/";

        @GET("comments")
        Call<ResponseBody>getComment(@Query("postId") int postId);

    }

}
