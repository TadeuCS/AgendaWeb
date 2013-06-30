/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Contato;
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
public class ContatoEJB {

    @PersistenceContext(unitName = "AgendaPIPU")
    private EntityManager em;

    public void Salvar(Contato contato) {
        em.merge(contato);
    }

    public List<Contato> listaContato() {
        Query query = em.createQuery("Select c From Contato c");
        return query.getResultList();
    }

    public void Excluir(Contato contato) {
        contato = em.getReference(Contato.class, contato.getId());
        em.remove(contato);
    }
}
