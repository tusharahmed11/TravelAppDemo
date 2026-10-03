package com.example.data.di


import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.data.datasource.CacheDataSource
import com.example.data.datasource.DummyDataSource
import com.example.data.datasource.RemoteDataSource
import com.example.data.repository.CacheRepositoryImpl
import com.example.data.repository.ListingRepositoryImpl
import com.example.data.repository.UserRepositoryImp
import com.example.domain.repository.CacheRepository
import com.example.domain.repository.ListingRepository
import com.example.domain.repository.UserRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import kotlin.coroutines.EmptyCoroutineContext.get

val dataModule = module {
    single { DummyDataSource() }

    single<HttpClient> {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    prettyPrint = true
                })
            }
            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        println("KtorHttp: $message")
                    }
                }
            }
            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
        }
    }

    single { RemoteDataSource(
        get <HttpClient>(), baseUrl = get<String>()
    ) }

    single { CacheDataSource(dataStore = get<DataStore<Preferences>>()) }

    single<ListingRepository> {
        ListingRepositoryImpl(
            get<RemoteDataSource>()
        )
    }

    single<UserRepository> {
        UserRepositoryImp(
            get<RemoteDataSource>(),
            cacheDataSource = get<CacheDataSource>()
        )
    }
    single<CacheRepository> {
        CacheRepositoryImpl(get<CacheDataSource>())
    }
}