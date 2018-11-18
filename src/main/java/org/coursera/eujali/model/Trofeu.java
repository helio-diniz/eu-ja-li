package org.coursera.eujali.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="trofeu")
public class Trofeu implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Usuario usuario;
	private Estilo estilo;
	private EstadoTrofeu estado;
	private List<Livro> livros = new ArrayList<>();;

	public Trofeu() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "estilo", nullable = false, length = 50)
	public Estilo getEstilo() {
		return estilo;
	}

	public void setEstilo(Estilo estilo) {
		this.estilo = estilo;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "estado", nullable = false, length = 50)
	public EstadoTrofeu getEstado() {
		return estado;
	}

	public void setEstado(EstadoTrofeu estado) {
		this.estado = estado;
	}

	@Transient
	public Integer getQuantidadeLivros() {
		return this.getLivros().size();
	}

	@ManyToMany
	@JoinTable(name = "trofeu_livro", joinColumns = @JoinColumn(name = "trofeu_id"), inverseJoinColumns = @JoinColumn(name = "livro_id"))
	public List<Livro> getLivros() {
		return livros;
	};

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	public void adicionarLivro(Livro livro) {
		this.livros.add(livro);
		if (this.getLivros().size() == 5) {
			this.setEstado(EstadoTrofeu.CONQUISTADO);
		}
	}

}
