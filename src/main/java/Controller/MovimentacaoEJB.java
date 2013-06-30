/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Compromissos;
import Model.Movimentacao;
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
public class MovimentacaoEJB {

    @PersistenceContext(unitName = "AgendaPIPU")
    public EntityManager em;

    public void Salvar(Movimentacao obj) {
        em.merge(obj);
    }

    public List<Movimentacao> listaMovimentacao() {
        Query query = em.createQuery("Select m From Movimentacao m");
        return query.getResultList();
    }
    
    public void Excluir(Movimentacao obj2){
        obj2 = em.getReference(Movimentacao.class, obj2.getId());
        em.remove(obj2);
    }
}
