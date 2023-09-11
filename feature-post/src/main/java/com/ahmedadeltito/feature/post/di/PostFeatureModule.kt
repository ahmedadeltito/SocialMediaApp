package com.ahmedadeltito.feature.post.di

import com.ahmedadeltito.datasource.local.DatabaseManager
import com.ahmedadeltito.feature.post.apiservice.PostAPIService
import com.ahmedadeltito.feature.post.mapper.PostDataSourceToEntityMapper
import com.ahmedadeltito.feature.post.mapper.PostLocalToDataSourceMapper
import com.ahmedadeltito.feature.post.mapper.PostResponseToDataSourceMapper
import com.ahmedadeltito.feature.post.mapper.PostResponseToLocalMapper
import com.ahmedadeltito.feature.post.repository.PostRepository
import com.ahmedadeltito.feature.post.usecase.GetPostUseCase
import com.ahmedadeltito.feature.post.usecase.GetPostsUseCase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Dagger Module that provides [GetPostsUseCase] and [GetPostUseCase].
 */
@Module
class PostFeatureModule {

    @Provides
    @PostFeatureScope
    fun providesApiService(
        retrofit: Retrofit,
    ): PostAPIService {
        return retrofit.create(PostAPIService::class.java)
    }

    @Provides
    @PostFeatureScope
    fun providesPostResponseToDataSourceMapper(): PostResponseToDataSourceMapper =
        PostResponseToDataSourceMapper()

    @Provides
    @PostFeatureScope
    fun providesPostResponseToLocalMapper(): PostResponseToLocalMapper =
        PostResponseToLocalMapper()

    @Provides
    @PostFeatureScope
    fun providesPostLocalToDataSourceMapper(): PostLocalToDataSourceMapper =
        PostLocalToDataSourceMapper()

    @Provides
    @PostFeatureScope
    fun providesPostDataSourceToEntityMapper(): PostDataSourceToEntityMapper =
        PostDataSourceToEntityMapper()

    @Provides
    @PostFeatureScope
    fun providesRepository(
        local: DatabaseManager,
        remote: PostAPIService,
        postResponseToDataSourceMapper: PostResponseToDataSourceMapper,
        postResponseToLocalMapper: PostResponseToLocalMapper,
        postLocalToDataSourceMapper: PostLocalToDataSourceMapper,
    ): PostRepository = PostRepository(
        local = local.postDao(),
        remote = remote,
        postResponseToDataSourceMapper = postResponseToDataSourceMapper,
        postResponseToLocalMapper = postResponseToLocalMapper,
        postLocalToDataSourceMapper = postLocalToDataSourceMapper
    )

    @Provides
    @PostFeatureScope
    fun provideGetPostsUseCase(
        repository: PostRepository,
        postDataSourceToEntityMapper: PostDataSourceToEntityMapper,
    ): GetPostsUseCase = GetPostsUseCase(
        repository = repository,
        mapper = postDataSourceToEntityMapper,
    )

    @Provides
    @PostFeatureScope
    fun provideGetPostUseCase(
        repository: PostRepository,
        postDataSourceToEntityMapper: PostDataSourceToEntityMapper,
    ): GetPostUseCase = GetPostUseCase(
        repository = repository,
        mapper = postDataSourceToEntityMapper,
    )
}