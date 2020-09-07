package com.example.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface retroAPI {


    //ngrok 경로에 가서 ngrok http 8000 하고 주소 업데이트(Net에도)
    public static final String API_URL = "https://ewhamd5.herokuapp.com/";
    //--------------------------------- B

    //로그인용 GET
    @GET("user/")
    Call<List<User>> user(@Query("id") String id, @Query("pw") String pw);


    //로그아웃용 POST
    @POST("user/")
    Call<Logout> logout(@Body Logout logout, @Header("Authorization") String authorization);

    //회원가입용 POST
    @POST("user/")
    Call<User> createUser(@Body User user);










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


