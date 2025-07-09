package com.fiap.cliente.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.cliente.controller.request.ClienteRequest;
import com.fiap.cliente.controller.response.ClienteResponse;
import com.fiap.cliente.domain.Cliente;
import com.fiap.cliente.domain.Cpf;
import com.fiap.cliente.exception.ClienteNotFoundException;
import com.fiap.cliente.mapper.ClienteMapper;
import com.fiap.cliente.usecase.BuscarClientePorIdUseCase;
import com.fiap.cliente.usecase.CriarClienteUseCase;

@ExtendWith(MockitoExtension.class)
class ClienteControllerTest {

        private MockMvc mockMvc;

        @Mock
        private CriarClienteUseCase criarClienteUseCase;

        @Mock
        private BuscarClientePorIdUseCase buscarClientePorIdUseCase;

        @Mock
        private ClienteMapper clienteMapper;

        private ObjectMapper objectMapper;

        @BeforeEach
        void setUp() {
                mockMvc = MockMvcBuilders
                                .standaloneSetup(new ClienteController(criarClienteUseCase, buscarClientePorIdUseCase,
                                                clienteMapper))
                                .setControllerAdvice(new com.fiap.cliente.exception.GlobalExceptionHandler())
                                .build();
                objectMapper = new ObjectMapper().findAndRegisterModules(); // Suporte a LocalDate
        }

        @Test
        void deveCriarClienteComSucesso() throws Exception {
                ClienteRequest request = criarClienteRequest();
                Cliente cliente = criarCliente();
                UUID uid = UUID.randomUUID();
                ClienteResponse response = criarClienteResponse(uid);

                when(clienteMapper.toDomain(any(ClienteRequest.class))).thenReturn(cliente);

                when(criarClienteUseCase.execute(any())).thenReturn(uid);
                when(clienteMapper.toResponse(any(), any())).thenReturn(response);

                mockMvc.perform(post("/clientes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.id").value(uid.toString()))
                                .andExpect(jsonPath("$.nome").value("João Silva"));
        }

        @Test
        void deveBuscarClientePorIdComSucesso() throws Exception {
                UUID uid = UUID.randomUUID();
                Cliente cliente = criarCliente();
                ClienteResponse response = criarClienteResponse(uid);

                when(buscarClientePorIdUseCase.execute(uid)).thenReturn(cliente);
                when(clienteMapper.toResponse(cliente, uid)).thenReturn(response);

                mockMvc.perform(get("/clientes/{uid}", uid))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.id").value(uid.toString()))
                                .andExpect(jsonPath("$.nome").value("João Silva"));
        }

        @Test
        void deveRetornarNotFoundQuandoClienteNaoExiste() throws Exception {
                UUID uid = UUID.randomUUID();

                when(buscarClientePorIdUseCase.execute(uid)).thenThrow(new ClienteNotFoundException(uid));

                mockMvc.perform(get("/clientes/{uid}", uid))
                                .andExpect(status().isNotFound());
        }

        @Test
        void deveRetornarBadRequestParaJsonInvalido() throws Exception {
                ClienteRequest request = new ClienteRequest("", "", "invalid", "", null, "", null);

                when(clienteMapper.toDomain(any(ClienteRequest.class)))
                                .thenThrow(new com.fiap.cliente.exception.ValidationException("Dados inválidos"));

                mockMvc.perform(post("/clientes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
                                .andExpect(status().isBadRequest());
        }

        private ClienteRequest criarClienteRequest() {
                return new ClienteRequest("João Silva", "12345678909", "joao@email.com", "11999999999",
                                LocalDate.of(1990, 1, 1), "Rua das Flores, 123", null);
        }

        private Cliente criarCliente() {
                return new Cliente("João Silva", new Cpf("12345678909"), "joao@email.com",
                                "11999999999", LocalDate.of(1990, 1, 1), "Rua das Flores, 123");
        }

        private ClienteResponse criarClienteResponse(UUID uid) {
                return new ClienteResponse(uid, "João Silva", "12345678909", "joao@email.com",
                                "11999999999", LocalDate.of(1990, 1, 1), "Rua das Flores, 123");
        }
}
