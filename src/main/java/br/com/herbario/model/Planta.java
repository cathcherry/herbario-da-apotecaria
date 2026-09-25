package br.com.herbario.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "plantas")
public class Planta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Informe o nome da planta.")
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String nome;

    @Size(max = 150)
    @Column(name = "nome_cientifico", length = 150)
    private String nomeCientifico;

    @NotBlank(message = "Selecione uma categoria.")
    @Column(nullable = false, length = 30)
    private String tipo;

    @NotBlank(message = "Selecione um nível de toxicidade.")
    @Column(nullable = false, length = 30)
    private String toxicidade;

    @Size(max = 255)
    @Column(length = 255)
    private String uso;

    @Size(max = 2000)
    @Column(length = 2000)
    private String descricao;

    @Size(max = 100)
    @Column(length = 100)
    private String origem;

    @Size(max = 120)
    @Column(length = 120)
    private String habitat;

    @Size(max = 120)
    @Column(name = "parte_utilizada", length = 120)
    private String parteUtilizada;

    @Size(max = 255)
    @Column(length = 255)
    private String cultivo;

    @Size(max = 1000)
    @Column(length = 1000)
    private String curiosidades;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String imagem;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getNomeCientifico() { return nomeCientifico; }
    public void setNomeCientifico(String nomeCientifico) { this.nomeCientifico = nomeCientifico; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getToxicidade() { return toxicidade; }
    public void setToxicidade(String toxicidade) { this.toxicidade = toxicidade; }
    public String getUso() { return uso; }
    public void setUso(String uso) { this.uso = uso; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getOrigem() { return origem; }
    public void setOrigem(String origem) { this.origem = origem; }
    public String getHabitat() { return habitat; }
    public void setHabitat(String habitat) { this.habitat = habitat; }
    public String getParteUtilizada() { return parteUtilizada; }
    public void setParteUtilizada(String parteUtilizada) { this.parteUtilizada = parteUtilizada; }
    public String getCultivo() { return cultivo; }
    public void setCultivo(String cultivo) { this.cultivo = cultivo; }
    public String getCuriosidades() { return curiosidades; }
    public void setCuriosidades(String curiosidades) { this.curiosidades = curiosidades; }
    public String getImagem() { return imagem; }
    public void setImagem(String imagem) { this.imagem = imagem; }
}
