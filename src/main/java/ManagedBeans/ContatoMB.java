/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import Controller.ContatoEJB;
import Model.Contato;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Tadeu
 */
@ManagedBean
@RequestScoped
public class ContatoMB {
    @EJB
    ContatoEJB CEJB;
    private Contato contato;

    public ContatoMB() {
        contato = new Contato();
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public void Salvar() {
        try {
            CEJB.Salvar(contato);
            contato = new Contato();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void Excluir(Contato contato){
        try {
            CEJB.Excluir(contato);
            this.contato = new Contato();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public List<Contato> listaDeContatos(){
        return CEJB.listaContato();
    }
}
