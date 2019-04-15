package br.edu.testes.domain;

import java.io.Serializable;
import java.util.regex.Pattern;

public class Pessoa implements Serializable {

  private String cpf;
  private String nome;

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    if (cpf == null)
      throw new NullPointerException(
        "O CPF corresponde a um valor nulo."
      );

    if (cpf.trim().isEmpty())
      throw new IllegalArgumentException(
        "O CPF corresponde a um valor vazio."
      );
    
    String exp = "^\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}$";

    if (!Pattern.matches(exp, cpf))
      throw new IllegalArgumentException(
        "O CPF deve conter 11 (onze) algarismos."
      );

    this.cpf = cpf;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    if (nome == null)
      throw new NullPointerException(
        "O nome corresponde a um valor nulo."
      );

    if (nome.trim().isEmpty())
      throw new IllegalArgumentException(
        "O nome corresponde a um valor vazio."
      );

    this.nome = nome;
  }

}
