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

package org.springframework.aop.framework;

import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * 代理配置类 用来确保所有的代理创建者都有部分统一的基础属性
 *
 * Convenience superclass for configuration used in creating proxies,
 * to ensure that all proxy creators have consistent properties.
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @see AdvisedSupport
 */
public class ProxyConfig implements Serializable {

	/** use serialVersionUID from Spring 1.2 for interoperability. */
	private static final long serialVersionUID = -8409359707199703185L;

	// AOP中是否直接代理目标类，将其设置为 "true"：当将其设置为 "true" 时，会强制代理目标对象的类。如果目标类是一个接口，将会创建一个基于JDK的代理对象。如果目标类是其他类型的类，则将会创建一个基于CGLIB的代理对象。
	private boolean proxyTargetClass = false;

	// 标记是否对代理进行优化。true：那么在生成代理对象之后，如果对代理配置进行了修改，已经创建的代理对象也不会获取修改之后的代理配置。
	// 如果exposeProxy设置为true，即使optimize为true也会被忽略。
	private boolean optimize = false;

	// 标记是否需要阻止通过该配置创建的代理对象转换为Advised类型，默认值为false，表示代理对象可以被转换为Advised类型
	// Advised接口其实就代表了被代理的对象（此接口是Spring AOP提供，它提供了方法可以对代理进行操作，比如移除一个切面之类的），它持有了代理对象的一些属性，通过它可以对生成的代理对象的一些属性进行人为干预
	// 默认情况，我们可以这么完 Advised target = (Advised) context.getBean("opaqueTest"); 从而就可以对该代理持有的一些属性进行干预勒   若此值为true，就不能这么玩了
	boolean opaque = false;

	//标记代理对象是否应该被aop框架通过AopContext以ThreadLocal的形式暴露出去。
	//当一个代理对象需要调用它【自己】的另外一个代理方法时，这个属性将非常有用。默认是是false，以避免不必要的拦截。
	boolean exposeProxy = false;

	//标记是否需要冻结代理对象，即在代理对象生成之后，是否允许对其进行修改，默认为false.
	//当我们不希望调用方修改转换成Advised对象之后的代理对象时，就可以设置为true 给冻结上即可
	private boolean frozen = false;


	/**
	 * Set whether to proxy the target class directly, instead of just proxying
	 * specific interfaces. Default is "false".
	 * <p>Set this to "true" to force proxying for the TargetSource's exposed
	 * target class. If that target class is an interface, a JDK proxy will be
	 * created for the given interface. If that target class is any other class,
	 * a CGLIB proxy will be created for the given class.
	 * <p>Note: Depending on the configuration of the concrete proxy factory,
	 * the proxy-target-class behavior will also be applied if no interfaces
	 * have been specified (and no interface autodetection is activated).
	 * @see org.springframework.aop.TargetSource#getTargetClass()
	 */
	public void setProxyTargetClass(boolean proxyTargetClass) {
		this.proxyTargetClass = proxyTargetClass;
	}

	/**
	 * Return whether to proxy the target class directly as well as any interfaces.
	 */
	public boolean isProxyTargetClass() {
		return this.proxyTargetClass;
	}

	/**
	 * Set whether proxies should perform aggressive optimizations.
	 * The exact meaning of "aggressive optimizations" will differ
	 * between proxies, but there is usually some tradeoff.
	 * Default is "false".
	 * <p>For example, optimization will usually mean that advice changes won't
	 * take effect after a proxy has been created. For this reason, optimization
	 * is disabled by default. An optimize value of "true" may be ignored
	 * if other settings preclude optimization: for example, if "exposeProxy"
	 * is set to "true" and that's not compatible with the optimization.
	 */
	public void setOptimize(boolean optimize) {
		this.optimize = optimize;
	}

	/**
	 * Return whether proxies should perform aggressive optimizations.
	 */
	public boolean isOptimize() {
		return this.optimize;
	}

	/**
	 * Set whether proxies created by this configuration should be prevented
	 * from being cast to {@link Advised} to query proxy status.
	 * <p>Default is "false", meaning that any AOP proxy can be cast to
	 * {@link Advised}.
	 */
	public void setOpaque(boolean opaque) {
		this.opaque = opaque;
	}

	/**
	 * Return whether proxies created by this configuration should be
	 * prevented from being cast to {@link Advised}.
	 */
	public boolean isOpaque() {
		return this.opaque;
	}

	/**
	 * Set whether the proxy should be exposed by the AOP framework as a
	 * ThreadLocal for retrieval via the AopContext class. This is useful
	 * if an advised object needs to call another advised method on itself.
	 * (If it uses {@code this}, the invocation will not be advised).
	 * <p>Default is "false", in order to avoid unnecessary extra interception.
	 * This means that no guarantees are provided that AopContext access will
	 * work consistently within any method of the advised object.
	 */
	public void setExposeProxy(boolean exposeProxy) {
		this.exposeProxy = exposeProxy;
	}

	/**
	 * Return whether the AOP proxy will expose the AOP proxy for
	 * each invocation.
	 */
	public boolean isExposeProxy() {
		return this.exposeProxy;
	}

	/**
	 * Set whether this config should be frozen.
	 * <p>When a config is frozen, no advice changes can be made. This is
	 * useful for optimization, and useful when we don't want callers to
	 * be able to manipulate configuration after casting to Advised.
	 */
	public void setFrozen(boolean frozen) {
		this.frozen = frozen;
	}

	/**
	 * Return whether the config is frozen, and no advice changes can be made.
	 */
	public boolean isFrozen() {
		return this.frozen;
	}


	/**
	 * Copy configuration from the other config object.
	 * @param other object to copy configuration from
	 */
	public void copyFrom(ProxyConfig other) {
		Assert.notNull(other, "Other ProxyConfig object must not be null");
		this.proxyTargetClass = other.proxyTargetClass;
		this.optimize = other.optimize;
		this.exposeProxy = other.exposeProxy;
		this.frozen = other.frozen;
		this.opaque = other.opaque;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("proxyTargetClass=").append(this.proxyTargetClass).append("; ");
		sb.append("optimize=").append(this.optimize).append("; ");
		sb.append("opaque=").append(this.opaque).append("; ");
		sb.append("exposeProxy=").append(this.exposeProxy).append("; ");
		sb.append("frozen=").append(this.frozen);
		return sb.toString();
	}

}
