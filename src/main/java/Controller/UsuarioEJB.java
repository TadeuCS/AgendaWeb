/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tadeu CS
 */
@Stateless
public class UsuarioEJB {

    @PersistenceContext
    EntityManager em;

    public void salva(Usuario obj) {
        em.merge(obj);
    }
}
