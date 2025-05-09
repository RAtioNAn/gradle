/*
 * Copyright 2023 the original author or authors.
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

package org.gradle.buildinit.plugins.internal;

import org.jspecify.annotations.NullMarked;

@NullMarked
public enum BuildInitComments {
    /**
     * Comments are included for generated source files and build files.
     * <p>
     * Comments include explanations of various build script constructs,
     * helpful links and disclaimers about files being generated by an 'init' task.
     */
    ON,

    /**
     * Comments are not included for the source files and build files.
     * <p>
     * Comments in less frequently accessed files are preserved,
     * e.g. in the {@code .gitignore} and {@code .gitattributes}.
     */
    OFF,

    /**
     * Special mode, currently required by our `SamplesGenerator`,
     * in which instead of comments, AsciiDoc numeric references are generated ({@code <1>, <2>}).
     */
    EXTERNAL
}
