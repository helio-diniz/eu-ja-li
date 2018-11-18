package org.coursera.eujali.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "livro")
public class Livro implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String titulo;
	private String imagemCapa;
	private Estilo estilo;
	private String editora;
	private Integer paginas;
	private Integer anoPublicacao;
	private List<Autor> autores;
	private Integer pontosPaginas;

	public Livro() {
		super();
	}

	public Livro(Long id, String titulo, String imagemCapa, Estilo estilo, String editora, Integer paginas,
			Integer anoPublicacao, List<Autor> autores) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.imagemCapa = imagemCapa;
		this.estilo = estilo;
		this.editora = editora;
		this.paginas = paginas;
		this.anoPublicacao = anoPublicacao;
		this.autores = autores;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull
	@Column(name = "titulo", nullable = false, length = 100)
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Column(name = "imagem_capa", nullable = true, length = 100)
	public String getImagemCapa() {
		return this.imagemCapa;
	}

	public void setImagemCapa(String imagemCapa) {
		this.imagemCapa = imagemCapa;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "estilo", nullable = true, length = 50)
	public Estilo getEstilo() {
		return estilo;
	}

	public void setEstilo(Estilo estilo) {
		this.estilo = estilo;
	}

	@Column(name = "editora", nullable = true, length = 100)
	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	@Column(name = "paginas", nullable = true)
	public Integer getPaginas() {
		return paginas;
	}

	public void setPaginas(Integer paginas) {
		this.paginas = paginas;
	}

	@Column(name = "ano_publicacao", nullable = true)
	public Integer getAnoPublicacao() {
		return anoPublicacao;
	}

	public void setAnoPublicacao(Integer anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "livro_autor", joinColumns = @JoinColumn(name = "livro_id"), inverseJoinColumns = @JoinColumn(name = "autor_id"))
	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	@Transient
	public Integer getPontosPaginas() {
		pontosPaginas = (paginas) / 100;
		if (paginas % 100 != 0) {
			pontosPaginas += 1;
		}
		return pontosPaginas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void marcarLido(Usuario usuario) {

	}

}
