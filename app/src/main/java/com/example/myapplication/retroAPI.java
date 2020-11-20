package com.example.myapplication;

import android.util.Log;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface retroAPI {


    //ngrok 경로에 가서 ngrok http 8000 하고 주소 업데이트(Net에도)

    public static final String API_URL = "https://73b8ab351527.ngrok.io/";
    //--------------------------------- B

    //로그인용 GET
    @GET("user/")
    Call<List<User>> user(@Query("id") String id, @Query("pw") String pw);

    //팔로잉/팔로우 서치용 GET
    @GET("user/")
    Call<List<User>> get_users();


    //약물 받아오기
    @GET("drugdata/")
    Call<List<Drug>> drug(@Query("injection") String injection, @Query("num") String num, @Query("component_code") String component_code, @Query("drug_code") String drug_code, @Query("drug_name") String drug_name, @Query("company") String company, @Query("size") String size, @Query("unit") String unit, @Query("price") String price, @Query("sj") String sj);


    //로그아웃용 POST
    @POST("user/")
    Call<Logout> logout(@Body Logout logout, @Header("Authorization") String authorization);

    //회원가입용 POST
    @POST("user/")
    Call<User> createUser(@Body User user);

    //이미지 업로드용 POST
    @POST("image/")
    Call<ImageType> postImage(@Body ImageType image);


    @Multipart
    @POST("image/")
    Call<ImageType> postImg(@Part("id") int id,
                            @Part MultipartBody.Part Image,
                            @Part("user_id") String uid

    );

    @GET("prescription/")
    Call<List<Prescription>> get_pres();


    //getPres(@Query("idi") int idi, @Query("user_id") String user_id, @Query("drug_name") String drug_name, @Query("dose_size") String dose_size, @Query("dose_num") String dose_num, @Query("dose_day") String dose_day);



    @POST("drugdata/")
    Call<Drug> post_posts(@Body Drug drug);

    @PATCH("/drugdata/{pk}/")
    Call<Drug> patch_posts(@Path("pk") int pk, @Body Drug drug);

    @DELETE("/drugdata/{pk}/")
    Call<Drug> delete_posts(@Path("pk") int pk);

    //약물 정보 받아오기
    @GET("drugdata/")
    Call<List<Drug>> get_posts();

    //로그인
    @GET("user/")
    Call<List<User>> get_logs();


//
//    @GET("user/")
//    Call<List<User>> regist_logs(@Field("id") String id, @Field("pw") String pw, @Field("birth") String birth, @Field("phone") String phone);

    //회원가입
    @POST("user/")
    Call<User> regist_logs(@Body User user);

    //아래로는 정보바꾸기

    //1. 비밀번호 바꾸기
    @PUT("user/{id}")
    Call<User> change_pw(@Path("id") String id, @Body User user);

    //2. 생일? 바꿀까?
    //@PUT("user/{birth}")
    //Call<User> change_email(@Path("email") String email, @Body User user);

    //3. 전화 번호 바꾸기
    @PUT("user/{id}")
    Call<User> change_phone(@Path("id") String id, @Body User user);


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


