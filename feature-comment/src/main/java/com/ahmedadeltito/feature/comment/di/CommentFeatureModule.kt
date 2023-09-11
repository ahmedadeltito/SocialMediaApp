package com.ahmedadeltito.feature.comment.di

import com.ahmedadeltito.datasource.local.DatabaseManager
import com.ahmedadeltito.feature.comment.apiservice.CommentAPIService
import com.ahmedadeltito.feature.comment.mapper.CommentDataSourceToEntityMapper
import com.ahmedadeltito.feature.comment.mapper.CommentLocalToDataSourceMapper
import com.ahmedadeltito.feature.comment.mapper.CommentResponseToDataSourceMapper
import com.ahmedadeltito.feature.comment.mapper.CommentResponseToLocalMapper
import com.ahmedadeltito.feature.comment.repository.CommentRepository
import com.ahmedadeltito.feature.comment.usecase.GetCommentsUseCase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Dagger Module that provides [GetCommentsUseCase].
 */
@Module
class CommentFeatureModule {

    @Provides
    @CommentFeatureScope
    fun providesApiService(
        retrofit: Retrofit,
    ): CommentAPIService {
        return retrofit.create(CommentAPIService::class.java)
    }

    @Provides
    @CommentFeatureScope
    fun providesCommentResponseToDataSourceMapper(): CommentResponseToDataSourceMapper =
        CommentResponseToDataSourceMapper()

    @Provides
    @CommentFeatureScope
    fun providesCommentResponseToLocalMapper(): CommentResponseToLocalMapper =
        CommentResponseToLocalMapper()

    @Provides
    @CommentFeatureScope
    fun providesCommentLocalToDataSourceMapper(): CommentLocalToDataSourceMapper =
        CommentLocalToDataSourceMapper()

    @Provides
    @CommentFeatureScope
    fun providesCommentDataSourceToEntityMapper(): CommentDataSourceToEntityMapper =
        CommentDataSourceToEntityMapper()

    @Provides
    @CommentFeatureScope
    fun providesRepository(
        local: DatabaseManager,
        remote: CommentAPIService,
        commentResponseToDataSourceMapper: CommentResponseToDataSourceMapper,
        commentResponseToLocalMapper: CommentResponseToLocalMapper,
        commentLocalToDataSourceMapper: CommentLocalToDataSourceMapper,
    ): CommentRepository = CommentRepository(
        local = local.commentDao(),
        remote = remote,
        commentResponseToDataSourceMapper = commentResponseToDataSourceMapper,
        commentResponseToLocalMapper = commentResponseToLocalMapper,
        commentLocalToDataSourceMapper = commentLocalToDataSourceMapper
    )

    @Provides
    @CommentFeatureScope
    fun provideGetCommentsUseCase(
        repository: CommentRepository,
        commentDataSourceToEntityMapper: CommentDataSourceToEntityMapper,
    ): GetCommentsUseCase = GetCommentsUseCase(
        repository = repository,
        mapper = commentDataSourceToEntityMapper,
    )
}