/**
 * Copyright 2016 SmartBear Software
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package model.annotations;

import java.lang.annotation.*;

/**
 * Marks a class as a Swagger resource.
 * <p>
 * By default, Swagger-Core will only include and introspect only classes that are annotated
 * with {@code @Api} and will ignore other resources (JAX-RS endpoints, Servlets and
 * so on).
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Api {
    /**
     * Implicitly sets a tag for the operations, legacy support (read description).
     * <p>
     * In swagger-core 1.3.X, this was used as the 'path' that is to host the API Declaration of the
     * resource. This is no longer relevant in swagger-core 1.5.X.
     * <p>
     * If {@link #tags()} is <i>not</i> used, this value will be used to set the tag for the operations described by this
     * resource. Otherwise, the value will be ignored.
     * <p>
     * The leading / (if exists) will be removed.
     *
     * @return tag name for operations under this resource, unless {@link #tags()} is defined.
     */
    String value() default "";

    /**
     * A list of tags for API documentation control.
     * Tags can be used for logical grouping of operations by resources or any other qualifier.
     * <p>
     * A non-empty value will override the value provided in {@link #value()}.
     *
     * @return a string array of tag values
     * @since 1.5.2-M1
     */
    String[] tags() default "";


}
