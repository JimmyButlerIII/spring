/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.aop.support;

import org.springframework.aop.MethodMatcher;

import java.lang.reflect.Method;

/**
 * 它表示不会考虑具体 方法参数。因为不用每次都检查参数，那么对于同样的类型的方法匹配结果，就可以在框架内部缓存以提高性能。
 * 常用实现类AnnotationMethodMatcher
 * Convenient abstract superclass for static method matchers, which don't care
 * about arguments at runtime.
 *
 * @author Rod Johnson
 */
public abstract class StaticMethodMatcher implements MethodMatcher {

	@Override
	public final boolean isRuntime() {
		return false;
	}

	@Override
	public final boolean matches(Method method, Class<?> targetClass, Object... args) {
		// should never be invoked because isRuntime() returns false
		throw new UnsupportedOperationException("Illegal MethodMatcher usage");
	}

}
