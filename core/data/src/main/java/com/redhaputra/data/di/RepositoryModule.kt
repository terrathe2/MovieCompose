package com.redhaputra.data.di

import com.redhaputra.data.repositories.DiscoverRepository
import com.redhaputra.data.repositories.GenreRepository
import com.redhaputra.data.repositories.MovieRepository
import com.redhaputra.data.repositories.impl.DiscoverRepositoryImpl
import com.redhaputra.data.repositories.impl.GenreRepositoryImpl
import com.redhaputra.data.repositories.impl.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Class that contributes to the object graph [SingletonComponent].
 *
 * @see Module
 */
@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    /**
     * Create a method binding for [GenreRepository].
     *
     * @return Instance of Genre Repository
     */
    @Binds
    fun bindsGenreRepository(
        genreRepository: GenreRepositoryImpl
    ): GenreRepository

    /**
     * Create a method binding for [MovieRepository].
     *
     * @return Instance of Movie Repository
     */
    @Binds
    fun bindsMovieRepository(
        movieRepository: MovieRepositoryImpl
    ): MovieRepository

    /**
     * Create a method binding for [DiscoverRepository].
     *
     * @return Instance of Discover Repository
     */
    @Binds
    fun bindsDiscoverRepository(
        discoverRepository: DiscoverRepositoryImpl
    ): DiscoverRepository
}