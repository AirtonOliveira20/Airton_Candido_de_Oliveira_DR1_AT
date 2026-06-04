package com.airton.airmed.DTO;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class PacienteDTO {


    private UUID idPaciente;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private String telefone;
    private String email;
    private String endereco;

    public UUID getId() {
        return idPaciente;
    }

    public void setId(UUID idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getCpf() {
        return this.cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }


}
