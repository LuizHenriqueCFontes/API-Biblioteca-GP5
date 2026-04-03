package com.biblioteca.gp5.user.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity //Informa que a classe eh uma entity
@Table(name = "users")//Defino como eh o nome da tabela no db
@Data //Isso significa que vai gerar os get e set automatico de acordo com os atributos, isso vem do lombok
public class Users implements UserDetails { //Implemento um userDetails, que sera tipo uma armazenado de users
	
	@Id //informa que é um id
	@GeneratedValue(strategy = GenerationType.UUID) /*Isso define que sera gerado um id automatico, 
	e vai ser tipo um hash de senha, varios caracteres
	*/
	
	@Column(name = "id_usuarios") //Uso o column para dar definicoes da tabela, o name nesse caso
	private String idUsuarios;
	
	@Column(nullable = false, unique = false) //Informo que nao pode esta vazio, mas nao é unique
	private String username;
	
	@Column(nullable = false, unique = true) //Informo que nao pode esta vazio, e é um atributo unique
	private String email;
	
	@Column(nullable = false, unique = false) //Informo que nao pode esta vazio, mas nao é unique
	private String telefone;
	
	@Column(nullable = false, unique = false) //Informo que nao pode esta vazio, mas nao é unique
	private String password;
	
	@Enumerated(EnumType.STRING) //Uso o enumerated para definir comos sera o enum, depois informo que vai ser como string
	UserRole role;
	
	private boolean enabled; //atributo para verificação da conta
	
	@Column(name = "created_at") // atributo que vai ser preenchido automaticamente no db, apenas para colocar quando criou a conta
	private LocalDateTime createdAt;
	
	public Users() {}

	//Crio um construtor que vai receber quase todos os atributos, para realizar o cadastrado
	public Users(String username, String email, String telefone, String password, UserRole role) {
		this.username = username;
		this.email = email;
		this.telefone = telefone;
		this.password = password;
		this.role = role;
	}
	
	//Crio um construtor que vai receber o email e senha, para realizar o login
	public Users(String email, String password) {
		this.email = email;
		this.password = password;
		
	}
	
	
	
	
	
	@Override //Esse metodo serve para definir as roles e acessa-las depois
	public Collection<? extends GrantedAuthority> getAuthorities() { 
		
		//Crio alguns ifs, para verificar qual role foi enviada, para definir no padrão do spring
		if(this.role == UserRole.USER) {
			return List.of(new SimpleGrantedAuthority("ROLE_USER"));
			
		}else if(this.role == UserRole.ALUNO) {
			return List.of(new SimpleGrantedAuthority("ROLE_ALUNO"));
			
		}else {
			return List.of(new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
	}

	
	//Esses metodos fazem parte da interface do userdetails
	@Override
	public @Nullable String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
		
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
		
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
		
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
		
	}

	@Override
	public boolean isEnabled() {
		return true;
		
	}

}
