/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Grupo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Tadeu
 */
@Stateless
public class GrupoEJB {

    @PersistenceContext(unitName = "AgendaPIPU")
    EntityManager em;

    public void Salvar(Grupo grupo) {
        em.merge(grupo);
    }

    public List<Grupo> listaDeGrupos() {
        Query query = em.createQuery("Select g FROM Grupo g");
        return query.getResultList();
    }
    
    public void Excluir(Grupo grupo){
        grupo = em.getReference(Grupo.class, grupo.getId());
        em.remove(grupo);
    }
}
