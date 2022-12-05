package com.pj.untapped.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pj.untapped.domain.Address;
import com.pj.untapped.domain.Categories;
import com.pj.untapped.domain.Event;
import com.pj.untapped.domain.Order;
import com.pj.untapped.domain.Permission;
import com.pj.untapped.domain.Ticket;
import com.pj.untapped.domain.TicketsOrder;
import com.pj.untapped.domain.User;
import com.pj.untapped.domain.enuns.StatusTicket;
import com.pj.untapped.repositories.AddressRepository;
import com.pj.untapped.repositories.CategoriesRepository;
import com.pj.untapped.repositories.EventRepository;
import com.pj.untapped.repositories.OrderRepository;
import com.pj.untapped.repositories.PermissionRepository;
import com.pj.untapped.repositories.TicketRepository;
import com.pj.untapped.repositories.TicketsOrderRepository;
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
	
	@Autowired
    private TicketRepository ticketRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private TicketsOrderRepository ticketsOrderRepository;
	
	@Autowired
	private CategoriesRepository categoriesRepository;

	@Transactional
	public void instanciaDB() {
	    /*Criando endereços*/
		Address ad1 = new Address(null,"Oasa", "Rua GV10", "Goiânia Viva", "74000-000", "Goiânia", "Goiás", "Brasil");
		
		Address ad2 = new Address(null,"CEL Da OAB", "Av. de Furnas", "Jardim Rio Grande", "74982-490", "Aparecida de Goiânia", "Goiás", "Brasil");
		ad2.setAddressNumber("312");
		
		Address ad3 = new Address(null,"Estádio Serra Dourada", "Av. Fued José Sebba", "Jardim Goiás", "74982-490", "Goiânia", "Goiás", "Brasil");
		ad3.setAddressNumber("1170");
		
		Address ad4 = new Address(null,"Centro Cultural Oscar Niemeyer", "Av. Dep. Jamel Cecílio", "Jardim Goiás", "74891-135", "Goiânia", "Goiás", "Brasil");
		ad4.setAddressComplement("Km 01");
		
		/*Criando Categorias de Eventos*/
		Categories ct1 = new Categories(null, "SERTANEJO");
		Categories ct2 = new Categories(null, "PAGODE");
		Categories ct3 = new Categories(null, "ROCK");
		Categories ct4 = new Categories(null, "SAMBA");
		Categories ct5 = new Categories(null, "RAP");
		Categories ct6 = new Categories(null, "FUNK");
		Categories ct7 = new Categories(null, "HIPHOP");
		Categories ct8 = new Categories(null, "MPB");
		Categories ct9 = new Categories(null, "POP");
		Categories ct10 = new Categories(null, "ELETRONICA");
		Categories ct11 = new Categories(null, "GOSPEL");
		Categories ct12 = new Categories(null, "STANDUP");
		Categories ct13 = new Categories(null, "CINEMA");
		Categories ct14 = new Categories(null, "TEATRO");
		Categories ct15 = new Categories(null, "FORRÓ");	
		
		/*Criando Eventos*/
		Event ev1 = new Event(null, 
		        "Cabaré 10 anos", 
		        "Uma festa promovida por Leonardo e Gusttavo Lima", 
		        LocalDateTime.now(), 
		        LocalDateTime.now().plusHours(5), 
		        "7236239LeonardoEGusttavoLima.jpg", 
		        35000, 
		        ad3, 
		        "Grandes amigos, muita resenha. Venha prestigiar esses ícones da música Sertaneja");
		ev1.setCategories(Arrays.asList(ct1, ct15));
		
		Event ev2 = new Event(null, 
		        "Hugo e Guilherme", 
		        "NOPELO 360", 
		        LocalDateTime.now(), 
		        LocalDateTime.now().plusHours(5), 
		        "1234011HugoeGuilherme.jpg", 
		        30000, 
		        ad3, 
		        "Venha participar dessa festa espetacular chamada NOPELO 360 em Goiânia. Espaço para 35 Mil pessoas");
		ev2.setCategories(Arrays.asList(ct1));
		
		Event ev3 = new Event(null, 
		        "Murillo Ruff", 
		        "Ao Vivão", 
		        LocalDateTime.now(), 
		        LocalDateTime.now().plusHours(5), 
		        "9933464MurilloRuff.png", 
		        20000, 
		        ad3, 
		        "Veja se você consegue escutar essa sem tomar uma!!");
		ev3.setCategories(Arrays.asList(ct1));
		
		Event ev4 = new Event(null, 
		        "Joao Gomes", 
		        "Caldas Contry", 
		        LocalDateTime.now(), 
		        LocalDateTime.now().plusHours(4), 
		        "5150304JoaoGomes.webp", 
		        20000, 
		        ad2, 
		        "Não tem formigueiro mais o piseiro está na ativa");
		ev4.setCategories(Arrays.asList(ct1, ct15));
		
		Event ev5 = new Event(null, 
		        "Vitinho", 
		        "Sonhos", 
		        LocalDateTime.now(), 
		        LocalDateTime.now().plusHours(3),
		        "2013771Vitinho.png", 
		        5000, 
		        ad2, 
		        "Venha realizar seus sonhos ouvindo a minha canção");
		ev5.setCategories(Arrays.asList(ct2, ct4));
		
		Event ev6 = new Event(null, 
		        "3030", 
		        "Tropicaliza", 
		        LocalDateTime.now(), 
		        LocalDateTime.now().plusHours(3), 
		        "95926873030.jpg", 
		        5000, 
		        ad4, 
		        "Direto da Bahia para cantar RAP pra vocês!");
		ev6.setCategories(Arrays.asList(ct5, ct7));	
		
		/*Criando Permissões*/
		Permission per1 = new Permission();
        per1.setDescription("ADMIN");
        Permission per2 = new Permission();
        per2.setDescription("MANAGER");
        Permission per3 = new Permission();
        per3.setDescription("USER");
        
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
        
        Ticket tkt1 = new Ticket(null, "Lounge", 144.5, "Lounge", LocalDateTime.now().plusHours(5), 100, StatusTicket.DISPONIVEL, ev6);
        Ticket tkt15 = new Ticket(null, "Camarote", 300.0, "Camarote", LocalDateTime.now().plusHours(5), 100, StatusTicket.DISPONIVEL, ev6);
        
        Ticket tkt2 = new Ticket(null, "Pista", 240.0, "Pista", LocalDateTime.now().plusHours(5), 10000, StatusTicket.DISPONIVEL, ev1);
        Ticket tkt3 = new Ticket(null, "Camarote", 400.0, "Camarote", LocalDateTime.now().plusHours(5), 10000, StatusTicket.DISPONIVEL, ev1);
        Ticket tkt4 = new Ticket(null, "Camarote Open Bar", 600.0, "Camarote Open Bar", LocalDateTime.now().plusHours(5), 15000, StatusTicket.DISPONIVEL, ev1);
        
        Ticket tkt5 = new Ticket(null, "Pista", 200.0, "Pista", LocalDateTime.now().plusHours(5), 10000, StatusTicket.DISPONIVEL, ev2);
        Ticket tkt6 = new Ticket(null, "Camarote Open Bar", 470.0, "Camarote Open Bar", LocalDateTime.now().plusHours(5), 10000, StatusTicket.DISPONIVEL, ev2);
        
        Ticket tkt7 = new Ticket(null, "Pista", 150.0, "Pista", LocalDateTime.now().plusHours(5), 10000, StatusTicket.DISPONIVEL, ev3);
        Ticket tkt8 = new Ticket(null, "Camarote Open Bar", 470.0, "Camarote Open Bar", LocalDateTime.now().plusHours(5), 10000, StatusTicket.DISPONIVEL, ev3);
        
        Ticket tkt9 = new Ticket(null, "Pista", 100.0, "Pista", LocalDateTime.now().plusHours(5), 10000, StatusTicket.DISPONIVEL, ev4);
        Ticket tkt10 = new Ticket(null, "Camarote Open Bar", 250.0, "Camarote Open Bar", LocalDateTime.now().plusHours(5), 10000, StatusTicket.DISPONIVEL, ev4);
        
        Ticket tkt11 = new Ticket(null, "Pista", 100.0, "Pista", LocalDateTime.now().plusHours(5), 2500, StatusTicket.DISPONIVEL, ev5);
        Ticket tkt12 = new Ticket(null, "Camarote Open Bar", 250.0, "Camarote Open Bar", LocalDateTime.now().plusHours(5), 2500, StatusTicket.DISPONIVEL, ev5);
        
        Ticket tkt13 = new Ticket(null, "Pista", 100.0, "Pista", LocalDateTime.now().plusHours(5), 2500, StatusTicket.DISPONIVEL, ev6);
        Ticket tkt14 = new Ticket(null, "Camarote Open Bar", 250.0, "Camarote Open Bar", LocalDateTime.now().plusHours(5), 2500, StatusTicket.DISPONIVEL, ev6);
        
        Order order = new Order();
        order.setId(null);
        order.setDate(LocalDateTime.now());
        order.setUser(user1);
        order.setEvent(ev6);
        order.setTotalValue(200.0);
        orderRepository.saveAll(Arrays.asList(order));
        
        TicketsOrder ticketsOrder = new TicketsOrder();
        ticketsOrder.setId(null);
        ticketsOrder.setPrice(100.0);
        ticketsOrder.setQuantity(2);
        ticketsOrder.setQrCode(gerarDigitosAleatorios(12));
        ticketsOrder.setTicket(tkt1);
        ticketsOrder.setOrder(order);
        ticketsOrderRepository.saveAll(Arrays.asList(ticketsOrder));
        
        //ev6.setTickets(Arrays.asList(tkt1));
        
		//Adiciona os relacionamentos
		ad2.setEvent(ev1);
		ad1.setUser(user1);
		//Salva no banco de dados
		categoriesRepository.saveAll(Arrays.asList(ct1, ct2, ct3, ct4, ct5, ct6, ct7, ct8, ct9, ct10, ct11, ct12, ct13, ct14, ct15));
        permissionRepository.saveAll(Arrays.asList(per1, per2, per3));
		userRepository.saveAll(Arrays.asList(user1));
		addressRepository.saveAll(Arrays.asList(ad1, ad2, ad3, ad4));
		eventRepository.saveAll(Arrays.asList(ev1, ev2, ev3, ev4, ev5, ev6));
		ticketRepository.saveAll(Arrays.asList(tkt1, tkt2, tkt3, tkt4, tkt5, tkt6, tkt7, tkt8, tkt9, tkt10, tkt11, tkt12, tkt13, tkt14, tkt15));
	}

    private String encriptPassword(String password) {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
	    encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
	    DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
	    passwordEncoder.setDefaultPasswordEncoderForMatches(new Pbkdf2PasswordEncoder());
	    return passwordEncoder.encode(password);
    }
    
    private static String gerarDigitosAleatorios(int digitos) {
        StringBuilder text = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < digitos; i++) {
            text.append(random.nextInt(10)); // gerar um número aleatório entre 0 e 9
        }
        return text.toString();
    }
}