package com.github.lipesc.springapidev.controller;

import com.github.lipesc.springapidev.entity.Client;
import com.github.lipesc.springapidev.entity.DepositRequest;
import com.github.lipesc.springapidev.entity.WithdrawRequest;
import com.github.lipesc.springapidev.service.ClientService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

  @Autowired
  private ClientService clientService;

  @PostMapping
  public Client createClient(@RequestBody Client client) {
    return clientService.createClient(client);
  }

  @GetMapping
  public List<Client> getAllClients() {
    return clientService.getALLClient();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Client> getClientById(@PathVariable Long id) {
    Optional<Client> client = clientService.getClientById(id);
    return client
      .map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<Client> updateClient(
    @PathVariable Long id,
    @RequestBody Client clientDetails
  ) {
    Client updatedClient = clientService.updateClient(id, clientDetails);
    return ResponseEntity.ok(updatedClient);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
    clientService.deleteClient(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/{clientId}/deposit/{companyId}")
  public ResponseEntity<Void> deposit(
    @PathVariable Long clientId,
    @PathVariable Long companyId,
    @RequestBody DepositRequest request
  ) {
    clientService.deposit(clientId, companyId, request);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/{clientId}/withdraw/{companyId}")
  public ResponseEntity<Void> withdraw(
    @PathVariable Long clientId,
    @PathVariable Long companyId,
    @RequestBody WithdrawRequest request
  ) {
    clientService.withdraw(clientId, companyId, request);
    return ResponseEntity.ok().build();
  }
}
