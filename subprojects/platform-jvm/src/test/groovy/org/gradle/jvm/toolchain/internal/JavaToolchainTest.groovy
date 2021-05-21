/*
 * Copyright 2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.jvm.toolchain.internal

import org.gradle.api.internal.file.TestFiles
import org.gradle.internal.jvm.inspection.JvmInstallationMetadata
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.jvm.toolchain.JvmImplementation
import spock.lang.Specification

class JavaToolchainTest extends Specification {
    def "java version is reported as specified in metadata"() {
        given:
        def javaHome = new File("/jvm/$implementationVersion").absoluteFile
        def metadata = JvmInstallationMetadata.from(javaHome, implementationVersion, "vendor", "implName")
        def compilerFactory = Mock(JavaCompilerFactory)
        def toolFactory = Mock(ToolchainToolFactory)

        when:
        def javaToolchain = new JavaToolchain(metadata, compilerFactory, toolFactory, TestFiles.fileFactory(), Mock(JavaToolchainInput) {
            getLanguageVersion() >> JavaLanguageVersion.of(languageVersion)
            getVendor() >> DefaultJvmVendorSpec.any().toString()
            getImplementation() >> JvmImplementation.VENDOR_SPECIFIC.toString()
        })
        then:
        javaToolchain.javaVersion == implementationVersion
        javaToolchain.languageVersion.asInt() == languageVersion

        where:
        implementationVersion | languageVersion
        "1.8.0_292"           | 8
        "11.0.11"             | 11
        "16"                  | 16
    }
}