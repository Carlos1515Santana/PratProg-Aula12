package command;

import java.io.IOException;

import javax.crypto.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import crypto.T_EncriptaDecriptaAES;
import model.Usuario;
import service.UsuarioService;

public class FazerLogin implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nome = request.getParameter("username");
		String senha = request.getParameter("passwd");

		Usuario usuario = new Usuario();
		usuario.setUsername(nome);
		usuario.setPassword(senha);
		UsuarioService service = new UsuarioService();
		T_EncriptaDecriptaAES aes = new T_EncriptaDecriptaAES();
		usuario.setUsername(nome);
		usuario.setPassword(senha);
		
		if(service.validar(usuario)) {
			try{
				usuario.setUsername(aes.decrypt(nome, "fSim"));
			}catch(Exception e){
				e.printStackTrace();
			}
			HttpSession session = request.getSession();
			session.setAttribute("logado", usuario);
			System.out.println("Logou "+usuario);
		} else {
			System.out.println("Não Logou "+usuario);
			throw new ServletException("Usuário/Senha inválidos");
		}
		response.sendRedirect("index.jsp");
	}

}
