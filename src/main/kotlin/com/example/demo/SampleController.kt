package com.example.demo

import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 *
 * @author 김제준 (reperion.kim@navercorp.com)
 * @since 2020-11-30
 */
@RestController
class SampleController(
	val okhttpClient: HttpClient
) {

	@GetMapping("call")
	fun call() : String {
		return runBlocking {
			okhttpClient.get<String>("https://www.google.com")
		}
	}
}