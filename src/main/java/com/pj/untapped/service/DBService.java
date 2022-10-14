package com.pj.untapped.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pj.untapped.domain.Address;
import com.pj.untapped.domain.Event;
import com.pj.untapped.domain.User;
import com.pj.untapped.repositories.AddressRepository;
import com.pj.untapped.repositories.EventRepository;
import com.pj.untapped.repositories.UserRepository;

@Service
public class DBService { //Classe para instanciar objetos no banco
	
	//Importa os Autowired
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private EventRepository eventRepository;

	@Transactional
	public void instanciaDB() {
		//Implementa as instancias
		User us1 = new User(null, "Jheorgenes Warlley", "jheorgenes@gmail.com", "123456", "933.670.620-90", LocalDate.of(1993, Month.DECEMBER, 20));
		User us2 = new User(null, "Joaquin Silva", "joaquin@gmail.com", "654321", "426.783.630-23", LocalDate.of(1989, Month.MARCH, 10));
		
		Address ad1 = new Address(null,"Onze", "RUA GV10", "Goiânia Viva", "74000-000", "Goiânia", "Goiás", "Brasil");
		Address ad2 = new Address(null,"Mercado Central", "Avenida T-4", "Setor Bueno", "74000-000", "Goiânia", "Goiás", "Brasil");
		
		Event ev1 = new Event(null, "Cabaré 50 anos", "Uma festa promovida por Gustavo Lima", LocalDateTime.now(), LocalDateTime.now(), "Capa Provisória", 30000, ad2, "O CABARÉ é um lugar onde além de um espetáculo, você também presencia os gigantes do sertanejo contando: histórias, tirando sarro uns dos outros e cantam as mais pedidas em churrascos, karaokês e até mesmo bares de petisco.");
		//Adiciona os relacionamentos
		ad1.setUser(us1);
//		ad2.setUser(us1);
		ad2.setEvent(ev1);
		
		//Salva no banco de dados
		userRepository.saveAll(Arrays.asList(us1, us2));
		addressRepository.saveAll(Arrays.asList(ad1, ad2));
		eventRepository.saveAll(Arrays.asList(ev1));
	}
}
