package com.movies.db.shared.infrastructure.di

import android.app.Application
import android.content.Context
import com.movies.db.shared.infrastructure.core.MoviesConfiguration
import com.movies.db.shared.infrastructure.core.modules.MoviesBDModule
import com.movies.db.shared.infrastructure.core.modules.MoviesCacheModule
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
            .name("corelibrary.bpd.realm")
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
            .name("corelibrary.bpd.realm")
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