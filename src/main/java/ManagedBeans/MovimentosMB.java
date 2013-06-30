/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import Controller.MovimentacaoEJB;
import Model.Compromissos;
import Model.Movimentacao;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Tadeu
 */
@ManagedBean
@SessionScoped
public class MovimentosMB {
    @EJB
    MovimentacaoEJB mEJB;
    private String nome;
    EntityManager em;
    private double credito;
    private double debito;
    private double saldo;

    public double getCredito() {
        return credito;
    }

    public void setCredito(double credito) {
        this.credito = credito;
    }

    public double getDebito() {
        return debito;
    }

    public void setDebito(double debito) {
        this.debito = debito;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    private Movimentacao movimentacao;

    public MovimentosMB() {
        movimentacao = new Movimentacao();
    }

    public Movimentacao getMovimentacao() {
        return movimentacao;
    }

    public void setMovimentacao(Movimentacao movimentacao) {
        this.movimentacao = movimentacao;
    }

    public String goListMovimentacao(){
        CalculaSaldo();
        return "List_Movimentacao.xhtml";
    }
    
    public void CalculaSaldo() {
        saldo=credito-debito;
        }
    
    public void Salvar() {
        try {
            mEJB.Salvar(movimentacao);
            if(movimentacao.getTipoMovimento().compareTo("D")==0){
               debito+=movimentacao.getValor();
            }
            if(movimentacao.getTipoMovimento().compareTo("C")==0){
               credito+=movimentacao.getValor();
            }
            movimentacao = new Movimentacao();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    

    public void Excluir(Movimentacao movimento){
        try {
            mEJB.Excluir(movimento);
            if(movimento.getTipoMovimento().compareTo("D")==0){
               debito-=movimento.getValor();
            }
            if(movimento.getTipoMovimento().compareTo("C")==0){
               credito-=movimento.getValor();
            }
            this.movimentacao = new Movimentacao();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Movimentacao> listaDeMovimentacao() {
        return mEJB.listaMovimentacao();
    }
   
}
