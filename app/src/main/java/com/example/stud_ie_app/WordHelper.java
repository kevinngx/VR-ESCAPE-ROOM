package com.example.stud_ie_app;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WordHelper {

    public static final String BASE_URL = "https://wordsapiv1.p.rapidapi.com/words/";

    public static WordResult getWord() throws ExecutionException, InterruptedException {
        GetWordTask getWordTask = new GetWordTask();
        return getWordTask.execute().get();
    }

    private static class GetWordTask extends AsyncTask<Void, Void, WordResult> {

        @Override
        protected WordResult doInBackground(Void... voids) {

            WordResult result = null;

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            WordsApiClient service = retrofit.create(WordsApiClient.class);
            Call<WordResult> call = service.getRandomWord();

            try {
                result = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(WordResult wordResult) {
            System.out.println("In post execute");
            System.out.printf(wordResult.toString());
        }
    }

}
