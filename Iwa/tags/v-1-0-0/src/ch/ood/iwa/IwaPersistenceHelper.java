package ch.ood.iwa;

import java.util.HashMap;
import java.util.Map;

import org.vaadin.appfoundation.authentication.data.User;
import org.vaadin.appfoundation.authorization.Role;
import org.vaadin.appfoundation.persistence.facade.FacadeFactory;

import ch.ood.iwa.authorization.ModulePermission;

/**
 * Helper for refreshing entities (in a sense that they 
 * are attached to current the JPA session)
 * 
 * @author Mischa
 *
 */
public class IwaPersistenceHelper {

	
	/**
	 * Reads the User from the datastore
	 * 
	 * @param user
	 * @return
	 */
	public User refreshUser(User user) {
		return FacadeFactory.getFacade().find(User.class, user.getId());
	}
	
	/**
	 * Reads the ModulePermission from the datastore
	 * 
	 * @param user
	 * @return
	 */
	public ModulePermission refreshPermission(ModulePermission permission) {
		return FacadeFactory.getFacade().find(ModulePermission.class, permission.getId());
	}
	
	/**
	 * Reads the Role from the datastore
	 * 
	 * @param user
	 * @return
	 */
	public Role refreshRole(Role role) {
		if (role.getId() != null) {
			return FacadeFactory.getFacade().find(Role.class, role.getId());
		} else {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("identifier", role.getIdentifier());
			return FacadeFactory.getFacade().find("SELECT r FROM Role r WHERE r.identifier = :identifier", parameters);
		}
	}		
}
