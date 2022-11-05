package com.pj.untapped.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pj.untapped.domain.Address;
import com.pj.untapped.domain.Event;
import com.pj.untapped.domain.Permission;
import com.pj.untapped.domain.User;
import com.pj.untapped.repositories.AddressRepository;
import com.pj.untapped.repositories.EventRepository;
import com.pj.untapped.repositories.PermissionRepository;
import com.pj.untapped.repositories.UserRepository;

@Service
public class DBService { //Classe para instanciar objetos no banco
	
	//Importa os Autowired
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PermissionRepository permissionRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private EventRepository eventRepository;

	@Transactional
	public void instanciaDB() {
	    /*Criando endereços*/
		Address ad1 = new Address(null,"Onze", "RUA GV10", "Goiânia Viva", "74000-000", "Goiânia", "Goiás", "Brasil");
		Address ad2 = new Address(null,"Mercado Central", "Avenida T-4", "Setor Bueno", "74000-000", "Goiânia", "Goiás", "Brasil");
		/*Criando Eventos*/
		Event ev1 = new Event(null, "Cabaré 50 anos", "Uma festa promovida por Eduardo Costa", LocalDateTime.now(), LocalDateTime.now().plusHours(3), "Capa Provisória", 30000, ad2, "O CABARÉ é um lugar onde além de um espetáculo, você também presencia os gigantes do sertanejo contando: histórias, tirando sarro uns dos outros e cantam as mais pedidas em churrascos, karaokês e até mesmo bares de petisco.");
		Event ev2 = new Event(null, "Hugo e Guilherme", "NOPELO 360", LocalDateTime.now(), LocalDateTime.now().plusHours(5), "Capa Provisória", 30000, ad2, "O evento NO PELO 360 tem muita história pra contar, mas só pra quem estiver disposto a ouvir..");
		Event ev3 = new Event(null, "Murilo Ruff", "Ao vivão", LocalDateTime.now(), LocalDateTime.now().plusHours(3), "Capa Provisória", 30000, ad2, "O evento NO PELO 360 tem muita história pra contar, mas só pra quem estiver disposto a ouvir..");
		Event ev4 = new Event(null, "Henrique e Juliano", "Caldas Contry", LocalDateTime.now(), LocalDateTime.now().plusHours(4), "Capa Provisória", 30000, ad2, "O evento NO PELO 360 tem muita história pra contar, mas só pra quem estiver disposto a ouvir..");
		Event ev5 = new Event(null, "Thiaguinho", "Tardezinha", LocalDateTime.now(), LocalDateTime.now().plusHours(5), "Capa Provisória", 30000, ad2, "O evento NO PELO 360 tem muita história pra contar, mas só pra quem estiver disposto a ouvir..");
		Event ev6 = new Event(null, "3030", "Tropicaliza", LocalDateTime.now(), LocalDateTime.now().plusHours(3), "Capa Provisória", 30000, ad2, "O evento NO PELO 360 tem muita história pra contar, mas só pra quem estiver disposto a ouvir..");
		/*Criando Permissões*/
		Permission per1 = new Permission();
        per1.setDescription("ADMIN");
        Permission per2 = new Permission();
        per2.setDescription("MANAGER");
        /*Criptografando senha*/
        String senhaUser1 = encriptPassword("admin234");
		/*Criando um usuário*/
        User user1 = new User();
        user1.setId(null);
        user1.setUsername("jheorgenes@gmail.com");
        user1.setPassword(senhaUser1);
        user1.setEmail("jheorgenes@gmail.com");
        user1.setCpf("933.670.620-90");
        user1.setBirthDate(LocalDate.of(1993, Month.DECEMBER, 20));
        user1.setAccountNonExpired(true);
        user1.setAccountNonLocked(true);
        user1.setCredentialsNonExpired(true);
        user1.setEnabled(true);
        user1.setPermissions(Arrays.asList(per1, per2));
		//Adiciona os relacionamentos
		ad2.setEvent(ev1);
		ad1.setUser(user1);
		//Salva no banco de dados
        permissionRepository.saveAll(Arrays.asList(per1, per2));
		userRepository.saveAll(Arrays.asList(user1));
		addressRepository.saveAll(Arrays.asList(ad1, ad2));
		eventRepository.saveAll(Arrays.asList(ev1, ev2, ev3, ev4, ev5, ev6));
	}

    private String encriptPassword(String password) {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
	    encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
	    DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
	    passwordEncoder.setDefaultPasswordEncoderForMatches(new Pbkdf2PasswordEncoder());
	    return passwordEncoder.encode(password);
    }
}
