import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.*;

import java.io.IOException;
import java.util.List;

public class Client {

    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();

        String url = "http://localhost:8885/courses";
       // String url1 = "http://localhost:8885/courses?title=Java";

      //  HttpUrl.Builder builder=HttpUrl.parse(url1).newBuilder();
      //  builder.addQueryParameter("title","Java");
        ///если передаем параметры


       ///авторизация
        String authData=Credentials.basic("admin","123");



        Request request = new Request.Builder()
                .addHeader("Autorization",authData)
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            public void onFailure(Request request, IOException e) {

            }

            public void onResponse(Response response) throws IOException {
                String responseBody = response.body().string();
                System.out.println( responseBody);

                ObjectMapper objectMapper=new ObjectMapper();
                List<Course> courseList=objectMapper.readValue(responseBody, new TypeReference<List<Course>>(){});
                System.out.println("courseList = " + courseList);
                System.out.println("courseList.get(0).getTitle() = " + courseList.get(0).getTitle());

            }
        });

    }
}
