package com.redhaputra.network.di

import com.redhaputra.network.api.GenreService
import com.redhaputra.network.api.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Class that contributes services API.
 *
 * @see Module
 */
@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
object ApiModule {
    /**
     * Create a provider method binding for [MovieService].
     *
     * @return Instance of Movie service.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideMovieService(
        @Named("authClient") client: OkHttpClient,
        retrofitBuilder: Retrofit.Builder
    ): MovieService = retrofitBuilder
        .client(client)
        .build()
        .create(MovieService::class.java)

    /**
     * Create a provider method binding for [GenreService].
     *
     * @return Instance of Genre service.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideGenreService(
        @Named("authClient") client: OkHttpClient,
        retrofitBuilder: Retrofit.Builder
    ): GenreService = retrofitBuilder
        .client(client)
        .build()
        .create(GenreService::class.java)
}