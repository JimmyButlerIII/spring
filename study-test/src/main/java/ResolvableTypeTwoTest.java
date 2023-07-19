import org.springframework.core.ResolvableType;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class ResolvableTypeTwoTest {

	// 父类
	class Parent<T, S>{

	}

	// 父接口1
	interface IParent1<T> {

	}

	// 父接口2
	interface IParent2<T> {

	}

	// 子类， 继承父类Parent， 实现接口IParent1，IParent2
	public class Children<K extends String, V extends Collection>
			extends Parent<String, Boolean>
			implements IParent1<Long>, IParent2<Double> {


	}

	private List<Integer> listString;

	private List<List<Parent>> listLists;

	private Map maps;

	private Parent parent;



	public Map<String, String> getMaps() {
		return maps;
	}

	/**
	 *  private Parent parent;
	 *
	 */
	public static void  doTestFindParent(){
		System.out.println("-----------private Parent parent;字段----------------\n");
		// 通过反射找到   private Parent parent;字段
		Field parentField = ReflectionUtils.findField(ResolvableTypeTwoTest.class,"parent");

		// 获取parent字段的ResolvableType
		ResolvableType parentResolvableType = ResolvableType.forField(parentField);
		System.out.println("parent属性的类型："+parentResolvableType.getType());

		//获取第0个位置的参数泛型
		ResolvableType[] generics = parentResolvableType.getGenerics();
		IntStream.range(0, generics.length).forEach(index -> {
			ResolvableType resolvableType = generics[index];

			Class resolve = resolvableType.resolve();
			System.out.println("parent属性的第"+index+"个泛型参数："+resolve);
		});

//        -----------private Parent parent;字段----------------
//
//        parent属性的类型：com.calebzhao.test.SpringResolvableTypeGenericClass$Parent
//        parent属性的第0个泛型参数：class java.lang.String
//        parent属性的第1个泛型参数：class java.lang.Double
	}

	/**
	 * private List listString;
	 */
	public static void  doTestFindListStr(){
		System.out.println("\n-----------private List listString;字段----------------\n");
		// 通过反射找到  private List listString;字段
		Field listStringField = ReflectionUtils.findField(ResolvableTypeTwoTest.class,"listString");

		// 获取listString字段的ResolvableType
		ResolvableType listStringResolvableType = ResolvableType.forField(listStringField);
		System.out.println("listString属性类型："+listStringResolvableType.getType());

		//获取第0个位置的参数泛型
		Class resolve = listStringResolvableType.getGeneric(0).resolve();
		System.out.println("listString属性泛型参数："+resolve);

//        -----------private List listString;字段----------------
//
//        listString属性类型：java.util.List
//        listString属性泛型参数：class java.lang.String
	}

	/**
	 * private List> listLists;
	 * listLists type:java.util.List>
	 * 泛型参数为：interface java.util.List
	 * 泛型参数为：class java.lang.String
	 * 泛型参数为：class java.lang.String
	 * begin 遍历
	 * 泛型参数为：java.util.List
	 * end 遍历
	 */
	public static void  doTestFindlistLists(){
		System.out.println("\n-----------private List> listLists;字段----------------\n");
		// 通过反射找到   private List> listLists;字段
		Field  listListsField = ReflectionUtils.findField(ResolvableTypeTwoTest.class,"listLists");

		// 获取listLists字段的ResolvableType
		ResolvableType listListsResolvableType = ResolvableType.forField(listListsField);
		System.out.println("listLists属性的类型："+listListsResolvableType.getType());

		//获取第0个位置的参数泛型
		Class resolve = listListsResolvableType.getGeneric(0).resolve();
		System.out.println("listLists属性的泛型参数："+resolve);

		//region 访问嵌套泛型，泛型参数为：class java.lang.String
		resolve = listListsResolvableType.getGeneric(0).getGeneric(0).resolve();
		System.out.println("listLists属性的嵌套泛型参数为："+resolve);

		// 和上面的代码等价
		resolve = listListsResolvableType.getGeneric(0,0).resolve();
		System.out.println("泛型参数为："+resolve);
		//end region

		ResolvableType[] resolvableTypes = listListsResolvableType.getGenerics();
		System.out.println("begin 遍历");
		for(ResolvableType resolvableType: resolvableTypes){
			resolve = resolvableType.resolve();
			System.out.println("泛型参数为："+resolve);
		}
		System.out.println("end 遍历");

//        -----------private List> listLists;字段----------------
//
//        listLists属性的类型：java.util.List>
//        listLists属性的泛型参数：interface java.util.List
//        泛型参数为：class java.lang.String
//        泛型参数为：class java.lang.String
//        begin 遍历
//        泛型参数为：interface java.util.List
//        end 遍历
	}


	/**
	 *  private Map maps;
	 */
	public static void  doTestFindMaps(){
		System.out.println("\n-----------private Map maps;字段----------------\n");
		// 通过反射找到 private Map maps;字段
		Field mapsField = ReflectionUtils.findField(ResolvableTypeTwoTest.class,"maps");

		// 获取maps字段的ResolvableType
		ResolvableType mapsResolvableType = ResolvableType.forField(mapsField);
		System.out.println("maps属性类型:"+mapsResolvableType.getType());

		System.out.println("begin 遍历");
		ResolvableType[] resolvableTypes = mapsResolvableType.getGenerics();
		Class resolve =null;
		for(ResolvableType resolvableType: resolvableTypes){
			resolve = resolvableType.resolve();
			System.out.println("泛型参数为："+resolve);
		}
		System.out.println("end 遍历");

//        -----------private Map maps;字段----------------
//
//        maps属性类型:java.util.Map
//        begin 遍历
//        泛型参数为：class java.lang.String
//        泛型参数为：class java.lang.Long
//        end 遍历

	}

	/**
	 * Map
	 */
	public static void doTestFindReturn(){
		System.out.println("\n-----------public Map getMaps(){ ... } 方法----------------\n");
		// 通过反射找到 public Map getMaps(){ ... } 方法
		Method getMapsMethod = ReflectionUtils.findMethod(ResolvableTypeTwoTest.class, "getMaps");

		// 获取getMaps方法的返回值的ResolvableType
		ResolvableType methodReturnTypeResolvableType = ResolvableType.forMethodReturnType(getMapsMethod);

		System.out.println("getMaps方法的返回值的类型："+methodReturnTypeResolvableType.getType());

		System.out.println("begin 遍历");
		ResolvableType[] resolvableTypes = methodReturnTypeResolvableType.getGenerics();
		Class resolve =null;
		for(ResolvableType resolvableTypeItem: resolvableTypes){
			resolve = resolvableTypeItem.resolve();
			System.out.println("泛型参数为："+resolve);
		}
		System.out.println("end 遍历");

//        -----------public Map getMaps(){ ... } 方法----------------
//
//        getMaps方法的返回值的类型：java.util.Map
//        begin 遍历
//        泛型参数为：class java.lang.String
//        泛型参数为：class java.lang.Long
//        end 遍历
	}

	/**
	 *
	 * @param args
	 */
	public static void main(String[] args) {
//		doTestFindParent();
//		doTestFindListStr();
//		doTestFindlistLists();
//		doTestFindMaps();
		doTestFindReturn();
	}
}
