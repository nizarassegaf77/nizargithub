package nizar.id.github.data.module

import android.content.Context
import dagger.Module
import dagger.Provides
import nizar.id.github.data.ApiRoute
import nizar.id.github.utils.*
import okhttp3.Cache
import okhttp3.ConnectionPool
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 *
 * Created by Nizar Assegaf on 3/2/2019.
 *
 */

@Module
class NetworkModule(private val context: Context){


    @Provides
    @Singleton
    protected fun providesApi(cache: Cache,
                              connectionPool: ConnectionPool): ApiRoute {

        fun getInterceptor(): Interceptor {
            return Interceptor { chain ->
                val request = chain.request()
                val builder = request.newBuilder()

                builder.addHeader("Content-Type", "application/json")
                chain.proceed(request)
            }
        }

        fun getOkHttpClient(cache: Cache,
                            connectionPool: ConnectionPool): OkHttpClient {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            return OkHttpClient.Builder()
                    .cache(cache)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .addInterceptor(getInterceptor())
                    .addInterceptor(httpLoggingInterceptor)
                    .connectionPool(connectionPool)
                    .build()
        }

        @Configs val baseUrl = Config.BASE_URL
        val retrofit = Retrofit.Builder()
                .client(getOkHttpClient(cache, connectionPool))
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create(clazz<ApiRoute>())
    }

    @Provides
    @Singleton
    protected fun providesCache(): Cache {
        @Networks val cacheSize = Network.CACHE_SIZE
        return Cache(context.externalCacheDir!!, cacheSize)
    }

    @Provides
    @Singleton
    protected fun providesConnectionPool(): ConnectionPool {
        @Networks val maxIdleConnections = Network.MAX_IDLE_CONNECTIONS.toInt()
        @Networks val keepAliveDurations = Network.KEEP_ALIVE_DURATION

        return ConnectionPool(maxIdleConnections, keepAliveDurations, TimeUnit.SECONDS)
    }
}