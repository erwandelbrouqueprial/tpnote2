/**
 * 
 */
package persistance;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author admin
 *
 */
public class VirtualProxyGeneriqueBuilder<T> implements InvocationHandler {
	private T realObject = null;
	
	private Factory<T> factory;
	
    private Class<?> iface;
	
	public VirtualProxyGeneriqueBuilder(Class<?> iface,Factory<T> factory){
		this.iface = iface;
		this.factory = factory;
	}
	
	public T getProxy(){
		@SuppressWarnings("unchecked")
		T r = (T) Proxy.newProxyInstance(iface.getClassLoader(), new Class<?>[]{ iface }, this);
		System.out.println("création du virtual proxy "+iface.getName());
		return r;
	}

	public Object invoke(Object o, Method m, Object[] args) throws Throwable {
		System.out.println("On appelle la méthode "+m.getName()+" sur le virtual proxy");
		if(realObject == null){
			System.out.println("On initialise l'objet ");
			realObject = factory.create();
		}
		System.out.println("On appelle la methode sur l'objet réel");
		return m.invoke(realObject, args);
	}
}

