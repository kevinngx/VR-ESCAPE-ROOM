package com.example.stud_ie_app;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SentenceHelper {

    public static final String BASE_URL = "https://od-api.oxforddictionaries.com/api/v1/";

    public static OxfordDictionaryApi getSentence(String word) throws ExecutionException, InterruptedException {
        SentenceHelper.GetSentenceTask getSentenceTask = new SentenceHelper.GetSentenceTask();
        return getSentenceTask.execute(word).get();
    }

    private static class GetSentenceTask extends AsyncTask<String, Void, OxfordDictionaryApi> {


        @Override
        protected OxfordDictionaryApi doInBackground(String... strings) {
            OxfordDictionaryApi result = null;

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            OxfordDictionaryClient service = retrofit.create(OxfordDictionaryClient.class);
            Call<OxfordDictionaryApi> call = service.getSentence(strings[0]);

            try {
                result = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(OxfordDictionaryApi oxfordDictionaryApi) {
            System.out.println("In post execute");
        }
    }

}
