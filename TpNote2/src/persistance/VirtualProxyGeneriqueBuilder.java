package persistance;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author admin Classe représentant le Virtual Proxy
 */
public class VirtualProxyGeneriqueBuilder<T> implements InvocationHandler {
	private T realObject = null;

	private Factory<T> factory;

	private Class<?> iface;

	/**
	 * Constructeur de la classe
	 * 
	 * @param iface
	 * @param factory
	 */
	public VirtualProxyGeneriqueBuilder(Class<?> iface, Factory<T> factory) {
		this.iface = iface;
		this.factory = factory;
	}

	/**
	 * Méthode créant et retourant le proxy
	 * 
	 * @return le proxy créé
	 */
	public T getProxy() {
		@SuppressWarnings("unchecked")
		T r = (T) Proxy.newProxyInstance(iface.getClassLoader(),
				new Class<?>[] { iface }, this);
		System.out.println("création du virtual proxy " + iface.getName());
		return r;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object,
	 * java.lang.reflect.Method, java.lang.Object[])
	 */
	public Object invoke(Object o, Method m, Object[] args) throws Throwable {
		System.out.println("On appelle la méthode " + m.getName()
				+ " sur le virtual proxy");
		if (realObject == null) {
			System.out.println("On initialise l'objet ");
			realObject = factory.create();
		}
		System.out.println("On appelle la methode sur l'objet réel");
		return m.invoke(realObject, args);
	}
}
