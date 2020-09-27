package com.example.myapplication;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface retroAPI {


    //ngrok 경로에 가서 ngrok http 8000 하고 주소 업데이트(Net에도)
    public static final String API_URL = "http://13.125.80.169:8000/";
    //--------------------------------- B

    //로그인용 GET
    @GET("user/")
    Call<List<User>> user(@Query("id") String id, @Query("pw") String pw);


    //약물 받아오기
    @GET("drugdata/")
    Call<List<Drug>> drug(@Query("injection") String injection, @Query("num") String num, @Query("component_code") String component_code, @Query("drug_code") String drug_code, @Query("drug_name") String drug_name, @Query("company") String company, @Query("size") String size, @Query("unit") String unit, @Query("price") String price, @Query("sj") String sj);


    //로그아웃용 POST
    @POST("user/")
    Call<Logout> logout(@Body Logout logout, @Header("Authorization") String authorization);

    //회원가입용 POST
    @POST("user/")
    Call<User> createUser(@Body User user);





    @POST("drugdata/")
    Call<Drug> post_posts(@Body Drug drug);

    @PATCH("/drugdata/{pk}/")
    Call<Drug> patch_posts(@Path("pk") int pk, @Body Drug drug);

    @DELETE("/drugdata/{pk}/")
    Call<Drug> delete_posts(@Path("pk") int pk);

    @GET("drugdata/")
    Call<List<Drug>> get_posts();

    @GET("user/")
    Call<List<User>> get_logs();
//
//    @GET("user/")
//    Call<List<User>> regist_logs(@Field("id") String id, @Field("pw") String pw, @Field("birth") String birth, @Field("phone") String phone);


    @POST("user/")
    Call<User> regist_logs(@Body User user);


    @GET("drugdata/{pk}/")
    Call<Drug> get_post_pk(@Path("pk") int pk);














//***이거 밑에도 김소현이 해 본 부분?
//    public interface ApiService {
//        public static final String API_URL = "http://jsonplaceholder.typicode.com/";
//
//        @GET("comments")
//        Call<ResponseBody>getComment(@Query("postId") int postId);
//
//    }


}

//    ////--------------김소현이 해 본 부분----------------------------///
//    //이미지 보내기 메소드
//    public interface ApiService2 {
//        public static final String API_URL = "http://jsonplaceholder.typicode.com/";
//
//        @GET("url뒷부분")
//        Call<ResponseBody>getComment(@Query("postId") ibnt postId);
//        //@GET(“api주소”)
//        //Call<ResponseBody>함수이름(@Query(“변수이름”), 안드로이드에서 보낼 변수)
//
//    }
//


