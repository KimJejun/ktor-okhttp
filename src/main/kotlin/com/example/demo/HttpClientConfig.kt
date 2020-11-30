package com.example.demo

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import okhttp3.Dispatcher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit

/**
 *
 * @author 김제준 (reperion.kim@navercorp.com)
 * @since 2020-11-30
 */
@Configuration
class HttpClientConfig {
	@Bean
	fun okhttpClient() = HttpClient(OkHttp) {
		install(JsonFeature) {
			serializer = JacksonSerializer()
		}
		install(UserAgent) {
			agent = "Ktor client/1.3.1(npay-point)"
		}
		engine {
			config {
				dispatcher(Dispatcher().apply {
					maxRequests = 200
					maxRequestsPerHost = 150
				})
				connectTimeout(1000, TimeUnit.MILLISECONDS) // 아파치 connectTimeout에 해당됨
				readTimeout(2500, TimeUnit.MILLISECONDS) // read 타임아웃. 아파치 socketTimeout에 해당됨
				callTimeout(3000, TimeUnit.MILLISECONDS) // 전 과정에 대한 timeout. readTimeout의 경우에는 느리지만 유효한 연결에 대해서는 종료시키지 않기에 설정 필요.
				cache(null)
				retryOnConnectionFailure(true)
			}
		}
	}
}