package desafioapitodo;

import desafioapitodo.domain.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DesafioApiToDoApplicationTests {

    /**
     Estilo de Programação Reativo vs. Síncrono:

     WebTestClient:
     Faz parte do módulo WebFlux do Spring Framework, que oferece suporte à programação reativa.
     Ele é projetado para interações assíncronas e baseadas em reatividade.

     RestTemplate: É uma classe síncrona, tradicionalmente usada em aplicativos baseados em Servlet
     e não oferece suporte direto a programação reativa.
     */

    @Autowired
    private  WebTestClient webTestClient;



    // bad and good way to test

    @Test
    void testCreateTodoSucess() {
        var todo = new Todo("todo 1", "description 1", false, 1);

        webTestClient.post()
                .uri("/todos/create")
                .bodyValue(todo)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$.length()").isEqualTo(1)
                .jsonPath("$[0].nome").isEqualTo(todo.getNome())
                .jsonPath("$[0].descricao").isEqualTo(todo.getDescricao())
                .jsonPath("$[0].prioridade").isEqualTo(todo.getPrioridade())
                .jsonPath("$[0].concluido").isEqualTo(todo.isRealizado());

    }

    @Test
    void testCreateTodoFail() {
        webTestClient.post()
                .uri("/todos/create")
                .bodyValue(new Todo("", "", false, 0))
                .exchange()
                .expectStatus().isBadRequest();
    }



}
