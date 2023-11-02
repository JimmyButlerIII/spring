/*
 * Copyright 2002-2012 the original author or authors.
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

package org.springframework.aop;

/**
 * 切入点
 * 假如我们只增强某个方法，那这些实际被我们增强的方法就称为切入点，真正增强的方法才成为切入点，没有实际增强（可以被增强但是没有增强）的不是
 * 提供了一个TruePointcut实例，当Pointcut为TruePointcut类型时，则会忽略所有的匹配条件，永远返回true
 *
 * Core Spring pointcut abstraction.
 *
 * <p>A pointcut is composed of a {@link ClassFilter} and a {@link MethodMatcher}.
 * Both these basic terms and a Pointcut itself can be combined to build up combinations
 * (e.g. through {@link org.springframework.aop.support.ComposablePointcut}).
 *
 * @author Rod Johnson
 * @see ClassFilter
 * @see MethodMatcher
 * @see org.springframework.aop.support.Pointcuts
 * @see org.springframework.aop.support.ClassFilters
 * @see org.springframework.aop.support.MethodMatchers
 */
public interface Pointcut {

	/**
	 * 类过滤器，可以知道哪些类需要拦截
	 * Return the ClassFilter for this pointcut.
	 * @return the ClassFilter (never {@code null})
	 */
	ClassFilter getClassFilter();

	/**
	 *方法匹配器，可以知道哪些方法需要拦截
	 * Return the MethodMatcher for this pointcut.
	 * @return the MethodMatcher (never {@code null})
	 */
	MethodMatcher getMethodMatcher();


	/**
	 * 提供了一个TruePointcut实例，当Pointcut为TruePointcut类型时，则会忽略所有的匹配条件，永远返回true
	 * Canonical Pointcut instance that always matches.
	 */
	Pointcut TRUE = TruePointcut.INSTANCE;

}
