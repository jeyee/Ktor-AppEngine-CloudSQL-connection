/*
 * Copyright 2018 Google LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.demo

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.html.*
import io.ktor.routing.*
import kotlinx.html.*

fun Application.main() {
    install(DefaultHeaders)
    install(CallLogging)

    routing {
        get("/") {

            val databaseHelper = DatabaseHelper()
            databaseHelper.runQuery("SELECT id, name from db_name.table_name where id = 2;")

            call.respondHtml {
                head {
                    title { +"Ktor on Google App Engine Standard" }
                }
                body {
                    p {
                        +"Hello there! This is Ktor running on Google Appengine Standard"
                    }
                }
            }
        }
    }
}
