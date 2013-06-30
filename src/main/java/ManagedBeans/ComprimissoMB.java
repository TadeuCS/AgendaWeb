/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import Controller.CompromissoEJB;
import Model.Compromissos;
import com.sun.org.apache.bcel.internal.generic.Select;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Tadeu
 */
@ManagedBean
@ViewScoped
public class ComprimissoMB {

    @EJB
    CompromissoEJB cEJB;
    EntityManager em;
    private Compromissos compromissos;

    public ComprimissoMB() {
        compromissos = new Compromissos();
    }

    public Compromissos getCompromissos() {
        return compromissos;
    }

    public void setCompromissos(Compromissos compromissos) {
        this.compromissos = compromissos;
    }

    public void Salvar() {
        try {
            cEJB.Salvar(compromissos);
            compromissos = new Compromissos();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void Excluir(Compromissos compromissos) {
        try {
            cEJB.Excluir(compromissos);
            this.compromissos = new Compromissos();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Compromissos> listaDeCompromissos() {
        return cEJB.listaCompromissos();
    }

    public String getData(Date date) {
        SimpleDateFormat simples = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return simples.format(date);
    }

    public void VerificaStatus() {
        
        Query query = em.createQuery("UPDATE Compromissos c SET c.status= 'Atrasado' where c.DT_Compromisso < (SELECT CURRENT_TIMESTAMP)");
           }
}
