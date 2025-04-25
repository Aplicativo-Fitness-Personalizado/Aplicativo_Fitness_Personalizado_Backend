package com.generation.appfitness.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_treino")
public class Treino {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O atributo título é Obrigatório!") 
	@Size(min = 3, max = 100, message = "O atributo título deve conter no mínimo 03 e no máximo 100 caracteres")
	private String titulo;

	@NotBlank(message = "O atributo descrição é Obrigatório!") 
	@Size(min = 3, max = 1000, message = "O atributo descrição deve conter no mínimo 05 e no máximo 1000 caracteres")
	private String descricao;
	
	@NotBlank(message = "O atributo repetições é obrigatório!")
	private Integer repeticao;
	
	@NotBlank(message = "O atributo Tempo de Descanso é Obrigatório!") 
	@Size(min = 3, max = 100, message = "O atributo Tempo de Descanso deve conter no mínimo 03 e no máximo 100 caracteres")
	private String tempoDescanso;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getRepeticao() {
		return repeticao;
	}

	public void setRepeticao(Integer repeticao) {
		this.repeticao = repeticao;
	}

	public String getTempoDescanso() {
		return tempoDescanso;
	}

	public void setTempoDescanso(String tempoDescanso) {
		this.tempoDescanso = tempoDescanso;
	}

}
