/**
 * 
 */
package services;

import java.util.HashSet;
import java.util.Set;

import domaine.IPersonne;
import persistance.Committer;

/**
 * @author erwan
 *
 */
public class UnitOfWork implements ObservateurPersonne {
	
	Set<IPersonne> dirty;
	static UnitOfWork inst = null;
	public UnitOfWork() {
		dirty = new HashSet<IPersonne>();
	}
	public static UnitOfWork getInstance() {
		if (inst == null)
			inst = new UnitOfWork();
		return inst;
	}
	public void action(IPersonne o) {
		dirty.add(o);
	}
	
	public void commit() {
		VisiteurPersonne v = new Committer();
		System.out.println("appel du visiteur");
		for (IPersonne o : dirty) {
			System.out.println("visite "+ o.getNom());
			v.visiter(o);
		}
		dirty.clear();
	}
}
