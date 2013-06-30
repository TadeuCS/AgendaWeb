/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import Controller.GrupoEJB;
import Model.Grupo;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Tadeu
 */
@ManagedBean
@RequestScoped
public class GrupoMB {

    @EJB
    GrupoEJB GEJB;
    private Grupo grupo;

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public GrupoMB() {
        grupo = new Grupo();
    }

    public void Salvar() {
        try {
            GEJB.Salvar(grupo);
            grupo = new Grupo();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void Excluir(Grupo grupo) {
        try {
            GEJB.Excluir(grupo);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
    public List<Grupo> listaDeGrupos(){
        return GEJB.listaDeGrupos();
    }
}
