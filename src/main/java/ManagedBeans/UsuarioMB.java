/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import Controller.UsuarioEJB;
import Model.Usuario;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

/**
 *
 * @author Tadeu CS
 */
@ManagedBean
@RequestScoped
public class UsuarioMB {

    private Usuario user = new Usuario();
    @EJB
    UsuarioEJB usuarioEJB;
    EntityManager em;
    private String senha2;

    public UsuarioMB() {
    }

    public String getSenha2() {
        return senha2;
    }

    public void setSenha2(String senha2) {
        this.senha2 = senha2;
    }

 
    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public boolean validaSenha() {
        boolean senhaValida = false;
        if (user.getSenha().compareTo(senha2) != 0) {
            senhaValida = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Senhas diferentes"));
        } else {
            senhaValida = true;
        }
        if((user.getSenha().length()<6)||(senha2.length()<6)){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Tamanho minino da senha é 6 caracteres"));
        }
        return senhaValida;
    }

    public boolean validaEmail() {
        String email = user.getEmail();
        boolean emailValido = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                emailValido = true;
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Email invalido"));
            }
        }
        return emailValido;
    }

    public void salva() {
        if ((validaEmail() == true) && (validaSenha() == true)) {
            Criptografia();
            usuarioEJB.salva(user);
        }
    }
    public String goTOLogin(){
        return "Login.xhtml";
    }
    public void Criptografia() {
        String dados = user.getSenha().trim().toUpperCase();



        user.setSenha(dados.replace("Z", "A01")
                .replace("Y", "B02")
                .replace("X", "C03")
                .replace("W", "D04")
                .replace("V", "E05")
                .replace("U", "F06")
                .replace("T", "G07")
                .replace("S", "H08")
                .replace("R", "I09")
                .replace("Q", "J10")
                .replace("P", "K11")
                .replace("O", "L12")
                .replace("N", "M13")
                .replace("M", "N14")
                .replace("L", "O15")
                .replace("K", "P16")
                .replace("J", "Q17")
                .replace("I", "R18")
                .replace("H", "S19")
                .replace("G", "T20")
                .replace("F", "U21")
                .replace("E", "V22")
                .replace("D", "W23")
                .replace("C", "X24")
                .replace("B", "Y25")
                .replace("A", "Z26")
                .replace("0", "A36")
                .replace("1", "B35")
                .replace("2", "C34")
                .replace("3", "D33")
                .replace("4", "E32")
                .replace("5", "F31")
                .replace("6", "G30")
                .replace("7", "H29")
                .replace("8", "I28")
                .replace("9", "J27"));

    }
}
