/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Compromissos;
import Model.Contato;
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
public class CompromissoEJB {

    @PersistenceContext(unitName = "AgendaPIPU")
    public EntityManager em;

    public void Salvar(Compromissos compromissos) {
        em.merge(compromissos);
    }

    public List<Compromissos> listaCompromissos() {
        Query query = em.createQuery("Select c From Compromissos c");
        return query.getResultList();
    }
    
    public void Excluir(Compromissos compromissos){
        compromissos = em.getReference(Compromissos.class, compromissos.getId());
        em.remove(compromissos);
    }
}
