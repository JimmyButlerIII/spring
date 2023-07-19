import org.springframework.core.ResolvableType;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.IntStream;

public class ResolvableTypeTest {

	/**
	 * 测试继承获取泛型
	 * @param
	 */
	class Parent<T, S>{

	}

	interface IParent1<T> {

	}

	interface IParent2<T> {

	}

	public class Children<K extends String, V extends Collection> extends Parent<String, Boolean> implements IParent1<Long>, IParent2<Double> {

	}

	class Person{}

	class Student extends Person{}

	public static void main(String[] args) {
//		System.out.println("------------------------jdk原生方式-获取泛型父类---------------------------------------");
//		// jdk原生方式获取Children类继承的父类的类型
//		Type genericSuperclassType = Children.class.getGenericSuperclass();
//		// com.calebzhao.test.SpringTypeTest$Parent
//		System.out.println("genericSuperclassType-->" + genericSuperclassType);
//
//		if (genericSuperclassType instanceof ParameterizedType) {
//			Type[] actualTypeArguments = ((ParameterizedType) genericSuperclassType)
//					.getActualTypeArguments();
//			for (Type argumentType : actualTypeArguments) {
//				System.out.println("父类ParameterizedType.getActualTypeArguments-->" + argumentType);
//			}
//		}
//
//		System.out.println("------------------------jdk原生方式-获取泛型父接口---------------------------------------");
//
//		// jdk原生方式获取Children类实现的接口的类型
//		Type[] genericInterfacesTypes = Children.class.getGenericInterfaces();
//		// [com.calebzhao.test.SpringTypeTest$IParent1, com.calebzhao.test.SpringTypeTest$IParent2]
//		System.out.println(Arrays.toString(genericInterfacesTypes));
//
//		for (Type interfaceType : genericInterfacesTypes) {
//			if (interfaceType instanceof ParameterizedType) {
//				Type[] actualTypeArguments = ((ParameterizedType) interfaceType)
//						.getActualTypeArguments();
//				for (Type argumentType : actualTypeArguments) {
//					System.out.println("父接口ParameterizedType.getActualTypeArguments:" + argumentType);
//				}
//			}
//		}


		System.out.println("------------------------分割线 spring ResolvableType---------------------------------------");
		ResolvableType childrenResolvableType = ResolvableType.forClass(Children.class);
		System.out.println("children type：" + childrenResolvableType.getType());
		System.out.println("children raw type：" + childrenResolvableType.getRawClass());
		System.out.println("children generics：" + Arrays.toString(childrenResolvableType.getGenerics()));

		System.out.println("-----super ResolvableType-------");
		ResolvableType superResolvableType = childrenResolvableType.getSuperType();
		System.out.println("super generics：" + Arrays.toString(superResolvableType.getGenerics()));
		System.out.println("super type：" + superResolvableType.getType());
		System.out.println("super raw class：" + superResolvableType.getRawClass());
		System.out.println("super getComponentType：" +  superResolvableType.getComponentType());
		System.out.println("super getSource：" +  superResolvableType.getSource());
		System.out.println("super：" + Arrays.toString(superResolvableType.getInterfaces()));

		System.out.println("\n-----interface ResolvableType-------");
		ResolvableType[] interfaceResolvableTypes = childrenResolvableType.getInterfaces();
		IntStream.range(0, interfaceResolvableTypes.length).forEach(index ->{
			ResolvableType interfaceResolvableType = interfaceResolvableTypes[index];

			System.out.println("\n -------第" + index + "个接口------------");
			System.out.println("interface generics：" + Arrays.toString(interfaceResolvableType.getGenerics()));
			System.out.println("interface type：" + interfaceResolvableType.getType());
			System.out.println("interface raw class：" + interfaceResolvableType.getRawClass());
			System.out.println("interface getComponentType：" +  interfaceResolvableType.getComponentType());
			System.out.println("interface getSource：" +  interfaceResolvableType.getSource());
			System.out.println("interface：" + Arrays.toString(interfaceResolvableType.getInterfaces()));
		});
	}
}
