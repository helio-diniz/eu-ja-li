package org.coursera.eujali.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.coursera.eujali.service.NegocioException;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable, Comparable<Usuario> {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;
	private String codigo;
	private String senha;
	private Integer pontos = 0;
	private Integer quantidadeTrofeus = 0;
	private List<Leitura> leituras = new ArrayList<>();
	private List<Trofeu> trofeus = new ArrayList<>();
	private List<Grupo> grupos = new ArrayList<>();

	public Usuario() {
		super();
	}

	public Usuario(Long id, String nome, String codigo, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.codigo = codigo;
		this.senha = senha;
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
	@Column(name = "nome", nullable = false, length = 200)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@NotNull
	@Column(name = "codigo", nullable = false, length = 30)
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@NotNull
	@Column(name = "senha", nullable = false, length = 50)
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Column(name = "pontos", nullable = true)
	public Integer getPontos() {
		return pontos;
	}

	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}

	@OneToMany(mappedBy = "usuario" , cascade = CascadeType.MERGE)
	public List<Leitura> getLeituras() {
		return leituras;
	}

	public void setLeituras(List<Leitura> leitura) {
		this.leituras = leitura;
	}

	@Column(name = "quantidade_trofeus", nullable = true)
	public Integer getQuantidadeTrofeus() {
		return quantidadeTrofeus;
	}

	public void setQuantidadeTrofeus(Integer quantidadeTrofeus) {
		this.quantidadeTrofeus = quantidadeTrofeus;
	}

	@OneToMany(mappedBy = "usuario" , cascade = CascadeType.MERGE)
	public List<Trofeu> getTrofeus() {
		return trofeus;
	}

	public void setTrofeus(List<Trofeu> trofeus) {
		this.trofeus = trofeus;
	}
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "usuario_grupo", joinColumns = @JoinColumn (name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "grupo_id"))
	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	private boolean leuLivro(Livro livro) {
		for (Leitura livroLido : leituras) {
			if (livroLido.getLivro().getId().equals(livro.getId())) {
				return true;
			}
		}
		return false;
	}

	private void atualizarLeituraUsuario(Livro livro, Leitura leitura) {
		this.setPontos(this.getPontos() + leitura.getPontos());
		this.leituras.add(leitura);
	}

	public void lerLivro(Livro livro, Leitura leitura) {
		if (leuLivro(livro)) {
			throw new NegocioException("Livro já lido pelo usuário!");
		}
		atualizarLeituraUsuario(livro, leitura);
	}

	public void adicionaTrofeu(Trofeu trofeu) {
		boolean achouTrofeuEstilo = false;

		for (int i = 0; i < trofeus.size(); i++) {
			if (trofeus.get(i).getEstilo().equals(trofeu.getEstilo())
					&& trofeus.get(i).getEstado().equals(EstadoTrofeu.CONCORRENDO)) {
				achouTrofeuEstilo = true;
				break;
			}
		}

		if (trofeu.getEstado().equals(EstadoTrofeu.CONQUISTADO)) {
			this.setQuantidadeTrofeus(this.getQuantidadeTrofeus() + 1);
		} else if (!achouTrofeuEstilo) {
			this.trofeus.add(trofeu);
		}
	}

	@Override
	public int compareTo(Usuario outroUsuario) {
		if (this.getPontos() > outroUsuario.getPontos()) {
			return -1;
		}
		if (this.getPontos() < outroUsuario.getPontos()) {
			return 1;
		}
		return this.getNome().compareTo(outroUsuario.getNome());

	}

}
