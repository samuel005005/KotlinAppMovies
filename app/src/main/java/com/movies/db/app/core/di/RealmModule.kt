package com.movies.db.app.core.di

import android.app.Application
import android.content.Context
import com.movies.db.app.core.MoviesConfiguration
import com.movies.db.app.core.modules.MoviesBDModule
import com.movies.db.app.core.modules.MoviesCacheModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RealmModule {
    private const val realmVersion = 3L
    @Provides
    @Singleton
    fun provideEncryptionKey(applicationContext: Application): ByteArray {
        val config = MoviesConfiguration(applicationContext)
        return config.getKey()
    }

    @Provides
    @Singleton
    fun provideRealmConfiguration(encryptionKey: ByteArray): RealmConfiguration {
        return RealmConfiguration.Builder()
            .name("movie.db.realm")
            .schemaVersion(realmVersion)
            .deleteRealmIfMigrationNeeded()
            .encryptionKey(encryptionKey)
            .allowWritesOnUiThread(true)
            .modules(MoviesBDModule(), MoviesCacheModule())
            .build()
    }

    @Provides
    @Singleton
    fun providesRealmDatabase(
        @ApplicationContext context: Context, encryptionKey: ByteArray
    ): Realm {
        Realm.init(context)
        val realmConfiguration = RealmConfiguration.Builder()
            .name("movie.db.realm")
            .schemaVersion(realmVersion)
            .deleteRealmIfMigrationNeeded()
            .encryptionKey(encryptionKey)
            .allowWritesOnUiThread(true)
            .modules(MoviesBDModule(), MoviesCacheModule())
            .build()
        Realm.setDefaultConfiguration(realmConfiguration)
        return Realm.getDefaultInstance()
    }
}