// TIMERO/app/src/main/java/com/example/timero/api/ApiClient.java
package com.example.timero.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// This class provides a singleton instance of the Retrofit client.
public class ApiClient {

    // IMPORTANT: BASE_URL must point to your C# API.
    // - For Android Emulator: Use "http://10.0.2.2:<port>/" to access your computer's localhost.
    // - For Physical Android Device: Use your computer's actual IP address on your local network
    //   (e.g., "http://192.168.1.100:5219/"). Make sure your firewall allows connections!
    // Replace 5219 with the port your C# API is running on.
    private static final String BASE_URL = "http://192.168.3.221:5219/";

    private static Retrofit retrofit = null;

    // Returns the Retrofit instance. Creates it if it doesn't exist.
    public static Retrofit getClient() {
        if (retrofit == null) {
            // HttpLoggingInterceptor is used to log network requests and responses to Logcat.
            // It's very useful for debugging API calls.
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY); // Set level to BODY to log headers and body

            // OkHttpClient can be customized with interceptors, timeouts, etc.
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging) // Add the logging interceptor
                    .build();

            // Build the Retrofit instance
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL) // Set the base URL for your API
                    .addConverterFactory(GsonConverterFactory.create()) // Use Gson for JSON serialization/deserialization
                    .client(client) // Set the custom OkHttpClient
                    .build();
        }
        return retrofit;
    }
}
    